/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadoConsole;


import dao.ClienteDao;

import dao.PadraoDAO;
import entidades.Cliente;

import java.io.IOException;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.TelaCadastrarPadrao;


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
        String nomeDaClasse = Cliente.class.getSimpleName().toUpperCase();
        
        try {
            lista = DAO.testeListagem(lista, DAO.getTypeParaListas());
        } catch (IOException ex) {
            Logger.getLogger(TelaCadastroCliente.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        Cliente entidade = new Cliente(DAO.sugereId(lista));

        TelaCadastrarPadrao montaCadastrar = new TelaCadastrarPadrao();

        sair = montaCadastrar.MontaTelaParaCadastrar(lista, DAO, entidade, sair, nomeDaClasse);

        return sair;
    }

}
