/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author 082170034
 */
public class Cliente {
    private int id;
    private String nome;

    public Cliente(int id) {
        this.id = id;
    }    
    

    public int getId() {
        return id;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
