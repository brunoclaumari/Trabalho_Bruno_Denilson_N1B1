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
    
    CADASTRAR_FUNCIONARIO(new EstadoMenuCadastroFuncionario()),
    
    CADASTRA_GERENTE(new TelaCadastroGerente()),
    
    CADASTRA_VENDEDOR(new TelaCadastroVendedor()),
    
    CADASTRA_CLIENTE(new TelaCadastroCliente()),
    
    CADASTRA_PRODUTO(new TelaCadastroProduto()),
    
    CADASTRA_PEDIDO(new TelaCadastroPedido())
    ;

    private final MaqEstadoLogins estadoMaq;

    private EnumEstadoConsole(MaqEstadoLogins estadoMaq) {
        this.estadoMaq = estadoMaq;
    }

    public MaqEstadoLogins getEstadoMaq() {
        return estadoMaq;
    }

}
