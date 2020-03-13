/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadoConsole;

/**
 *
 * @author BRUNOSILVA
 */
public abstract class MaqEstadoLogins {
    public abstract boolean Executar();

    public MaqEstadoLogins() {
        
    }
       
        
    //variavel para passar que tipo de usuario está de tela em tela
    private String usuLogado;

    public String getUsuLogado() {
        return usuLogado;
    }

    public void setUsuLogado(String usuLogado) {
        this.usuLogado = usuLogado;
    }    
    

}
