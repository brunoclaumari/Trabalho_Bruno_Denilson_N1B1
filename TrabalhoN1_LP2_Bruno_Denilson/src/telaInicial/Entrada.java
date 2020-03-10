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
    
    public static String usuarioLogado;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String quemTaLogado;
        estadoMaq = EnumEstadoConsole.TELA_INICIAL.getEstadoMaq();
        boolean saindo = false;
        while (!saindo) {
            if(estadoMaq.getUsuLogado()!=null){
                usuarioLogado=estadoMaq.getUsuLogado();
            }
            //quemTaLogado=usuarioLogado;
            saindo = estadoMaq.Executar();
        }
        System.out.println("SAINDO DO SISTEMA, OBRIGADO!!!!");

    }

}


