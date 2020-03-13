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
public class Funcionario extends EntidadePai {
    
    private String nome;  
    
     public Funcionario() {
        
    }

    public Funcionario(int id) {
        super(id);
    }
     
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
 }
