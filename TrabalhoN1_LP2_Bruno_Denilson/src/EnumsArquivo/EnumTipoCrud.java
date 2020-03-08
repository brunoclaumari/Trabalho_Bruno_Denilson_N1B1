/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnumsArquivo;

/**
 *
 * @author BRUNOSILVA
 */
public enum EnumTipoCrud {

    INCLUIR("I"),
    ALTERAR("A");

    private String tipoCrud;

    private EnumTipoCrud(String tipoCrud) {
        this.tipoCrud = tipoCrud;
    }

    public String getNomeDoArquivo() {
        return tipoCrud;
    }

}
