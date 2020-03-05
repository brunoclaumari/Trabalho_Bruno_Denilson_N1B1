/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Cliente;
import entidades.EntidadePai;
import entidades.Pedido;
import entidades.Produto;

/**
 *
 * @author 082170034
 */
public interface Icadastro {
    public void inserir(EntidadePai p);
    public void alterar(EntidadePai p);
    public void deletar(EntidadePai c);
     
    
}
