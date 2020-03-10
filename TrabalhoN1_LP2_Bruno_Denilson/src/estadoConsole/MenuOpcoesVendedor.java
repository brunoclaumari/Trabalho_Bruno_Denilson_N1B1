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
        List<Integer> listOpcoes = Arrays.asList(0, 1, 2, 3);
        int alternativa = 0;

        try {
            do {
                System.out.println("MENU DE OPCOES - VENDEDOR");
                System.out.println("Escolha a opção desejada: ");
                System.out.println("0 - SAIR");
                System.out.println("1 - CADASTRAR CLIENTES");
                System.out.println("2 - CADASTRAR PRODUTOS");
                System.out.println("3 - CADASTRAR PEDIDOS");
                System.out.println("---------------------------------");

                alternativa = sc.nextInt();

            } while (!listOpcoes.contains(alternativa));

        } catch (InputMismatchException e) {
            alternativa=-1;
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
        }
        return sair;
    }

}
