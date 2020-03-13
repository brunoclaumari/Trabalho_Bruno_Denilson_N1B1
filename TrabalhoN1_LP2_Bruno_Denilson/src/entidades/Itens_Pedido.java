/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import dao.ProdutoDao;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BRUNOSILVA
 */
public class Itens_Pedido extends EntidadePai {

    private int id_Pedido;
    private int id_Produto;
    private int quantidade;
    private double preco;

    public Itens_Pedido(int id) {
        super(id);
    }

    public int getId_Pedido() {
        return id_Pedido;
    }

    public void setId_Pedido(int id_Pedido) {
        this.id_Pedido = id_Pedido;
    }

    public int getId_Produto() {
        return id_Produto;
    }

    public void setId_Produto(int id_Produto) {
        this.id_Produto = id_Produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double subTotal() {
        return quantidade * preco;
    }

    @Override
    @SuppressWarnings("null")
    public String toString() {
        ProdutoDao DAO=new ProdutoDao();
        Produto prod = null;
        try {
            prod = DAO.consultar(getId_Produto());
        } catch (IOException ex) {
            Logger.getLogger(Itens_Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "Produto: " + prod.getNome() + " - "
                + "Quantidade: " + getQuantidade() + " - "
                + "Preço: R$ " + String.format("%.2f", getPreco())  + " - "
                + "Subtotal: R$ " + String.format("%.2f",subTotal());
    }

}
