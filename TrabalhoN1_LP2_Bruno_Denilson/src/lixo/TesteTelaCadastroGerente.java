/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lixo;


import dao.GerenteDao;
import dao.PadraoDAO;
import entidades.Gerente;
import estadoConsole.MaqEstadoLogins;

import java.io.IOException;

import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.TelaCadastrarPadrao;


/**
 *
 * @author 082170034
 */
public class TesteTelaCadastroGerente extends MaqEstadoLogins {

    @Override
    public boolean Executar() {
        boolean sair = false;

        ArrayList<Gerente> lista = new ArrayList<>();
        PadraoDAO DAO = new GerenteDao();        
        String nomeDaClasse = Gerente.class.getSimpleName().toUpperCase();
        
        try {
            lista = DAO.testeListagem(lista, DAO.getTypeParaListas());
        } catch (IOException ex) {
            Logger.getLogger(TesteTelaCadastroGerente.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        Gerente ger = new Gerente(DAO.sugereId(lista));

        TelaCadastrarPadrao montaCadastrar = new TelaCadastrarPadrao();

        sair = montaCadastrar.MontaTelaParaCadastrar(lista, DAO, ger, sair, nomeDaClasse);

        return sair;
    }

}
//DAO.SalvarDadosDAO(ger, operacao);
/*
             lista.add(ger);
            //json = DAO.MontaJson(lista);
            DAO.escreveArquivoJson(lista);
 */
 /*
int idSugerido;

        idSugerido = DAO.sugereId(lista);  
*/
