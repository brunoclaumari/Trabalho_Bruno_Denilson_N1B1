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

import entidades.EntidadePai;
import estadoConsole.EnumEstadoConsole;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
    //public abstract ArrayList<T> transformaParaEntidade(ArrayList<T> retornaLista,Type type) throws IOException;
    
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
                System.out.println("O arquivo existe e foi lido");

            } catch (FileNotFoundException ex) {
                Logger.getLogger(GerenteDao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GerenteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retornaLista;    
    }

    //Escreve o Arquivo Json na pasta
    public void escreveArquivoJson(ArrayList<T> listaEntidades) {
        String caminho = getTipoArquivo();
        String js = MontaJson(listaEntidades);

        //Imprime lista no arquivo, porém tem que subir o arquivo inteiro pois se houver
        //adição de dados, será adicionada na classe e na lista e salvará porcima do arquivo que já existe
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
            bw.write(js);

        } catch (IOException e) {
            e.getMessage();
        }
    }

    //Lê um arquivo Json e retorna uma lista de entidades

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
            for (T ent : cons) {
                if (ent.getId() == id) {
                    retorno = ent;
                    break;
                }
            }
        }

        return retorno;
    }

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

    public void SalvarDadosDAO(T entidade, String operacao) throws IOException {

        boolean dadosInvalidos = validaInclusaoDAO(entidade);
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
        ArrayList<T> retornaLista=null;

        ArrayList<T> arquivoLido = testeListagem(retornaLista, getTypeParaListas());

        //Testa se está nulo, pois aí será instanciado
        if (arquivoLido == null) {
            arquivoLido = new ArrayList<>();
        }
        arquivoLido.add((T) p);
        escreveArquivoJson(arquivoLido);
        System.out.println("Arquivo escrito com sucesso");

    }

    @Override
    public void alterar(EntidadePai p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(EntidadePai p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

//Pega um arquivo Json e transforma em uma lista de entidade
/*
         public ArrayList<T> MontaListaDeEntidades(T entidade) {
        ArrayList<T> listaArquivo = null;
        try {
            listaArquivo = listar();
            listaArquivo.add(entidade);
        } catch (IOException ex) {
            Logger.getLogger(GerenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaArquivo;
    } 
 */
//---------------------------------------------------------------------------------------------
/**
 *
 * @return Método retorna uma lista da entidade atual a partir do arquivo Json,
 * porém não dá para serializar pois este é genérico
 * @throws IOException
 */

/*
public ArrayList<T> listar() throws IOException {
        Gson gson = new GsonBuilder().create();
        //Gson gson = new Gson();
         ArrayList<T> listaArquivo = new ArrayList<>();
        String caminho = getTipoArquivo();

        if (!new File(caminho).exists()) {
            new File(caminho).createNewFile();
            //System.out.println("O arquivo não existe e foi criado");

        } else {
            try (JsonReader reader = new JsonReader(new FileReader(caminho))) {

                Type type2 = new TypeToken<ArrayList<T>>() {
                }.getType();         
                  
                listaArquivo = gson.fromJson(reader, type2); 

                System.out.println("O arquivo existe e foi lido\n");

            } catch (IOException e) {
                e.getMessage();
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return listaArquivo;
    }
 */

/*



//ULTIMO QUE ESTAVA DANDO CERTO
 public String MontaJson(ArrayList<T> listaEntidades) {
        Gson gson = new GsonBuilder().create();
        //String caminho = getTipoArquivo();
        Type listType = new TypeToken<ArrayList<T>>() {
        }.getType();
        String js = gson.toJson(listaEntidades, listType);

        return js;
    }

-------------------------------------------------------------

 public T consultar(int id) throws IOException {
        ArrayList<T> cons = transformaParaEntidade();

        T retorno = null;

        if (cons == null || cons.isEmpty()) {
            return null;
        } else {
            for (T ent : cons) {
                if (ent.getId() == id) {
                    retorno = ent;
                    break;
                }
            }
        }

        return retorno;
    }

-----------------------------------------------

 public void inserir(EntidadePai p) throws IOException {

        ArrayList<T> arquivoLido = transformaParaEntidade();

        //Testa se está nulo, pois aí será instanciado
        if (arquivoLido == null) {
            arquivoLido = new ArrayList<>();
        }
        arquivoLido.add((T) p);
        escreveArquivoJson(arquivoLido);
        System.out.println("Arquivo escrito com sucesso");

    }

*/
