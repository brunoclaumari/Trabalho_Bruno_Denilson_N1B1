/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadoConsole;

import java.util.Scanner;
import principal.Entrada;

/**
 *
 * @author BRUNOSILVA
 */
public class EstadoCadastrosDeGerente extends MaqEstadoLogins {

    @Override
    public boolean Executar() {
       
        
        System.out.println("CADASTRO DE GERENTE, bem vindo!!!");
        System.out.println("0 - Sair");
        System.out.println("1 - Cadastrar Gerente");
        System.out.println("--------------------------------");
        Scanner sc = new Scanner(System.in);
        
        int resp=sc.nextInt();
        
        boolean sair = false;
        //MaqEstadoLogins estadoMaq;
        switch (resp) {            
            case 0:
                sair = true;
                break;
            case 1:
                
                break;
        }
        
        return sair;
    }
    
}
