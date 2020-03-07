/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaInicial;

import estadoConsole.EnumEstadoConsole;
import estadoConsole.MaqEstadoLogins;

/**
 *
 * @author 082170034
 */
public class Entrada {

    public static MaqEstadoLogins estadoMaq;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        estadoMaq = EnumEstadoConsole.TELA_INICIAL.getEstadoMaq();
        boolean saindo = false;
        while (!saindo) {
            saindo = estadoMaq.Executar();
        }
        System.out.println("SAINDO DO SISTEMA, OBRIGADO!!!!");

    }

}
