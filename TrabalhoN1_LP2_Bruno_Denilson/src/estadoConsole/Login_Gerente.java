/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadoConsole;

import entidades.Usuario;
import java.util.Scanner;

/**
 *
 * @author bruna
 */
public class Login_Gerente extends MaqEstadoLogins{

    @Override
    public boolean Executar() {
        boolean sair=false;
        Scanner sc=new Scanner(System.in);
        Usuario usuario=new Usuario();
        
        System.out.println("Digite seus dados de Gerente!");
        System.out.println("-----------------------------");
        System.out.println("LOGIN: ");
        usuario.setLogin(sc.nextLine());
        System.out.println("SENHA: ");
        usuario.setSenha(sc.nextLine());
        
        
        
        
        
        return sair;
    }
    
    
}
