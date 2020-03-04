/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadoConsole;

import dao.GerenteDao;
import entidades.Gerente;
import entidades.Usuario;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.Acessar;

/**
 *
 * @author bruna
 */
public class EstadoLoginGerente extends MaqEstadoLogins {

    @Override
    public boolean Executar() {
        boolean sair = false;
        Scanner sc = new Scanner(System.in);
        Usuario usuario = new Usuario();
        List<Gerente> gerentes = null;

        System.out.println("Digite seus dados de Gerente!");
        System.out.println("-----------------------------");
        System.out.println("LOGIN: ");
        usuario.setLogin(sc.nextLine());
        System.out.println("SENHA: ");
        usuario.setSenha(sc.nextLine());

        GerenteDao gDao = new GerenteDao();
        try {
            gerentes = gDao.sobeGerente();
        } catch (IOException ex) {
            Logger.getLogger(EstadoLoginGerente.class.getName()).log(Level.SEVERE, null, ex);
        }
        Acessar ac = new Acessar();
        boolean senhaValida=false;

        if (gerentes == null) {
            System.out.println("A lista de gerentes está vazia, "
                    + "cadastre um gerente! ");
            
        } else {
            for (Gerente g : gerentes) {
                senhaValida=ac.validaUsuario(g.getUsuario(), usuario);                
            }
        }

        return sair;
    }

}
