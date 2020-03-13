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
import negocio.TelaDeletePadrao;


/**
 *
 * @author BRUNOSILVA
 */
public class TelaDeletarVendedor extends MaqEstadoLogins {

    @Override
    @SuppressWarnings("null")
    public boolean Executar() {
        boolean sair = false;
        ArrayList<Vendedor> lista = null;
        PadraoDAO DAO = new VendedorDao();
        Vendedor vend = null;
        String nomeDaClasse = Vendedor.class.getSimpleName().toUpperCase();

        TelaDeletePadrao montaDelete = new TelaDeletePadrao();
        sair = montaDelete.MontaTelaParaDelete(lista, DAO, vend, sair, nomeDaClasse);

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
