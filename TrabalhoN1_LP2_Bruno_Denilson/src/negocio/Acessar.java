/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;


import entidades.Usuario;

/**
 *
 * @author bruna
 */
public class Acessar {

    private boolean validaSenha_Login(String senhaUsu, String senhaDigitada) {
        return senhaUsu.equals(senhaDigitada);
    }

    public boolean validaUsuario(Usuario usuDigitado, Usuario usuDoSistema) {
        boolean usuValido;
        usuValido = validaSenha_Login(usuDigitado.getLogin(), usuDoSistema.getLogin());
        if (usuValido) {
            usuValido = validaSenha_Login(usuDigitado.getSenha(), usuDoSistema.getSenha());
        }

        return usuValido;
    }
    
    

}
