/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import entidades.EntidadePai;

/**
 *
 * @author BRUNOSILVA
 */
public class gerenteControl extends padraoControl<EntidadePai> {

    @Override
    protected boolean validaDados(EntidadePai entidade) {
        boolean ehValido = super.validaDados(entidade); //To change body of generated methods, choose Tools | Templates.

        return ehValido;
    }

    @Override
    protected void SalvarDados(EntidadePai entidade) {
        super.SalvarDados(entidade); //To change body of generated methods, choose Tools | Templates.
    }

}
