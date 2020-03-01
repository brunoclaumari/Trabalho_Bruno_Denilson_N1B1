/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import entidades.Cliente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import jdk.nashorn.internal.ir.debug.JSONWriter;

/**
 *
 * @author bruna
 */
public class Teste_Arquivo_Json {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        List<Cliente> listaCli = new ArrayList<>();

        Cliente c1 = new Cliente(1);
        c1.setNome("Bruno");
        c1.setLista(new ArrayList<>());
        c1.getLista().add("Oi mano");
        c1.getLista().add("Oi cara");
        c1.getLista().add("e ae truta");

        listaCli.add(c1);

        Cliente c2 = new Cliente(2);
        c2.setNome("Mariana");
        c2.setLista(new ArrayList<>());
        c2.getLista().add("Oi papai");
        c2.getLista().add("Te amo papai");
        c2.getLista().add("Vc está bem?");

        listaCli.add(c2);

        Cliente c3 = new Cliente(3);
        c3.setNome("Mariana");
        c3.setLista(new ArrayList<>());
        c3.getLista().add("Oi papai");
        c3.getLista().add("Te amo papai");
        c3.getLista().add("Vc está bem?");

        listaCli.add(c3);

        Gson gson = new GsonBuilder().create();

        
        String path = "teste.json";
        String js = (gson.toJson(c1, Cliente.class));

        Type listType = new TypeToken<List<Cliente>>() {
        }.getType();

        //Imprimir avulso cliente c1
        
        //try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true)))//Adiciona item ao arquivo existente
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {//Sustitui o arquivo existente
            bw.append(js);

        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] vet;

        js = gson.toJson(listaCli, listType);
        path = "listaCliente.json";

        //Imprime lista no arquivo, porém tem que subir o arquivo inteiro pois se houver
        //adição de dados, será adicionada na classe e na lista e salvará porcima do arquivo que já existe
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(js);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //-------------------------------------------------------------
        //Lendo o Arquivo Json
        List<Cliente> leCliente = new ArrayList<>();

        //path = "existe.json";
        if (!new File(path).exists()) {
            new File(path).createNewFile();
            //System.out.println("O arquivo não existe e foi criado");

        } else {
            try (JsonReader reader = new JsonReader(new FileReader(path))) {
                Type type2 = new TypeToken<List<Cliente>>() {
                }.getType();
                //lendo = br.readLine();
                leCliente = gson.fromJson(reader, type2);
                System.out.println("O arquivo existe e foi lido");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        

        for (Cliente cli : leCliente) {
            System.out.println(cli.getId());
            System.out.println(cli.getNome());
            System.out.println(cli.getLista());

        }
    }

}
