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
public class Gerente extends Funcionario {

    private Usuario usuario;

    public Gerente() {
        
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Gerente(int id) {
        super(id);
    }
    
    @Override
    public String toString(){
        String imp= String.format(" id: %d - "
                + "name: %s - "
                + "login: %s", getId(),getNome(),getUsuario().getLogin());
        return imp;
    }

}
