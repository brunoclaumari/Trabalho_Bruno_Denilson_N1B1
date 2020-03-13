/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import EnumsArquivo.EnumArquivoTexto;
import EnumsArquivo.EnumTypeToken;

import entidades.Vendedor;

import java.io.IOException;


/**
 *
 * @author BRUNOSILVA
 */
public class VendedorDao extends PadraoDAO<Vendedor> {

    public VendedorDao() {
        setTipoArquivo(EnumArquivoTexto.VENDEDOR.getNomeDoArquivo());
        setTypeParaListas(EnumTypeToken.VENDEDOR.getTypeToken());
    }

    public VendedorDao(Class entidade) {
        super(entidade);
    }

    /**
     *
     * @param entidade
     * @return //Pega um arquivo Json e transforma em uma lista de entidade Vendedor
     * @throws java.io.IOException
     *
     */ 
   
     @Override
    public boolean validaInclusaoDAO(Vendedor entidade) throws IOException {

        boolean valorInvalido = super.validaInclusaoDAO(entidade);

        if (entidade.getNome().isEmpty()) {
            System.out.println("Não pode deixar nome vazio");
            valorInvalido = true;
        }
        return valorInvalido;
    }      
}

