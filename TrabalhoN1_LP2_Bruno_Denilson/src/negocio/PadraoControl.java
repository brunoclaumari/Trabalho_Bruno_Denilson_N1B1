/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao.PadraoDAO;
import entidades.EntidadePai;

import java.io.IOException;


/**
 *
 * @author BRUNOSILVA
 * @param <T>
 */
public class PadraoControl<T extends EntidadePai> {    
    
    protected PadraoDAO<T> DAO;

    public PadraoDAO<T> getDAO() {
        return DAO;
    }

    public void setDAO(PadraoDAO<T> DAO) {
        this.DAO = DAO;
    }
    
    
    
    /**
     *
     * @param entidade
     * @return Valida a ação de inserir
     * @throws IOException
     */
    public boolean validaInclusao(T entidade) throws IOException {
        boolean valorInvalido = false;
        if (DAO.consultar(entidade.getId()) != null) {
            valorInvalido = true;
            System.out.println("Este id já existe!");

        }
        if (entidade.getId() <= 0) {
            valorInvalido = true;
            System.out.println("Esse id é inválido");

        }

        return valorInvalido;
    }
    
    
    
      /*
     public void SalvarDados(T entidade, String operacao) throws IOException {
        boolean dadosInvalidos = validaInclusao(entidade);
        if (dadosInvalidos) {
            System.out.println("Dados inválidos, cadastre novamente!");
            Entrada.estadoMaq=EnumEstadoConsole.CADASTRA_GERENTE.getEstadoMaq();
        } else {
            DAO.inserir(entidade);
        }

    }   
     */

}

