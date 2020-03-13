/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import EnumsArquivo.EnumArquivoTexto;
import EnumsArquivo.EnumTypeToken;
import entidades.Cliente;
import java.io.IOException;


/**
 *
 * @author BRUNOSILVA
 */
public class ClienteDao extends PadraoDAO<Cliente> {

    public ClienteDao() {
        setTipoArquivo(EnumArquivoTexto.CLIENTE.getNomeDoArquivo());
        setTypeParaListas(EnumTypeToken.CLIENTE.getTypeToken());
    }

    public ClienteDao(Class entidade) {
        super(entidade);
    }  
    
   
    @Override
    public boolean validaInclusaoDAO(Cliente entidade) throws IOException {

        boolean valorInvalido = super.validaInclusaoDAO(entidade);

        if (entidade.getNome().isEmpty()) {
            System.out.println("Não pode deixar nome vazio");
            valorInvalido = true;
        }
        return valorInvalido;
    }   
    
}


