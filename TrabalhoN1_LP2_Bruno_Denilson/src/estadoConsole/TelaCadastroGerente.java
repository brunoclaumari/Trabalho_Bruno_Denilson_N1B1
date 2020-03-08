/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadoConsole;

import EnumsArquivo.EnumTipoCrud;
import dao.GerenteDao;
import dao.PadraoDAO;
import entidades.Gerente;
import entidades.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author 082170034
 */
public class TelaCadastroGerente extends MaqEstadoLogins {

    @Override
    public boolean Executar() {
        boolean sair = false;       

        ArrayList<Gerente> lista = new ArrayList<>();
        PadraoDAO DAO = new GerenteDao();

        Scanner sc = new Scanner(System.in);

        //String json = null;

        try {
            lista = DAO.transformaParaEntidade();
        } catch (IOException ex) {
            Logger.getLogger(TelaCadastroGerente.class.getName()).log(Level.SEVERE, null, ex);
        }

        int idSugerido;

        idSugerido = DAO.sugereId(lista);
        /*
         if (lista == null) {
            idSugerido = 1;
            //lista= new ArrayList<>();
        } else {
            idSugerido = lista.size() + 1;
        }
         */

        Gerente ger = new Gerente(idSugerido);
        Usuario usu = new Usuario();

        try {
            System.out.println("Bem vindo ao cadastro de Gerente");
            System.out.println("Digite o nome: ");
            ger.setNome(sc.nextLine());
            System.out.println("Digite um login para usuario: ");
            usu.setLogin(sc.nextLine());
            System.out.println("Digite uma senha para usuario: ");
            usu.setSenha(sc.nextLine());
            ger.setUsuario(usu);

            char resp;

            do {
                System.out.println("Deseja salvar os dados? S/N");
                resp = sc.nextLine().toUpperCase().charAt(0);
            } while (resp != 'N' && resp != 'S');

            if (resp == 'S') {
                String operacao=EnumTipoCrud.INCLUIR.getNomeDoArquivo();

                DAO.SalvarDadosDAO(ger, operacao);
                /*
             lista.add(ger);
            //json = DAO.MontaJson(lista);
            DAO.escreveArquivoJson(lista);
                 */
                System.out.println("O arquivo foi escrito!!");
            }
        } catch (IOException e) {
            e.getMessage();
        } catch (NumberFormatException e) {
            System.out.println("Não foi possível converter algum número");
        } catch (Exception e) {
            e.getMessage();
        }

        return sair;
    }

}
