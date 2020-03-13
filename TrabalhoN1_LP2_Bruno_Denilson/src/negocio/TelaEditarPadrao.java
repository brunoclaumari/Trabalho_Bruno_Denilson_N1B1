/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import EnumsArquivo.EnumTipoCrud;
import dao.PadraoDAO;

import entidades.EntidadePai;
import entidades.Funcionario;

import entidades.Usuario;

import estadoConsole.EnumEstadoConsole;

import estadoConsole.TelaEditarVendedor;
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
public class TelaEditarPadrao<T extends EntidadePai> {

    /**
     *
     * @param lista
     * @param DAO
     * @param entidade
     * @param sair
     * @param nomeDaClasse
     * @return
     */
    public boolean MontaTelaParaAlterar(ArrayList<T> lista, PadraoDAO DAO, EntidadePai entidade, boolean sair, String nomeDaClasse) {
        lista = null;

        //int idDaBusca = 0;
        Scanner sc = new Scanner(System.in);

        int digUsuario = 0;
        boolean idEncontrado = false;

        try {
            lista = DAO.testeListagem(lista, DAO.getTypeParaListas());
            DAO.EscreveOpcoesNaTela();

            do {
                System.out.println("\nEDIÇÃO DE " + nomeDaClasse);
                System.out.println("Digite um dos ID's da lista para editar ");
                digUsuario = sc.nextInt();
                sc.nextLine();

                for (T item : lista) {
                    if (item.getId() == digUsuario) {
                        entidade = item;
                        idEncontrado = true;
                        break;
                    }
                }

            } while (!idEncontrado);

            //idDaBusca = resp;
            DigitandoDadosPadrao digitando = new DigitandoDadosPadrao();

            if (entidade instanceof Funcionario) {
                Usuario usuario = new Usuario();
                entidade = (T) digitando.EntradaComUsuario(entidade, usuario, sc);
            } else {
                entidade = (T) digitando.EntradaDeDados(entidade, sc);
            }

            char resp;

            do {
                System.out.println("Deseja salvar os dados? S/N");
                resp = sc.nextLine().toUpperCase().charAt(0);
            } while (resp != 'N' && resp != 'S');

            if (resp == 'S') {
                String operacao = EnumTipoCrud.ALTERAR.getNomeDoArquivo();
                DAO.SalvarDadosDAO(entidade, operacao);

                //condiciona o menu ao tipo de usuario logado                
                if (Entrada.usuarioLogado.equals("gerente")) {
                    Entrada.estadoMaq = EnumEstadoConsole.MENU_OPCOES_GERENTE.getEstadoMaq();
                } else {
                    Entrada.estadoMaq = EnumEstadoConsole.MENU_OPCOES_VENDEDOR.getEstadoMaq();
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(EntidadePai.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InputMismatchException e) {
            digUsuario = -1;
            e.getMessage();
            System.out.println("DIGITE APENAS OS NUMEROS INFORMADOS NO MENU!!!\n");
        }

        /*
        
         */
        return sair;

    }

}
