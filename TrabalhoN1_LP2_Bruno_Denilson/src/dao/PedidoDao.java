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
import entidades.Pedido;
import java.io.IOException;
import java.util.ArrayList;

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

    @Override
    public boolean validaInclusaoDAO(Pedido entidade) throws IOException {

        boolean valorInvalido = super.validaInclusaoDAO(entidade);
        PedidoDao DAO = new PedidoDao();

        if (DAO.consultar(entidade.getFuncionario().getId()) == null) {
            System.out.println("Funcionario não cadastrado");
            valorInvalido = true;
        }
        return valorInvalido;
    }

    //Escreve Lista de Entidades na tela para escolher
    public void EscreveFaturaNaTela(int id) throws IOException {
        ArrayList<Pedido> aux = null;

        ArrayList<Pedido> listagem = testeListagem(aux, getTypeParaListas());

        PadraoDAO itemDAO = new Itens_PedidoDao();
        ArrayList<Itens_Pedido> auxitem = null;
        ArrayList<Itens_Pedido> itensP = itemDAO.testeListagem(auxitem, itemDAO.getTypeParaListas());
        
        //Imprime os dados do pedido, tipo uma fatura
        listagem.stream()
                .filter(prod -> prod.getId() == id)
                .forEach((x) -> System.out.println(x));
        
        itensP.stream()
                .filter(itens->itens.getId_Pedido()==id).forEach((x) -> System.out.println(x));
        

    }

}
