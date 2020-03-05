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
public enum EnumEstadoConsole {
    LOGIN_VENDEDOR(new EstadoLoginVendedor()),
    
    LOGIN_GERENTE(new EstadoLoginGerente()),
    
    MENU_PRINCIPAL(new EstadoMenuPrincipal()),
    
    TELA_INICIAL(new EstadoTelaInicial()),
    
    CADASTRO_GERENTE(new EstadoCadastrosDeGerente())
    ;

    private final MaqEstadoLogins estadoMaq;

    private EnumEstadoConsole(MaqEstadoLogins estadoMaq) {
        this.estadoMaq = estadoMaq;
    }

    public MaqEstadoLogins getEstadoMaq() {
        return estadoMaq;
    }

}
