/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadoConsole;

import com.google.gson.internal.LinkedTreeMap;
import dao.GerenteDao;
import dao.PadraoDAO;
import entidades.Gerente;
import entidades.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.Acessar;
import telaInicial.Entrada;

/**
 *
 * @author bruna
 */
public class EstadoLoginGerente extends MaqEstadoLogins {

    @Override
    public boolean Executar() {
        boolean sair = false;
        boolean senhaValida = false;
        Scanner sc = new Scanner(System.in);
        Usuario usuario = new Usuario();

        ArrayList<Gerente> gerentes;

        System.out.println("Digite seus dados de Gerente!");
        System.out.println("-----------------------------");
        System.out.println("LOGIN: ");
        usuario.setLogin(sc.nextLine());
        System.out.println("SENHA: ");
        usuario.setSenha(sc.nextLine());

        PadraoDAO gDao = new GerenteDao();
        //ArrayList<Gerente> aux = new ArrayList<>();

        gerentes = gDao.transformaParaEntidade();

        Acessar ac = new Acessar();

        if (gerentes == null) {
            System.out.println("A lista de gerentes está vazia, "
                    + "cadastre um gerente!\n");
            Entrada.estadoMaq = EnumEstadoConsole.CADASTRAR_FUNCIONARIO.getEstadoMaq();

        } else {
            for (Gerente ger : gerentes) {
                senhaValida = ac.validaUsuario(ger.getUsuario(), usuario);               
            }
             if (senhaValida) {
                    Entrada.estadoMaq = EnumEstadoConsole.CADASTRA_PRODUTO.getEstadoMaq();
                } else {
                    System.out.println("Dados usuario e senha inválidos!!");
                    sair = true;
                }
        }

        return sair;
    }

}
