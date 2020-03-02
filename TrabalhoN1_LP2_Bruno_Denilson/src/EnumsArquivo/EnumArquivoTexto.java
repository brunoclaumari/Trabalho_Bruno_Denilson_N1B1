/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnumsArquivo;

/**
 *
 * @author BRUNOSILVA
 */
public enum EnumArquivoTexto {
    
    VENDEDOR("Vendedor.json"),
    GERENTE("Gerente.json"),
    USUARIO("Usuario.json"),
    PEDIDO("Pedido.json"),
    CLIENTE("Cliente.json"),
    PRODUTO("Produto.json"),
    ITENS_PEDIDO("Itens_Pedido.json"),
    IMPRESSOES("Impressoes.json");
    
    
    private String nomeDoArquivo;

    private EnumArquivoTexto(String nomeDoArquivo) {
        this.nomeDoArquivo = nomeDoArquivo;
    }

    public String getNomeDoArquivo() {
        return nomeDoArquivo;
    }
    
}
