/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnumsArquivo;

import com.google.gson.reflect.TypeToken;

import entidades.Cliente;
import entidades.Gerente;
import entidades.Itens_Pedido;
import entidades.Pedido;
import entidades.Produto;
import entidades.Vendedor;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 * @author BRUNOSILVA
 */
public enum EnumTypeToken {
    VENDEDOR(new TypeToken<ArrayList<Vendedor>>() {}.getType()),
    GERENTE(new TypeToken<ArrayList<Gerente>>() {}.getType()),    
    PEDIDO(new TypeToken<ArrayList<Pedido>>() {}.getType()),
    CLIENTE(new TypeToken<ArrayList<Cliente>>() {}.getType()),
    PRODUTO(new TypeToken<ArrayList<Produto>>() {}.getType()),
    ITENS_PEDIDO(new TypeToken<ArrayList<Itens_Pedido>>() {}.getType());

    private Type typeToken;
    
    EnumTypeToken(Type typeToken) {
        this.typeToken = typeToken;
    }

    public Type getTypeToken() {
        return typeToken;
    }

    

  
}
