/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import EnumsArquivo.EnumArquivoTexto;
import EnumsArquivo.EnumTypeToken;
import entidades.Itens_Pedido;
import entidades.Pedido;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

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
        ProdutoDao prodDAO = new ProdutoDao();
        ClienteDao cliDAO = new ClienteDao();

        if (prodDAO.consultar(entidade.getFuncionario().getId()) == null) {
            System.out.println("Funcionario não cadastrado");
            valorInvalido = true;
        }
        if (cliDAO.consultar(entidade.getCliente().getId()) == null) {
            System.out.println("Cliente não cadastrado");
            valorInvalido = true;
        }
        return valorInvalido;
    }

    //Escreve Lista de Entidades na tela para escolher
    public void EscreveFaturaNaTela(int id) throws IOException {
        Locale localeBR = new Locale("pt", "BR");
        NumberFormat formata = NumberFormat.getInstance(localeBR);
        Locale.setDefault(localeBR);

        ArrayList<Pedido> aux = null;

        ArrayList<Pedido> listagem = testeListagem(aux, getTypeParaListas());

        PadraoDAO itemDAO = new Itens_PedidoDao();
        ArrayList<Itens_Pedido> auxitem = null;
        ArrayList<Itens_Pedido> itensP = itemDAO.testeListagem(auxitem, itemDAO.getTypeParaListas());

        //Imprime os dados do pedido, tipo uma fatura
        System.out.println("-------------------DADOS DO PEDIDO------------------");
        System.out.println("----------------------------------------------------");
        listagem.stream()
                .filter(prod -> prod.getId() == id)
                .forEach((x) -> System.out.println(x));

        itensP.stream()
                .filter(itens -> itens.getId_Pedido() == id).forEach((x) -> System.out.println(x));

        double total = itensP.stream()
                .filter(itens -> itens.getId_Pedido() == id).mapToDouble(m -> m.subTotal()).sum();
        System.out.println("Total: R$ " + String.format("%.2f", total));

        System.out.println("----------------------------------------------------");

    }

}
