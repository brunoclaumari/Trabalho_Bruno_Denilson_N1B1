/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import EnumsArquivo.EnumArquivoTexto;
import EnumsArquivo.EnumTypeToken;
import entidades.Gerente;
import java.io.IOException;


/**
 *
 * @author BRUNOSILVA
 */
public class GerenteDao extends PadraoDAO<Gerente> {

    public GerenteDao() {
        setTipoArquivo(EnumArquivoTexto.GERENTE.getNomeDoArquivo());
        setTypeParaListas(EnumTypeToken.GERENTE.getTypeToken());
    }

    public GerenteDao(Class entidade) {
        super(entidade);
    }
 
 
   
     @Override
    public boolean validaInclusaoDAO(Gerente entidade) throws IOException {

        boolean valorInvalido = super.validaInclusaoDAO(entidade);

        if (entidade.getNome().isEmpty()) {
            System.out.println("Não pode deixar nome vazio");
            valorInvalido = true;
        }
        return valorInvalido;
    }     
}
