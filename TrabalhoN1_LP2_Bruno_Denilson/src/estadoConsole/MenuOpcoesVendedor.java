/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadoConsole;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import telaInicial.Entrada;

/**
 *
 * @author BRUNOSILVA
 */
public class MenuOpcoesVendedor extends MaqEstadoLogins {

    public MenuOpcoesVendedor() {
        setUsuLogado("vendedor");
    }

    @Override
    public boolean Executar() {
        //quemTaLogado=getUsuLogado();

        boolean sair = false;
        Scanner sc = new Scanner(System.in);
        List<Integer> listOpcoes = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        int alternativa = 0;

        try {
            do {
                System.out.println("---------------------------------");
                System.out.println("---------------------------------");
                System.out.println("MENU DE OPCOES - VENDEDOR");
                System.out.println("Escolha a opção desejada: ");
                System.out.println("0 - SAIR");
                System.out.println("1 -  CADASTRAR CLIENTES");
                System.out.println("2 -  CADASTRAR PRODUTOS");
                System.out.println("3 -  CADASTRAR PEDIDOS");
                System.out.println("4 -  ALTERAR CLIENTES");
                System.out.println("5 -  ALTERAR PRODUTOS");
                System.out.println("6 -  ALTERAR PEDIDOS");
                System.out.println("7 -  DELETAR CLIENTES");
                System.out.println("8 -  DELETAR PRODUTOS");
                System.out.println("9 -  DELETAR PEDIDOS");
                System.out.println("---------------------------------");

                alternativa = sc.nextInt();

            } while (!listOpcoes.contains(alternativa));

        } catch (InputMismatchException e) {
            alternativa = -1;
            e.getMessage();
            System.out.println("DIGITE APENAS OS NUMEROS INFORMADOS NO MENU!!!\n");
        }

        //MaqEstadoLogins estadoMaq;
        switch (alternativa) {
            case 0:
                sair = true;
                break;
            case 1:
                Entrada.estadoMaq = EnumEstadoConsole.CADASTRA_CLIENTE.getEstadoMaq();
                break;
            case 2:
                Entrada.estadoMaq = EnumEstadoConsole.CADASTRA_PRODUTO.getEstadoMaq();
                break;
            case 3:
                Entrada.estadoMaq = EnumEstadoConsole.CADASTRA_PEDIDO.getEstadoMaq();
                break;           
            case 4:
                Entrada.estadoMaq = EnumEstadoConsole.ALTERAR_CLIENTE.getEstadoMaq();
                break;
            case 5:
                Entrada.estadoMaq = EnumEstadoConsole.ALTERAR_PRODUTO.getEstadoMaq();
                break;
            case 6:
                Entrada.estadoMaq = EnumEstadoConsole.ALTERAR_PEDIDO.getEstadoMaq();
                break;
            case 7:
                Entrada.estadoMaq = EnumEstadoConsole.ALTERAR_CLIENTE.getEstadoMaq();
                break;
            case 8:
                Entrada.estadoMaq = EnumEstadoConsole.ALTERAR_PRODUTO.getEstadoMaq();
                break;
            case 9:
                Entrada.estadoMaq = EnumEstadoConsole.ALTERAR_PEDIDO.getEstadoMaq();
                break;
        }
        return sair;
    }

}
