/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folder_threads;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BRUNOSILVA
 */
public class GerenciadorAuditoriaSingleton {

    //---------Inicio do Padrão Singleton---------------
    private static Object objeto = new Object();

    //instancia que se auto cria no Singleton
    private static GerenciadorAuditoriaSingleton _instancia;

    ThreadRunnableGestaoMensAuditoria run1;
    //Thread tr;

    //Fila de mensagens a serem enviadas
    private ConcurrentLinkedQueue<String> filaMensAuditoria;

    //construtor privado para instanciar a fila
    private GerenciadorAuditoriaSingleton() {
        filaMensAuditoria = new ConcurrentLinkedQueue<>();
    }

    //método estático que 'auto cria' a instancia do Singleton
    public static GerenciadorAuditoriaSingleton getInstancia() {
        if (_instancia == null) {
            _instancia = new GerenciadorAuditoriaSingleton();
        }

        return _instancia;
    }
    //--------------Fim do Padrão Singleton--------------------
    //-------------Métodos adicionais

    //Enfileira uma mensagem na fila desta classe
    public void addMensagemAuditoria(String mensAuditoria) {
        filaMensAuditoria.add(mensAuditoria);
    }

    //Desenfileira uma mensagem em uma string e a retorna
    public String retiraMensagemAuditoria() {

        String mens = filaMensAuditoria.poll();

        return mens;
    }

    public void ativarThread() {
        if (run1 == null) {
            //Esse runnable cria e inicia a Thread
            // dentro dele
            run1 = new ThreadRunnableGestaoMensAuditoria();
            
        }
    }

    public void desativarThread() {
        if (run1.getTr() != null) {
            //passa para o booleano que vai parar a 
            //execução atribuindo 'false'
            run1.setExecutando(false);
            try {
                run1.getTr().join(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GerenciadorAuditoriaSingleton.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (run1.getTr().isAlive()) {
                run1.getTr().interrupt();
            }
        }

    }

}
