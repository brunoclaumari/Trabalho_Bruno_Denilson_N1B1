/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadoConsole;

import lixo.TesteTelaCadastroGerente;

/**
 *
 * @author BRUNOSILVA
 */
public enum EnumEstadoConsole {
    LOGIN_VENDEDOR(new EstadoLoginVendedor()),
    
    LOGIN_GERENTE(new EstadoLoginGerente()),
    
    MENU_OPCOES_GERENTE(new MenuOpcoesGerente()),
    
    MENU_OPCOES_VENDEDOR(new MenuOpcoesVendedor()),
    
    TELA_INICIAL(new EstadoTelaInicial()),
    
    CADASTRAR_FUNCIONARIO(new EstadoMenuCadastroFuncionario()),
    
    CADASTRA_GERENTE(new TelaCadastroGerente()),
    
    CADASTRA_VENDEDOR(new TelaCadastroVendedor()),
    
    CADASTRA_CLIENTE(new TelaCadastroCliente()),
    
    CADASTRA_PRODUTO(new TelaCadastroProduto()),
    
    CADASTRA_PEDIDO(new TelaCadastroPedido()),
    
    DELETAR_GERENTE(new TelaDeletarGerente()),
    
    DELETAR_VENDEDOR(new TelaDeletarVendedor()),
    
    DELETAR_CLIENTE(new TelaDeletarCliente()),
    
    DELETAR_PRODUTO(new TelaDeletarProduto()),
    
    DELETAR_PEDIDO(new TelaDeletarPedido()),
    
    ALTERAR_GERENTE(new TelaEditarGerente()),
    
    ALTERAR_VENDEDOR(new TelaEditarVendedor()),
    
    ALTERAR_CLIENTE(new TelaEditarCliente()),
    
    ALTERAR_PRODUTO(new TelaEditarProduto()),
    
    ALTERAR_PEDIDO(new TelaEditarPedido()),
    ;

    private final MaqEstadoLogins estadoMaq;

    private EnumEstadoConsole(MaqEstadoLogins estadoMaq) {
        this.estadoMaq = estadoMaq;
    }

    public MaqEstadoLogins getEstadoMaq() {
        return estadoMaq;
    }

}
