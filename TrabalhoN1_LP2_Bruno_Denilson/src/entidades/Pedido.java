/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;

/**
 *
 * @author 082170034
 */
public class Pedido extends EntidadePai {

    private Date data;
    private Funcionario funcionario;
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getData() {
        return data;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Pedido(Funcionario funcionario, int id) {
        super(id);
        this.data = new Date(System.currentTimeMillis());
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        return "Id: " + getId() + "-"
                + "Data: " + String.format("dd/MM/yyyy", getData()) + "-"
                + "Id_Funcionario: " + funcionario.getId()+ "-"
                + "Nome: " + funcionario.getNome()+ "-"
                + "Cliente: " + cliente.getNome();
    }

}
