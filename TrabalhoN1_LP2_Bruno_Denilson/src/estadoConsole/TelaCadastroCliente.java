/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadoConsole;

import EnumsArquivo.EnumTipoCrud;
import dao.ClienteDao;
import dao.PadraoDAO;
import entidades.Cliente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 082170034
 */
public class TelaCadastroCliente extends MaqEstadoLogins {

    @Override
    public boolean Executar() {
        boolean sair = false;
        ArrayList<Cliente> lista = new ArrayList<>();
        PadraoDAO DAO = new ClienteDao();

        Scanner sc = new Scanner(System.in);       

        try {
            lista = DAO.testeListagem(lista, DAO.getTypeParaListas());
        } catch (IOException ex) {
            Logger.getLogger(TelaCadastroGerente.class.getName()).log(Level.SEVERE, null, ex);
        }

        int idSugerido;

        idSugerido = DAO.sugereId(lista);       

        Cliente cli = new Cliente(idSugerido);
        
        try {
            System.out.println("Bem vindo ao cadastro de Cliente");
            System.out.println("Digite o nome: ");
            cli.setNome(sc.nextLine());          

            char resp;

            do {
                System.out.println("Deseja salvar os dados? S/N");
                resp = sc.nextLine().toUpperCase().charAt(0);
            } while (resp != 'N' && resp != 'S');

            if (resp == 'S') {
                String operacao = EnumTipoCrud.INCLUIR.getNomeDoArquivo();
                DAO.SalvarDadosDAO(cli, operacao);
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
