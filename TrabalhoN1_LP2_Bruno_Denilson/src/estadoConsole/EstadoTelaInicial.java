/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadoConsole;

import java.util.Scanner;
import telaInicial.Entrada;

/**
 *
 * @author BRUNOSILVA
 */
public class EstadoTelaInicial extends MaqEstadoLogins {

    @Override
    public boolean Executar() {
        boolean sair = false;
        Scanner sc = new Scanner(System.in); 
        
        System.out.println("Olá, bem vindo ao nosso sistema");
        System.out.println("Escolha a opção desejada: ");
        System.out.println();
        System.out.println("0 - SAIR");
        System.out.println("1 - LOGIN DE VENDEDOR");
        System.out.println("2 - LOGIN DE GERENTE");
        System.out.println("---------------------------------");
              
        
        int alternativa = sc.nextInt();
        
        
        //MaqEstadoLogins estadoMaq;
        switch (alternativa) {            
            case 0:
                sair = true;
                break;
            case 1:
                Entrada.estadoMaq = EnumEstadoConsole.LOGIN_VENDEDOR.getEstadoMaq();
                break;
            case 2:
                Entrada.estadoMaq = EnumEstadoConsole.LOGIN_GERENTE.getEstadoMaq();
                break;                
        }
        
        return sair;
    }
    
    
}
