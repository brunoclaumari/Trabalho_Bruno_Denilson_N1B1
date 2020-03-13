/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import EnumsArquivo.EnumTipoCrud;
import dao.GerenteDao;
import dao.PadraoDAO;
import entidades.EntidadePai;
import entidades.Funcionario;
import entidades.Gerente;
import entidades.Usuario;
import estadoConsole.EnumEstadoConsole;
import estadoConsole.TelaCadastroGerente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import telaInicial.Entrada;

/**
 *
 * @author BRUNOSILVA
 * @param <T>
 */
public class TelaCadastrarPadrao<T extends EntidadePai> {
    //boolean sair = false;

    //ArrayList<Gerente> lista = new ArrayList<>();
    //PadraoDAO DAO = new GerenteDao();
    
    /*
    int idSugerido;

        idSugerido = DAO.sugereId(lista);

        entidade = new EntidadePai(idSugerido);
    
    */
    public boolean MontaTelaParaCadastrar(ArrayList<T> lista, PadraoDAO DAO, EntidadePai entidade, boolean sair, String nomeDaClasse) {

        Scanner sc = new Scanner(System.in);
        char resp;

        try {           
            
            System.out.println("BEM VINDO AO CADASTRO DE " + nomeDaClasse);
            DigitandoDadosPadrao digitando = new DigitandoDadosPadrao();

            if (entidade instanceof Funcionario) {
                Usuario usuario = new Usuario();
                entidade = (T) digitando.EntradaComUsuario(entidade, usuario, sc);
            } else {
                entidade = (T) digitando.EntradaDeDados(entidade, sc);
            }
            

            do {
                System.out.println("Deseja salvar os dados? S/N");
                resp = sc.nextLine().toUpperCase().charAt(0);
            } while (resp != 'N' && resp != 'S');

            if (resp == 'S') {
                String operacao = EnumTipoCrud.INCLUIR.getNomeDoArquivo();
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
            ex.getMessage();
        } catch (NumberFormatException e) {
            System.out.println("Não foi possível converter algum número");
        } catch (Exception e) {
            e.getMessage();
        }

        return sair;
    }

}
