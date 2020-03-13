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

import java.util.Scanner;
import negocio.Acessar;
import telaInicial.Entrada;

/**
 *
 * @author bruna
 */
public class EstadoLoginGerente extends MaqEstadoLogins {   

    
    EstadoLoginGerente() {
        setUsuLogado("gerente");
    }
        
   
    @Override                  
    public boolean Executar() {
        
        //quemTaLogado=getUsuLogado();
        

        boolean sair = false;
        boolean senhaValida = false;
        Scanner sc = new Scanner(System.in);
        Usuario usuario = new Usuario();

        ArrayList<Gerente> gerentes = null;

        System.out.println("DIGITE SEUS DADOS DE GERENTE!");
        System.out.println("-----------------------------");
        System.out.println("LOGIN: ");
        usuario.setLogin(sc.nextLine());
        System.out.println("SENHA: ");
        usuario.setSenha(sc.nextLine());

        PadraoDAO gDao = new GerenteDao();
        
        Entrada.usuario=usuario;
        //ArrayList<Gerente> aux = new ArrayList<>();

        try {
            gerentes = gDao.testeListagem(gerentes, gDao.getTypeParaListas());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Acessar ac = new Acessar();

        if (gerentes == null || gerentes.isEmpty()) {
            System.out.println("A lista de gerentes está vazia, "
                    + "cadastre um gerente!\n");
            //Entrada.usuarioLogado=getUsuLogado();
            Entrada.estadoMaq = EnumEstadoConsole.CADASTRAR_FUNCIONARIO.getEstadoMaq();

        } else {
            for (Gerente ger : gerentes) {
                senhaValida = ac.validaUsuario(ger.getUsuario(), usuario);
                if (senhaValida) {
                    Entrada.estadoMaq = EnumEstadoConsole.MENU_OPCOES_GERENTE.getEstadoMaq();
                    break;
                }
            }
            if (!senhaValida) {
                System.out.println("Dados usuario e senha inválidos!!\n");
                char resp;

                do {
                    System.out.println("USUARIO GERENTE INVALIDO!!!!");
                    System.out.println("VEJA AS OPÇÕES A SEGUIR: ");
                    System.out.println("'C'     --PARA SE CADASTRAR--");
                    System.out.println("'V' --PARA VOLTAR PARA O LOGIN--");
                    System.out.println("'S'       --PARA SAIR--");
                    System.out.println("------------------------------------");
                    resp = sc.nextLine().toUpperCase().charAt(0);
                } while (resp != 'C' && resp != 'V' && resp != 'S');

                switch (resp) {
                    case 'C':
                        Entrada.estadoMaq = EnumEstadoConsole.CADASTRA_GERENTE.getEstadoMaq();
                        break;
                    case 'V':
                        Entrada.estadoMaq = EnumEstadoConsole.LOGIN_GERENTE.getEstadoMaq();
                        break;
                    case 'S':
                        sair = true;
                        break;
                }                
            }
        }

        return sair;
    }

   

}
