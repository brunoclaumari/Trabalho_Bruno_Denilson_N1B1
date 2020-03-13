/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao.PadraoDAO;

import entidades.EntidadePai;

import estadoConsole.EnumEstadoConsole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import telaInicial.Entrada;

/**
 *
 * @author BRUNOSILVA
 * @param <T>
 */
public class TelaDeletePadrao<T extends EntidadePai> {

    /**
     *
     * @param lista
     * @param DAO
     * @param entidade
     * @param sair
     * @param nomeClasse
     * @return
     */
    public boolean MontaTelaParaDelete(ArrayList<T> lista, PadraoDAO DAO, T entidade, boolean sair,String nomeClasse) {
        //sair = false;

        lista = null;
        //DAO = new VendedorDao();       

        Scanner sc = new Scanner(System.in);
        entidade = null;
        int resp = 0;
        boolean idEncontrado = false;

        try {
            lista = DAO.testeListagem(lista, DAO.getTypeParaListas());
            DAO.EscreveOpcoesNaTela();

            do {
                System.out.println("\nEXCLUSÃO DE " + nomeClasse);
                System.out.println("Digite um dos ID's da lista para deletar ");
                resp = sc.nextInt();

                for (T item : lista) {
                    if (item.getId() == resp) {
                        entidade = item;
                        idEncontrado = true;
                        break;
                    }
                }

            } while (!idEncontrado);

            boolean validaExclusaoDAO = DAO.validaExclusaoDAO(entidade);
            if (validaExclusaoDAO) {
                DAO.deletar(entidade.getId());
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
