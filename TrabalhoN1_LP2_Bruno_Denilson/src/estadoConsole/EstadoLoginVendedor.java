/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadoConsole;


import dao.PadraoDAO;
import dao.VendedorDao;
import entidades.Usuario;
import entidades.Vendedor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import negocio.Acessar;

import telaInicial.Entrada;

/**
 *
 * @author bruna
 */
public class EstadoLoginVendedor extends MaqEstadoLogins{

    public EstadoLoginVendedor() {
        setUsuLogado("vendedor");
    }  
  
    


   @Override
    public boolean Executar() {        
                
        boolean sair = false;
        boolean senhaValida = false;
        Scanner sc = new Scanner(System.in);
        Usuario usuario = new Usuario();

        ArrayList<Vendedor> vendedores = null;

        System.out.println("DIGITE SEUS DADOS DE VENDEDOR!");
        System.out.println("-----------------------------");
        System.out.println("LOGIN: ");
        usuario.setLogin(sc.nextLine());
        System.out.println("SENHA: ");
        usuario.setSenha(sc.nextLine());

        PadraoDAO vDAO = new VendedorDao();
        
        Entrada.usuario=usuario;
        //ArrayList<Gerente> aux = new ArrayList<>();

        try {
            vendedores = vDAO.testeListagem(vendedores, vDAO.getTypeParaListas());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Acessar ac = new Acessar();

        if (vendedores == null || vendedores.isEmpty()) {
            System.out.println("A lista de vendedores está vazia, "
                    + "peça a um gerente para cadastrar!\n");
            //Entrada.usuarioLogado=getUsuLogado();            

        } else {
            for (Vendedor vend : vendedores) {
                senhaValida = ac.validaUsuario(vend.getUsuario(), usuario);
                if (senhaValida) {
                    Entrada.estadoMaq = EnumEstadoConsole.MENU_OPCOES_VENDEDOR.getEstadoMaq();
                    break;
                }
            }
            if (!senhaValida) {
                System.out.println("Dados usuario e senha inválidos!!\n");
                char resp;

                do {
                    System.out.println("USUARIO VENDEDOR INVALIDO!!!!");
                    System.out.println("PARA TER CADASTRO DE ACESSO COMUNIQUE AO SEU GERENTE");
                    System.out.println("VEJA AS OPÇÕES A SEGUIR: ");
                    //System.out.println("'C'     --PARA SE CADASTRAR--");
                    System.out.println("'V' --PARA VOLTAR PARA O LOGIN--");
                    System.out.println("'S'       --PARA SAIR--");
                    System.out.println("------------------------------------");
                    resp = sc.nextLine().toUpperCase().charAt(0);
                } while (resp != 'V' && resp != 'S');

                switch (resp) {                    
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
