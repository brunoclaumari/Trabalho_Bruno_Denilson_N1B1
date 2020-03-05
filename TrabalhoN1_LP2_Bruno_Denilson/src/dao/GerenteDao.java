/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import EnumsArquivo.EnumArquivoTexto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import entidades.EntidadePai;
import entidades.Gerente;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
import EnumsArquivo.EnumArquivoTexto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
 */
/**
 *
 * @author BRUNOSILVA
 */
public class GerenteDao extends PadraoDAO<Gerente> {

    public GerenteDao() {
        setTipoArquivo(EnumArquivoTexto.GERENTE.getNomeDoArquivo());
    }

    public GerenteDao(Class entidade) {
        super(entidade);
    }

    @Override
    protected String MontaJson(ArrayList<Gerente> listaEntidades) {
        Gson gson = new GsonBuilder().create();        
        String caminho = getTipoArquivo();       

        Type listType = new TypeToken<ArrayList<Gerente>>() {}.getType();      

        String js = gson.toJson(listaEntidades, listType);
        

        //Imprime lista no arquivo, porém tem que subir o arquivo inteiro pois se houver
        //adição de dados, será adicionada na classe e na lista e salvará porcima do arquivo que já existe
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
            bw.write(js);

        } catch (IOException e) {
            e.getMessage();
        }
        return js;
    }

    @Override
    protected ArrayList<Gerente> MontaListaDeEntidades(Gerente entidade) {
        ArrayList<Gerente> listaArquivo = null;
        try {
            listaArquivo = listar();
            listaArquivo.add(entidade);
        } catch (IOException ex) {
            Logger.getLogger(GerenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaArquivo;        
    }
}

/*
    
    public List<Gerente> sobeGerente() throws IOException {
        Gson gson = new GsonBuilder().create();
        List<Gerente> listaArquivoGerente = new ArrayList<>();
        String caminho = EnumArquivoTexto.GERENTE.getNomeDoArquivo();

        if (!new File(caminho).exists()) {
            new File(caminho).createNewFile();
            //System.out.println("O arquivo não existe e foi criado");

        } else {
            try (JsonReader reader = new JsonReader(new FileReader(caminho))) {
                Type type2 = new TypeToken<List<Gerente>>() {
                }.getType();
                //lendo = br.readLine();
                listaArquivoGerente = gson.fromJson(reader, type2);
                System.out.println("O arquivo existe e foi lido");

            } catch (IOException e) {
                e.getMessage();
            } catch (Exception e) {
                e.getMessage();
            }
        }

        return listaArquivoGerente;
    }
 */
