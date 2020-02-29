/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entidades.Cliente;
import java.util.ArrayList;




/**
 *
 * @author bruna
 */
public class Teste_Arquivo_Json {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Cliente c = new Cliente(1);
        c.setNome("Bruno");
        c.setLista(new ArrayList<>());
        c.getLista().add("Oi mano");
        c.getLista().add("Oi cara");
        c.getLista().add("e ae truta");
        
        
        
        Gson gson=new GsonBuilder().create();
        
        System.out.println(gson.toJson(c, Cliente.class));
        
    }
    
}
