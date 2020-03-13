/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
}
