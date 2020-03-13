/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadoConsole;

import EnumsArquivo.EnumTipoCrud;
import dao.ClienteDao;

import dao.Itens_PedidoDao;
import dao.PadraoDAO;
import dao.PedidoDao;
import dao.ProdutoDao;

import entidades.Funcionario;

import entidades.Itens_Pedido;
import entidades.Pedido;
import entidades.Produto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import telaInicial.Entrada;

/**
 *
 * @author BRUNOSILVA
 */
public class TelaCadastroPedido extends MaqEstadoLogins {

    @Override
    public boolean Executar() {
        boolean sair = false;
        ArrayList<Pedido> listaPedido = new ArrayList<>();
        PedidoDao pedidoDAO = new PedidoDao();
        ArrayList<Itens_Pedido> listaItensP = new ArrayList<>();
        PadraoDAO itensDAO = new Itens_PedidoDao();

        String nomeDaClasse = Pedido.class.getSimpleName().toUpperCase();

        Scanner sc = new Scanner(System.in);

        Funcionario funcLogado = Entrada.usuario;

        Pedido pedido = null;
        int digUsuario = 0;
        boolean idEncontrado = false;
        char continuaCadastro;
        try {
            listaPedido = pedidoDAO.testeListagem(listaPedido, pedidoDAO.getTypeParaListas());
            listaItensP = itensDAO.testeListagem(listaItensP, itensDAO.getTypeParaListas());
        } catch (IOException ex) {
            Logger.getLogger(TelaCadastroPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<Produto> produtosAux = new ArrayList<>();

        ArrayList<Itens_Pedido> auxItensPedido = new ArrayList<>();
        pedido = new Pedido(funcLogado, pedidoDAO.sugereId(listaPedido));
        int idParaItem = itensDAO.sugereId(listaItensP);

        try {

            if (listaPedido == null) {
                listaPedido = new ArrayList<>();
            }
            if (listaItensP == null) {
                listaItensP = new ArrayList<>();
            }

            //Prepara para mostrar a lista de nomes do cliente
            System.out.println("BEM VINDO AO CADASTRO DE " + nomeDaClasse);
            ClienteDao cliDAO = new ClienteDao();
            ProdutoDao produtoDao = new ProdutoDao();

            produtosAux = produtoDao.testeListagem(produtosAux, produtoDao.getTypeParaListas());

            do {
                System.out.println("\nESCOLHA O ID DO CLIENTE PARA O PEDIDO ");
                System.out.println("----------------------------------------");
                cliDAO.EscreveOpcoesNaTela();
                digUsuario = sc.nextInt();
                sc.nextLine();

                if (cliDAO.consultar(digUsuario) == null) {
                    idEncontrado = false;
                } else {
                    pedido.setCliente(cliDAO.consultar(digUsuario));
                    idEncontrado = true;
                }

            } while (!idEncontrado);
            Produto pr = null;
            do {
                System.out.println("----------------------------------------");
                System.out.println("LISTA DE PRODUTOS, ESCOLHA O SEU!");

                //lista os produtos na tela para escolher
                produtosAux.stream().forEach((x) -> System.out.println(x));

                digUsuario = sc.nextInt();
                sc.nextLine();

                if (produtoDao.consultar(digUsuario) == null) {
                    idEncontrado = false;
                } else {
                    Itens_Pedido itemPedido = new Itens_Pedido(idParaItem);
                    itemPedido.setId_Pedido(pedido.getId());

                    pr = new Produto();
                    pr = produtoDao.consultar(digUsuario);
                    itemPedido.setId_Produto(pr.getId());
                    itemPedido.setPreco(pr.getPreco());
                    boolean temEstoque = false;
                    do {
                        int quantid = 0;
                        System.out.println(pr.getNome() + " QUANTIDADE EM ESTOQUE: " + pr.getQuantidade());
                        System.out.println("Digite a quantidade a ser comprada: ");
                        quantid = sc.nextInt();
                        sc.nextLine();

                        if (pr.getQuantidade() - quantid < 0) {
                            temEstoque = false;
                            System.out.println("Quantidade exigida acima do estoque!");
                        } else {
                            temEstoque = true;
                            itemPedido.setQuantidade(quantid);
                        }

                        //Vai tirando os itens da lista                        
                        for (Produto p : produtosAux) {
                            if (p.getId() == pr.getId()) {
                                produtosAux.remove(p);
                                break;
                            }
                        }
                    } while (!temEstoque);
                    //listaItensP.add(itemPedido);
                    auxItensPedido.add(itemPedido);

                    System.out.println("Produto selecionado \n"
                            + pr.getNome() + " - "
                            + itemPedido.getQuantidade() + " - "
                            + itemPedido.getPreco() + " - "
                            + itemPedido.subTotal());
                    idEncontrado = true;
                }

                do {
                    System.out.println("S--Confirmar e continuar");
                    System.out.println("N--Confirmar e sair");
                    continuaCadastro = sc.nextLine().toUpperCase().charAt(0);
                } while (continuaCadastro != 'N' && continuaCadastro != 'S');

                idParaItem++;

            } while (continuaCadastro == 'S');

            char resp;

            do {
                System.out.println("Deseja salvar os dados? S/N");
                resp = sc.nextLine().toUpperCase().charAt(0);
            } while (resp != 'N' && resp != 'S');

            if (resp == 'S') {
                String operacao = EnumTipoCrud.INCLUIR.getNomeDoArquivo();
                pedidoDAO.SalvarDadosDAO(pedido, operacao);
                for (Itens_Pedido it : auxItensPedido) {
                    Produto teste = produtoDao.consultar(it.getId_Produto());
                    teste.removeEstoque(it.getQuantidade());
                    produtoDao.SalvarDadosDAO(teste, "A");
                    //produtoDao.alterar(teste);
                    itensDAO.SalvarDadosDAO(it, operacao);
                }

                System.out.println("Pedido cadastrado com sucesso!\n");

                do {
                    System.out.println("Deseja mostrar os dados na tela? S/N");
                    resp = sc.nextLine().toUpperCase().charAt(0);
                } while (resp != 'N' && resp != 'S');

                if (resp == 'S') {
                    pedidoDAO.EscreveFaturaNaTela(pedido.getId());
                }

                System.out.println("Digite qualquer tecla para continuar!");
                sc.nextLine();

                //condiciona o menu ao tipo de usuario logado                
                if (Entrada.usuarioLogado.equals("gerente")) {
                    Entrada.estadoMaq = EnumEstadoConsole.MENU_OPCOES_GERENTE.getEstadoMaq();
                } else {
                    Entrada.estadoMaq = EnumEstadoConsole.MENU_OPCOES_VENDEDOR.getEstadoMaq();
                }

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            Logger.getLogger(TelaCadastroGerente.class.getName()).log(Level.SEVERE, null, e);
        } catch (NumberFormatException e) {
            System.out.println("Não foi possível converter algum número");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sair;

    }

}
/*1° Do/While
                for (T item : lista) {
                    if (item.getId() == digUsuario) {
                        entidade = item;
                        idEncontrado = true;
                        break;
                    }
                }
 */

 //produtoDao.EscreveOpcoesNaTela();
