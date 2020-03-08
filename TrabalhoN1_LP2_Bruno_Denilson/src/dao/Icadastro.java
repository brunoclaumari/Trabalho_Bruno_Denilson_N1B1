/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.EntidadePai;
import java.io.IOException;


/**
 *
 * @author 082170034
 * @param <T>
 */
public interface Icadastro <T extends EntidadePai>{
    public void inserir(T entidade) throws IOException;
    public void alterar(T entidade) throws IOException;
    public void deletar(T entidade) throws IOException;
     
    
}
