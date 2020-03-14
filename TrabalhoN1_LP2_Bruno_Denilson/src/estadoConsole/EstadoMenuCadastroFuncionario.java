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
public class EstadoMenuCadastroFuncionario extends MaqEstadoLogins {

    @Override
    public boolean Executar() {
        boolean sair = false;
        Scanner sc = new Scanner(System.in);
        int alternativa = 0;

        List<Integer> listOpcoes = Arrays.asList(0, 1, 2, 3, 4, 5, 6);

        try {
            do {
                System.out.println("CADASTRO DE GERENTE, bem vindo!!!");
                System.out.println("0 - SAIR");
                System.out.println("1 - CADASTRAR GERENTE");
                System.out.println("2 - ALTERAR GERENTE");
                System.out.println("3 - DELETAR GERENTE");
                System.out.println("4 - CADASTRAR VENDEDOR");
                System.out.println("5 - ALTERAR VENDEDOR");
                System.out.println("6 - DELETAR VENDEDOR");

                System.out.println("--------------------------------");

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
                Entrada.estadoMaq = EnumEstadoConsole.CADASTRA_GERENTE.getEstadoMaq();
                break;
            case 2:
                Entrada.estadoMaq = EnumEstadoConsole.ALTERAR_GERENTE.getEstadoMaq();
                break;
            case 3:
                Entrada.estadoMaq = EnumEstadoConsole.DELETAR_GERENTE.getEstadoMaq();
                break;
            case 4:
                Entrada.estadoMaq = EnumEstadoConsole.CADASTRA_VENDEDOR.getEstadoMaq();
                break;
            case 5:
                Entrada.estadoMaq = EnumEstadoConsole.ALTERAR_VENDEDOR.getEstadoMaq();
                break;
            case 6:
                Entrada.estadoMaq = EnumEstadoConsole.DELETAR_VENDEDOR.getEstadoMaq();
                break;
        }

        return sair;
    }

}
