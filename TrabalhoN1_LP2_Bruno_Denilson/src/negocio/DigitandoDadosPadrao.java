/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import entidades.Cliente;
import entidades.EntidadePai;
import entidades.Funcionario;
import entidades.Gerente;
import entidades.Pedido;
import entidades.Produto;
import entidades.Usuario;
import entidades.Vendedor;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author BRUNOSILVA
 * @param <T>
 */
public class DigitandoDadosPadrao<T extends EntidadePai> {

    public T EntradaComUsuario(T elemento, Usuario usuario, Scanner scanner) {
        Funcionario auxF = null;

        System.out.println("Digite o nome: ");
        String nome = (scanner.nextLine());
        System.out.println("Digite um login para usuario: ");
        usuario.setLogin(scanner.nextLine());
        System.out.println("Digite uma senha para usuario: ");
        usuario.setSenha(scanner.nextLine());

        if (elemento instanceof Gerente) {
            Gerente entidade = (Gerente) elemento;
            entidade.setNome(nome);
            entidade.setUsuario(usuario);
            auxF = entidade;

        } else {
            Vendedor entidade = (Vendedor) elemento;
            entidade.setNome(nome);
            entidade.setUsuario(usuario);
            auxF = entidade;
        }

        return (T) auxF;
    }

    public T EntradaDeDados(T elemento, Scanner scanner) {
        EntidadePai auxF = null;
        if (elemento instanceof Produto) {
            Produto entidade=(Produto) elemento;
            
            Locale localeBR = new Locale("pt","BR");
            NumberFormat formata=NumberFormat.getInstance(localeBR);
            Locale.setDefault(localeBR);
            
            System.out.println("Digite o nome: ");
            entidade.setNome(scanner.nextLine());
            System.out.println("Digite o preço");
            //String format = formata.format(scanner.nextLine());
            entidade.setPreco(scanner.nextDouble());
            scanner.nextLine();
            
            System.out.println("Digite a quantidade no estoque");            
            entidade.addEstoque(scanner.nextInt());
            scanner.nextLine();
           
           auxF = entidade;

        } else if (elemento instanceof Cliente) {
            Cliente entidade=(Cliente) elemento;
            
            System.out.println("Digite o nome: ");
            entidade.setNome(scanner.nextLine());
            
            auxF = entidade;
        } 

        return (T) auxF;

    }

}
