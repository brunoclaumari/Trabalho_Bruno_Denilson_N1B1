/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folder_threads;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BRUNOSILVA
 */
public class ThreadRunnableGestaoMensAuditoria implements Runnable {

    private boolean executando = false;

    private Thread thrd;

    public ThreadRunnableGestaoMensAuditoria() {
        thrd = new Thread(this);
        thrd.start();

    }

    @Override
    public void run() {
        setExecutando(true);
        while (executando) {
            String mensParaAuditoria = GerenciadorAuditoriaSingleton
                    .getInstancia()
                    .retiraMensagemAuditoria();
            if (mensParaAuditoria != null) {
                enviaMensAuditoria(mensParaAuditoria);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadRunnableGestaoMensAuditoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void enviaMensAuditoria(String mensagem) {

        try (PrintWriter bw = new PrintWriter(new FileWriter("Sistema de Controle de Segurança.txt", true))) {
            bw.println(mensagem);
            //System.out.printf("\nEnviado-> " + mensagem+"\n");
            Thread.sleep(1000);

        } catch (IOException e) {
            e.getMessage();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadRunnableGestaoMensAuditoria.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean getExecutando() {
        return executando;
    }

    public void setExecutando(boolean teste) {
        this.executando = teste;
    }

    /*
        public void setTr(Thread passaThread) {
            this.tr = passaThread;
        }
        
    
     */
    public Thread getTr() {
        return thrd;
    }
}
