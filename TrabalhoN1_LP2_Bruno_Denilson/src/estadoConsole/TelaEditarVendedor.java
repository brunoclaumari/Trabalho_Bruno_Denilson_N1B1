/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadoConsole;

import dao.PadraoDAO;
import dao.VendedorDao;


import entidades.Vendedor;


import java.util.ArrayList;

import negocio.TelaEditarPadrao;



/**
 *
 * @author BRUNOSILVA
 */
public class TelaEditarVendedor extends MaqEstadoLogins {

    @Override

    public boolean Executar() {
        boolean sair = false;
        ArrayList<Vendedor> lista = null;
        PadraoDAO DAO = new VendedorDao();
        Vendedor vend = null;
        String nomeDaClasse = Vendedor.class.getSimpleName().toUpperCase();
        
        TelaEditarPadrao montaAlterar = new TelaEditarPadrao();

        sair = montaAlterar.MontaTelaParaAlterar(lista, DAO, vend, sair, nomeDaClasse);
        return sair;

    }

}


/*


        //--------------------------------------------------
        ArrayList<Gerente> lista = null;
        PadraoDAO DAO = new GerenteDao();

        Scanner sc = new Scanner(System.in);
        Gerente ger = null;
        int resp = 0;
        boolean idEncontrado = false;

        try {
            lista = DAO.testeListagem(lista, DAO.getTypeParaListas());
            DAO.EscreveOpcoesNaTela();

            do {
                System.out.println("\nEXCLUSÃO DE GERENTES");
                System.out.println("Digite um dos ID's da lista para deletar ");
                resp = sc.nextInt();

                for (Gerente item : lista) {
                    if (item.getId() == resp) {
                        ger = item;
                        idEncontrado = true;
                        break;
                    }
                }

            } while (!idEncontrado);

            boolean validaExclusaoDAO = DAO.validaExclusaoDAO(ger);
            if (validaExclusaoDAO) {
                DAO.deletar(ger.getId());
                //condiciona o menu ao tipo de usuario logado                
                if (Entrada.usuarioLogado.equals("gerente")) {
                    Entrada.estadoMaq = EnumEstadoConsole.MENU_OPCOES_GERENTE.getEstadoMaq();
                } else {
                    Entrada.estadoMaq = EnumEstadoConsole.MENU_OPCOES_VENDEDOR.getEstadoMaq();
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(TelaCadastroGerente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InputMismatchException e) {
            resp = -1;
            e.getMessage();
            System.out.println("DIGITE APENAS OS NUMEROS INFORMADOS NO MENU!!!\n");
        }
        
        //----------------------------------------------
        return sair;
 */
