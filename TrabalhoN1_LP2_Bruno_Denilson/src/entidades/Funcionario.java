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
public class Funcionario {
    private int id;
    private String nome;

    public Funcionario(int id) {
        this.id = id;
    }
    
    

    public int getId() {
        return id;
    }    

    /*//Não tem o set para que o id não seja modificado posteriormente
    public void setId(int id) {
        this.id = id;
    }
    */

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
 }
