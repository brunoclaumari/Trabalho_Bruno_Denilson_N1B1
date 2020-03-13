/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import EnumsArquivo.EnumArquivoTexto;
import EnumsArquivo.EnumTypeToken;
import entidades.Produto;
import java.io.IOException;


/**
 *
 * @author BRUNOSILVA
 */
public class ProdutoDao extends PadraoDAO<Produto> {

    public ProdutoDao() {
        setTipoArquivo(EnumArquivoTexto.PRODUTO.getNomeDoArquivo());
        setTypeParaListas(EnumTypeToken.PRODUTO.getTypeToken());
    }

    public ProdutoDao(Class entidade) {
        super(entidade);
    }

  
    
    @Override
    public boolean validaInclusaoDAO(Produto entidade) throws IOException {

        boolean valorInvalido = super.validaInclusaoDAO(entidade);

        if (entidade.getNome().isEmpty()) {
            System.out.println("Não pode deixar nome vazio");
            valorInvalido = true;
        }
        return valorInvalido;
    }  
    
}

