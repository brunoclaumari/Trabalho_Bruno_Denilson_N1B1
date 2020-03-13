/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import entidades.Cliente;

import entidades.EntidadePai;
import entidades.Funcionario;

import entidades.Itens_Pedido;
import entidades.Pedido;
import entidades.Produto;

import estadoConsole.EnumEstadoConsole;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import java.util.logging.Level;
import java.util.logging.Logger;

import telaInicial.Entrada;

/**
 *
 * @author BRUNOSILVA
 * @param <T>
 */
public abstract class PadraoDAO<T extends EntidadePai> implements Icadastro {

    public PadraoDAO() {

    }

    public PadraoDAO(Class<T> entidade) {
        this.entidade = entidade;
    }

    //Para definir o TypeToken do para converter JSON em Entidade
    private Type typeParaListas;

    /**
     *
     * @return retorne type token para ler os arquivos
     */
    public Type getTypeParaListas() {
        return typeParaListas;
    }

    /**
     *
     * @param typeParaListas
     */
    public void setTypeParaListas(Type typeParaListas) {
        this.typeParaListas = typeParaListas;
    }

    //Para definir o nome de arquivo JSON
    private String tipoArquivo;

    protected Class<T> entidade;

    protected String getTipoArquivo() {
        return tipoArquivo;
    }

    protected void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    /**
     *
     * @param listaEntidades
     * @return //Pega uma entidade e transforma em arquivo Json
     */
    public String MontaJson(ArrayList<T> listaEntidades) {
        Gson gson = new GsonBuilder().create();
        //String caminho = getTipoArquivo();
        Type listType = new TypeToken<ArrayList<T>>() {
        }.getType();
        String js = gson.toJson(listaEntidades, listType);

        return js;
    }

    /**
     *
     * @param retornaLista
     * @param type2
     * @return Método abstrato que transforma um Json em uma lista de Entidades
     * @throws java.io.IOException
     */
    //NOVO TESTE PARA O GENERICS
    public ArrayList<T> testeListagem(ArrayList<T> retornaLista, Type type2) throws IOException {

        retornaLista = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        String caminho = getTipoArquivo();

        if (!new File(caminho).exists()) {
            new File(caminho).createNewFile();
            System.out.println("Arquivo " + caminho + " criado!");
        } else {
            try (JsonReader reader = new JsonReader(new FileReader(caminho))) {
                type2 = getTypeParaListas();
                //lendo = br.readLine();
                retornaLista = gson.fromJson(reader, type2);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(PadraoDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PadraoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retornaLista;
    }

    //Escreve Lista de Entidades na tela para escolher
    public void EscreveOpcoesNaTela() throws IOException {
        ArrayList<T> aux = null;

        ArrayList<T> listagem = testeListagem(aux, getTypeParaListas());

        for (T ent : listagem) {
            System.out.println(ent.toString());
        }
    }

    //Escreve o Arquivo Json na pasta
    public void escreveArquivoJson(ArrayList<T> listaEntidades) {
        String caminho = getTipoArquivo();
        String js = MontaJson(listaEntidades);

        Date data = new Date(System.currentTimeMillis());
        DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy , HH:mm:ss");
        String controleDeSeguranca = " Arquivo " + getTipoArquivo() + " alterado dia " + formatDate.format(data);

        //Imprime lista no arquivo, porém tem que subir o arquivo inteiro pois se houver
        //adição de dados, será adicionada na classe e na lista e salvará porcima do arquivo que já existe
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
            bw.write(js);

        } catch (IOException e) {
            e.getMessage();
        }

        try (PrintWriter bw = new PrintWriter(new FileWriter("Sistema de Controle de Segurança.txt", true))) {
            bw.println(controleDeSeguranca);

        } catch (IOException e) {
            e.getMessage();
        }
    }

    /**
     *
     * @param id
     * @return Metodo para consultar o arquivo texto
     * @throws IOException
     */
    public T consultar(int id) throws IOException {
        ArrayList<T> retornaLista = null;
        ArrayList<T> cons = testeListagem(retornaLista, getTypeParaListas());

        T retorno = null;

        if (cons == null || cons.isEmpty()) {
            return null;
        } else {
            //retorno = (T) cons.stream().filter(x -> x.getId() == id);

            for (T ent : cons) {
                if (ent.getId() == id) {
                    retorno = ent;
                    break;
                }
            }

        }
        return retorno;
    }

    /**
     *
     * @param entidade
     * @return valida dados ao incluir
     * @throws IOException
     */
    public boolean validaInclusaoDAO(T entidade) throws IOException {
        boolean valorInvalido = false;
        if (consultar(entidade.getId()) != null) {
            valorInvalido = true;
            System.out.println("Este id já existe!");

        }
        if (entidade.getId() <= 0) {
            valorInvalido = true;
            System.out.println("Esse id é inválido");
        }
        return valorInvalido;
    }

    /**
     *
     * @param entidade
     * @return Valida a alteração, para ver se o id solicitado realmente existe
     * no arquivo
     * @throws IOException
     */
    public boolean validaAlteracaoDAO(T entidade) throws IOException {
        boolean valorInvalido = false;
        if (consultar(entidade.getId()) == null) {
            valorInvalido = true;
            System.out.println("Este id NÃO existe!");
        }

        return valorInvalido;
    }

    /**
     *
     * @param entidade
     * @return Consulta os pedidos para saber se a entidade está registrada no
     * pedido. Se estiver, não será possível a exclusão.
     * @throws IOException
     */
    public boolean validaExclusaoDAO(T entidade) throws IOException {
        boolean valorValido = true;

        Scanner sc = new Scanner(System.in);

        PedidoDao pedidoDao = new PedidoDao();
        Itens_PedidoDao itensDao = new Itens_PedidoDao();

        EntidadePai auxEntidade;

        ArrayList<Pedido> aux = null;
        ArrayList<Itens_Pedido> auxItens = null;
        ArrayList<Pedido> pedidos = pedidoDao.testeListagem(aux, pedidoDao.getTypeParaListas());
        ArrayList<Itens_Pedido> itens = itensDao.testeListagem(auxItens, itensDao.getTypeParaListas());

        if (pedidos != null && itens != null) {
            if (entidade instanceof Funcionario) {
                //testa se Funcionario, ou os filhos estão na lista de pedidos
                for (Pedido ped : pedidos) {
                    if (ped.getFuncionario().equals(entidade)) {
                        valorValido = false;
                        System.out.println("Não é possível excluir o " + Funcionario.class.getSimpleName()
                                + ", ele existe em um pedido");
                        System.out.println("Pressione qualquer tecla para continuar");
                        sc.nextLine();
                        break;
                    }
                }
                //testa se Cliente estão na lista de pedidos
            } else if (entidade instanceof Cliente) {
                for (Pedido ped : pedidos) {
                    if (ped.getCliente().equals(entidade)) {
                        valorValido = false;
                        System.out.println("Não é possível excluir o " + Cliente.class.getSimpleName()
                                + ", ele existe em um pedido");
                        System.out.println("Pressione qualquer tecla para continuar");
                        sc.nextLine();
                        break;
                    }

                }//testa se Produto estão na lista de pedidos
            } else if (entidade instanceof Produto) {
                for (Itens_Pedido ped : itens) {
                    if (ped.getId_Produto() == entidade.getId()) {
                        valorValido = false;
                        System.out.println("Não é possível excluir o " + Produto.class.getSimpleName()
                                + ", ele existe em um pedido");
                        System.out.println("Pressione qualquer tecla para continuar");
                        sc.nextLine();
                        break;
                    }
                }
            } else {
                valorValido = true;
            }
        }

        return valorValido;
    }

    /**
     *
     * @param entidade - Salva os dados registrados e validados no arquivo texto
     * @param operacao
     * @throws IOException
     */
    public void SalvarDadosDAO(T entidade, String operacao) throws IOException {

        boolean dadosInvalidos = false;
        if (operacao.equals("I")) {
            dadosInvalidos = validaInclusaoDAO(entidade);
        } else {
            dadosInvalidos = validaAlteracaoDAO(entidade);
        }

        if (dadosInvalidos) {
            System.out.println("Dados inválidos, cadastre novamente!");
            Entrada.estadoMaq = EnumEstadoConsole.CADASTRA_GERENTE.getEstadoMaq();
        } else {
            if (operacao.equals("I")) {
                inserir(entidade);
            } else {
                alterar(entidade);
            }
        }
    }

    /**
     *
     * @param p
     * @throws IOException
     * @void Insere os dados no arquivo.
     */
    @Override
    public void inserir(EntidadePai p) throws IOException {
        ArrayList<T> retornaLista = null;

        ArrayList<T> arquivoLido = testeListagem(retornaLista, getTypeParaListas());

        //Testa se está nulo, pois aí será instanciado
        if (arquivoLido == null) {
            arquivoLido = new ArrayList<>();
        }
        arquivoLido.add((T) p);
        escreveArquivoJson(arquivoLido);

    }

    /**
     *
     * @param p
     * @throws IOException
     */
    @Override
    public void alterar(EntidadePai p) throws IOException {
        ArrayList<T> retornaLista = null;
        ArrayList<T> arquivoLido = testeListagem(retornaLista, getTypeParaListas());

        T antigo = (T) arquivoLido.stream().filter(x -> x.getId() == p.getId()).findAny().get();
        int indice = arquivoLido.indexOf(antigo);

        arquivoLido.remove(indice);
        arquivoLido.add(indice, (T) p);
        escreveArquivoJson(arquivoLido);

    }

    @Override
    public void deletar(int id) throws IOException {
        ArrayList<T> retornaLista = null;
        ArrayList<T> arquivoLido = testeListagem(retornaLista, getTypeParaListas());

        // T item = consultar(id);
        arquivoLido.removeIf((T it) -> it.getId() == id);
        escreveArquivoJson(arquivoLido);

    }

    public int sugereId(ArrayList<T> listEntidade) {
        int idSug;

        T aux;

        if (listEntidade == null || listEntidade.isEmpty()) {
            idSug = 1;
        } else {
            listEntidade.sort((a, b) -> Integer.compare(a.getId(), b.getId()));
            aux = listEntidade.get(listEntidade.size() - 1);
            idSug = aux.getId() + 1;
        }
        return idSug;
    }
}

