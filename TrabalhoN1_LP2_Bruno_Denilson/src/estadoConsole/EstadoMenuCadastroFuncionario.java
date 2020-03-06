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
public class EstadoMenuCadastroFuncionario extends MaqEstadoLogins {

    @Override
    public boolean Executar() {
        boolean sair = false;
        Scanner sc = new Scanner(System.in);

        System.out.println("CADASTRO DE GERENTE, bem vindo!!!");
        System.out.println("0 - Sair");
        System.out.println("1 - Cadastrar Gerente");
        System.out.println("2 - Cadastrar Vendedor");
        System.out.println("--------------------------------");
        

        int resp = sc.nextInt();

        
        //MaqEstadoLogins estadoMaq;
        switch (resp) {
            case 0:
                sair = true;
                break;
            case 1:
                Entrada.estadoMaq=EnumEstadoConsole.CADASTRA_GERENTE.getEstadoMaq();
                break;
            case 2:
                Entrada.estadoMaq = EnumEstadoConsole.CADASTRA_VENDEDOR.getEstadoMaq();
                break;
        }

        return sair;
    }

}
