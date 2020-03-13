/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadoConsole;

import dao.Itens_PedidoDao;

import dao.PedidoDao;

import entidades.Itens_Pedido;
import entidades.Pedido;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import negocio.TelaDeletePadrao;
import telaInicial.Entrada;

/**
 *
 * @author BRUNOSILVA
 */
public class TelaDeletarPedido extends MaqEstadoLogins {

    @Override
    @SuppressWarnings("null")
    public boolean Executar() {
        boolean sair = false;
        ArrayList<Pedido> pedidos = null;
        PedidoDao pedidoDAO = new PedidoDao();
        Pedido pedido = null;

        ArrayList<Itens_Pedido> listaItens = new ArrayList<>();
        Itens_PedidoDao itensDAO = new Itens_PedidoDao();

        String nomeDaClasse = Pedido.class.getSimpleName().toUpperCase();

        pedidos = null;
        //DAO = new VendedorDao();       

        Scanner sc = new Scanner(System.in);
        pedido = null;
        int resp = 0;
        boolean idEncontrado = false;

        try {
            pedidos = pedidoDAO.testeListagem(pedidos, pedidoDAO.getTypeParaListas());
            listaItens = itensDAO.testeListagem(listaItens, itensDAO.getTypeParaListas());

            //Mesmo tendo pedido e item, 
            //será escrito apenas os dados mais importantes do pedido
            pedidoDAO.EscreveOpcoesNaTela();

            do {
                System.out.println("\nEXCLUSÃO DE " + nomeDaClasse + "S");
                System.out.println("Digite um dos ID's da lista para deletar ");
                resp = sc.nextInt();

                for (Pedido item : pedidos) {
                    if (item.getId() == resp) {
                        pedido = item;
                        //Filtra a lista de itens_pedido pelo id_Pedido
                        listaItens.stream()
                                .filter(x -> x.getId_Pedido() == item.getId())
                                .collect(Collectors.toList());

                        idEncontrado = true;
                        break;
                    }
                }

            } while (!idEncontrado);

            boolean validaExclusaoDAO = pedidoDAO.validaExclusaoDAO(pedido);
            if (validaExclusaoDAO) {

                pedidoDAO.deletar(pedido.getId());

                for (Itens_Pedido itens : listaItens) {
                    if (itens.getId_Pedido() == pedido.getId()) {
                        itensDAO.deletar(itens.getId());
                    }
                }                
                System.out.println("Pedido Excluido com sucesso!\n");
                

                //condiciona o menu ao tipo de usuario logado                
                if (Entrada.usuarioLogado.equals("gerente")) {
                    Entrada.estadoMaq = EnumEstadoConsole.MENU_OPCOES_GERENTE.getEstadoMaq();
                } else {
                    Entrada.estadoMaq = EnumEstadoConsole.MENU_OPCOES_VENDEDOR.getEstadoMaq();
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(TelaDeletePadrao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InputMismatchException e) {
            resp = -1;
            e.getMessage();
            System.out.println("DIGITE APENAS OS NUMEROS INFORMADOS NO MENU!!!\n");
        }

        return sair;

    }

}

/*
boolean sair = false;

        ArrayList<Vendedor> lista = null;
        PadraoDAO DAO = new VendedorDao();

        Scanner sc = new Scanner(System.in);
        Vendedor vend = null;
        int resp = 0;
        boolean idEncontrado = false;

        try {
            lista = DAO.testeListagem(lista, DAO.getTypeParaListas());
            DAO.EscreveOpcoesNaTela();

            do {
                System.out.println("\nEXCLUSÃO DE VENDEDORES");
                System.out.println("Digite um dos ID's da lista para deletar ");
                resp = sc.nextInt();

                for (Vendedor item : lista) {
                    if (item.getId() == resp) {
                        vend = item;
                        idEncontrado = true;
                        break;
                    }
                }

            } while (!idEncontrado);

            boolean validaExclusaoDAO = DAO.validaExclusaoDAO(vend);
            if (validaExclusaoDAO) {
                DAO.deletar(vend.getId());
                //condiciona o menu ao tipo de usuario logado                
                if (Entrada.usuarioLogado.equals("gerente")) {
                    Entrada.estadoMaq = EnumEstadoConsole.MENU_OPCOES_GERENTE.getEstadoMaq();
                } else {
                    Entrada.estadoMaq = EnumEstadoConsole.MENU_OPCOES_VENDEDOR.getEstadoMaq();
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(TelaCadastroVendedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InputMismatchException e) {
            resp = -1;
            e.getMessage();
            System.out.println("DIGITE APENAS OS NUMEROS INFORMADOS NO MENU!!!\n");
        }
        return sair;
 */
