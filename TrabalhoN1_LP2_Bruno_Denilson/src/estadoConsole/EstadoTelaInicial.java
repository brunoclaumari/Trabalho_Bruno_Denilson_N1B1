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
public class EstadoTelaInicial extends MaqEstadoLogins {

    @Override
    public boolean Executar() {
        System.out.println("Olá, bem vindo ao nosso sistema");
        System.out.println("Escolha a opção desejada: ");
        System.out.println("0 - Sair");
        System.out.println("1 - Login de Vendedor");
        System.out.println("2 - Login de Gerente");
        System.out.println("---------------------------------");
        Scanner sc = new Scanner(System.in);       
        
        int alternativa = sc.nextInt();
        
        boolean sair = false;
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
