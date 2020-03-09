/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
*/
package dao;

import EnumsArquivo.EnumArquivoTexto;
import EnumsArquivo.EnumTypeToken;
import entidades.Gerente;
import entidades.Pedido;
import java.io.IOException;
/*

*/

/**
 *
 * @author BRUNOSILVA
 */
public class PedidoDao extends PadraoDAO<Pedido> {

    public PedidoDao() {
        
        setTipoArquivo(EnumArquivoTexto.PEDIDO.getNomeDoArquivo());
        setTypeParaListas(EnumTypeToken.PEDIDO.getTypeToken());
    }

    public PedidoDao(Class entidade) {
        super(entidade);
    }

    /**
     *
     * @param entidade
     * @return //Pega um arquivo Json e transforma em uma lista de entidade
     * Pedido
     * @throws java.io.IOException
     *
     */
   
     @Override
    public boolean validaInclusaoDAO(Pedido entidade) throws IOException {

        boolean valorInvalido = super.validaInclusaoDAO(entidade);
        PedidoDao DAO=new PedidoDao();

        if (DAO.consultar(entidade.getFuncionario().getId())==null) {
            System.out.println("Funcionario não cadastrado");
            valorInvalido = true;
        }
        return valorInvalido;
    }    
    
}


