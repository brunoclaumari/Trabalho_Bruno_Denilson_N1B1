/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaInicial;

import entidades.Usuario;
import estadoConsole.EnumEstadoConsole;
import estadoConsole.MaqEstadoLogins;
import java.io.IOException;

/**
 *
 * @author 082170034
 */
public class Entrada {

    public static MaqEstadoLogins estadoMaq;
    
    public static String usuarioLogado;
    
    public static Usuario usuario; 
    
    public final static void clearConsole(){

        try{
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")){
                Runtime.getRuntime().exec("cls");

            }else{
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final IOException e){
        //  Tratar Exceptions
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String quemTaLogado;
        clearConsole();
        estadoMaq = EnumEstadoConsole.TELA_INICIAL.getEstadoMaq();
        boolean saindo = false;
        while (!saindo) {
            if(estadoMaq.getUsuLogado()!=null){
                usuarioLogado=estadoMaq.getUsuLogado();
            }
            //quemTaLogado=usuarioLogado;
            clearConsole();
            saindo = estadoMaq.Executar();
        }
        System.out.println("SAINDO DO SISTEMA, OBRIGADO!!!!");

    }

}


