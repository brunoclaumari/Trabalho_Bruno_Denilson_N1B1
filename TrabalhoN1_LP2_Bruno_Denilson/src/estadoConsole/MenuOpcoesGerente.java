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
public class MenuOpcoesGerente extends MaqEstadoLogins {

    @Override
    public boolean Executar() {
        boolean sair = false;
        Scanner sc = new Scanner(System.in);

        System.out.println("MENU DE OPCOES - GERENCIA");
        System.out.println("Escolha a opção desejada: ");
        System.out.println("0 - SAIR");
        System.out.println("1 - CADASTRAR FUNCIONARIOS");
        System.out.println("2 - CADASTRAR CLIENTES");
        System.out.println("3 - CADASTRAR PRODUTOS");
        System.out.println("4 - CADASTRAR PEDIDOS");

        System.out.println("---------------------------------");

        int alternativa = sc.nextInt();

        //MaqEstadoLogins estadoMaq;
        switch (alternativa) {
            case 0:
                sair = true;
                break;
            case 1:
                Entrada.estadoMaq = EnumEstadoConsole.CADASTRAR_FUNCIONARIO.getEstadoMaq();
                break;
            case 2:
                Entrada.estadoMaq = EnumEstadoConsole.CADASTRA_CLIENTE.getEstadoMaq();
                break;
            case 3:
                Entrada.estadoMaq = EnumEstadoConsole.CADASTRA_PRODUTO.getEstadoMaq();
                break;
            case 4:
                Entrada.estadoMaq = EnumEstadoConsole.CADASTRA_PEDIDO.getEstadoMaq();
                break;
        }
        return sair;
    }

}
