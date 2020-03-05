/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadoConsole;

import dao.GerenteDao;
import dao.PadraoDAO;
import entidades.Gerente;
import entidades.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.Acessar;
import principal.Entrada;

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
        ArrayList<Gerente> gerentes = null;

        System.out.println("Digite seus dados de Gerente!");
        System.out.println("-----------------------------");
        System.out.println("LOGIN: ");
        usuario.setLogin(sc.nextLine());
        System.out.println("SENHA: ");
        usuario.setSenha(sc.nextLine());

        PadraoDAO gDao = new GerenteDao();

        try {
            gerentes = gDao.listar();
        } catch (IOException ex) {
            Logger.getLogger(EstadoLoginGerente.class.getName()).log(Level.SEVERE, null, ex);
        }

        Acessar ac = new Acessar();
        boolean senhaValida = false;

        if (gerentes == null) {
            System.out.println("A lista de gerentes está vazia, "
                    + "cadastre um gerente!");
            Entrada.estadoMaq=EnumEstadoConsole.CADASTRO_GERENTE.getEstadoMaq();

        } else {
            for (Gerente g : gerentes) {
                senhaValida = ac.validaUsuario(g.getUsuario(), usuario);
            }
        }
        
        
        return sair;
    }

}
