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
 * @param <T>
 */
public interface Icadastro <T extends EntidadePai>{
    public void inserir(T entidade);
    public void alterar(T entidade);
    public void deletar(T entidade);
     
    
}
