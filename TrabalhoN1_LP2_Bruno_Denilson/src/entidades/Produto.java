/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author 082170034
 */
public class Produto extends EntidadePai {

    private String nome;
    private double preco;
    private int quantEstoque;

    public Produto() {

    }

    public int getQuantidade() {
        return quantEstoque;
    }

    public Produto(int id) {
        super(id);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void addEstoque(int quant) {
        quantEstoque += quant;
    }

    public void removeEstoque(int quant) {
        quantEstoque -= quant;
    }

    @Override
    public String toString() {
        Locale localeBR = new Locale("pt", "BR");
        NumberFormat formata = NumberFormat.getInstance(localeBR);

        return "Id: " + getId() + " - "
                + "Nome: " + getNome() + " - "
                + "Preço: R$ " + String.format("%.2f", getPreco()) + " - "
                + "Quantidade: " + getQuantidade();
        //formata.format(getPreco())
    }

}
