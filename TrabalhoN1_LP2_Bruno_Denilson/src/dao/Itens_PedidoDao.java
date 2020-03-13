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
import entidades.Itens_Pedido;

import java.io.IOException;

/*

 */

/**
 *
 * @author BRUNOSILVA
 */
public class Itens_PedidoDao extends PadraoDAO<Itens_Pedido> {

    public Itens_PedidoDao() {

        setTipoArquivo(EnumArquivoTexto.ITENS_PEDIDO.getNomeDoArquivo());
        setTypeParaListas(EnumTypeToken.ITENS_PEDIDO.getTypeToken());
    }

    public Itens_PedidoDao(Class entidade) {
        super(entidade);
    }

    @Override
    public boolean validaInclusaoDAO(Itens_Pedido entidade) throws IOException {
        boolean valorInvalido = super.validaInclusaoDAO(entidade); 
        ProdutoDao DAO = new ProdutoDao();

        if (DAO.consultar(entidade.getId_Pedido()) == null) {
            System.out.println("Funcionario não cadastrado");
            valorInvalido = true;
        }
        return valorInvalido;
        
    }

    /**
     *
     * @param entidade
     * @return //Pega um arquivo Json e transforma em uma lista de entidade
     * Pedido
     * @throws java.io.IOException
     *
     */
    
   

}
