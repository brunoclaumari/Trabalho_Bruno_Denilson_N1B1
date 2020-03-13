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
public class EstadoTelaInicial extends MaqEstadoLogins {
    
    

    @Override
    public boolean Executar() {
        boolean sair = false;
        Scanner sc = new Scanner(System.in);
        List<Integer> listOpcoes = Arrays.asList(0, 1, 2);
        int alternativa = 0;

        try {
            do {
                System.out.println("Olá, bem vindo ao nosso sistema");
                System.out.println("Escolha a opção desejada: ");
                System.out.println();
                System.out.println("0 - SAIR");
                System.out.println("1 - LOGIN DE VENDEDOR");
                System.out.println("2 - LOGIN DE GERENTE");
                System.out.println("---------------------------------");

                alternativa = sc.nextInt();

            } while (!listOpcoes.contains(alternativa));

        } catch (InputMismatchException e) {
            alternativa=-1;
            //sair = false;
            e.getMessage();
            System.out.println("DIGITE APENAS OS NUMEROS INFORMADOS NO MENU!!!\n");           

        }

        //MaqEstadoLogins estadoMaq;
        switch (alternativa) {
            case 0:
                sair = true;
                break;
            case 1:
                setUsuLogado("vendedor");
                Entrada.estadoMaq = EnumEstadoConsole.LOGIN_VENDEDOR.getEstadoMaq();
                break;
            case 2:
                setUsuLogado("gerente");
                Entrada.estadoMaq = EnumEstadoConsole.LOGIN_GERENTE.getEstadoMaq();
                break;
        }

        return sair;
    }

}
