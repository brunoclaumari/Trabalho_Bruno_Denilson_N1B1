/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadoConsole;

import telaInicial.Entrada;

/**
 *
 * @author bruna
 */
public class EstadoLoginVendedor extends MaqEstadoLogins{

    public EstadoLoginVendedor() {
        setUsuLogado("vendedor");
    }  
  
    

    @Override
    public boolean Executar() {
        //quemTaLogado=getUsuLogado();
        Entrada.usuarioLogado=getUsuLogado();
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
