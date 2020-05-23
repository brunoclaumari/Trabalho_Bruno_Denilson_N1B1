/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaInicial;

import entidades.Funcionario;

import estadoConsole.EnumEstadoConsole;
import estadoConsole.MaqEstadoLogins;
import folder_threads.GerenciadorAuditoriaSingleton;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author 082170034
 */
public class Entrada {

    public static MaqEstadoLogins estadoMaq;

    public static String usuarioLogado;

    public static Funcionario usuario;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Locale localeBR = new Locale("pt", "BR");
        NumberFormat formata = NumberFormat.getInstance(localeBR);
        Locale.setDefault(localeBR);
        
        //Ativa a thread de envio de mensagens ao Sistema de Segurança
        GerenciadorAuditoriaSingleton.getInstancia().ativarThread();
        
        estadoMaq = EnumEstadoConsole.TELA_INICIAL.getEstadoMaq();
        boolean saindo = false;
        while (!saindo) {
            if (estadoMaq.getUsuLogado() != null) {
                usuarioLogado = estadoMaq.getUsuLogado();
            }
            //quemTaLogado=usuarioLogado;            
            saindo = estadoMaq.Executar();
        }
        if(saindo==true){
            
            GerenciadorAuditoriaSingleton.getInstancia().desativarThread();
            
        }

        System.out.println("SAINDO DO SISTEMA, OBRIGADO!!!!");

    }

}
