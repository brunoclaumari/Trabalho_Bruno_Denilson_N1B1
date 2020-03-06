/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.internal.LinkedTreeMap;

import entidades.EntidadePai;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BRUNOSILVA
 * @param <T>
 */
public abstract class PadraoDAO<T extends EntidadePai> implements Icadastro {

    public PadraoDAO() {

    }

    public PadraoDAO(Class<T> entidade) {
        this.entidade = entidade;
    }   
        

    //Para definir o tipo de arquivo
    private String tipoArquivo;

    protected Class<T> entidade;

    protected String getTipoArquivo() {
        return tipoArquivo;
    }

    protected void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }    

    /**
     *
     * @param listaEntidades
     * @return //Pega uma entidade e transforma em arquivo Json
     */
    public String MontaJson(ArrayList<T> listaEntidades) {
        Gson gson = new GsonBuilder().create();
        //String caminho = getTipoArquivo();
        Type listType = new TypeToken<ArrayList<T>>() {
        }.getType();
        String js = gson.toJson(listaEntidades, listType);
        
        return js;
    }
    
    //Método abstrato que retorna um Json em uma lista de Entidaes

    /**
     *
     * @return Método abstrato que transforma um Json em uma lista de Entidades
     */
    public abstract ArrayList<T> transformaParaEntidade();   

    //Escreve o Arquivo Json na pasta
    public void escreveArquivoJson(ArrayList<T> listaEntidades) {
        String caminho = getTipoArquivo();
        String js = MontaJson(listaEntidades);

        //Imprime lista no arquivo, porém tem que subir o arquivo inteiro pois se houver
        //adição de dados, será adicionada na classe e na lista e salvará porcima do arquivo que já existe
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
            bw.write(js);

        } catch (IOException e) {
            e.getMessage();
        }
    }

    /**
     *
     * @return Método retorna uma lista da entidade atual a partir do arquivo
     * Json, porém não dá para serializar pois este é genérico
     * @throws IOException
     */
    //Lê um arquivo Json e retorna uma lista de entidades
    public ArrayList<T> listar() throws IOException {
        Gson gson = new GsonBuilder().create();
        //Gson gson = new Gson();
         ArrayList<T> listaArquivo = new ArrayList<>();
        String caminho = getTipoArquivo();

        if (!new File(caminho).exists()) {
            new File(caminho).createNewFile();
            //System.out.println("O arquivo não existe e foi criado");

        } else {
            try (JsonReader reader = new JsonReader(new FileReader(caminho))) {

                Type type2 = new TypeToken<ArrayList<T>>() {
                }.getType();         
                  
                listaArquivo = gson.fromJson(reader, type2); 

                System.out.println("O arquivo existe e foi lido\n");

            } catch (IOException e) {
                e.getMessage();
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return listaArquivo;
    }

    public T consultar(int id) throws IOException {
        ArrayList<T> cons = listar();

        T retorno = null;

        for (T ent : cons) {
            if (ent.getId() == id) {
                retorno = ent;
                break;
            }
        }

        return retorno;
    }
   

    @Override
    public void inserir(EntidadePai p) {
        try {
            ArrayList<T> arquivoLido = listar();
            arquivoLido.add((T) p);            

        } catch (IOException ex) {
            Logger.getLogger(PadraoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @Override
    public void alterar(EntidadePai p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(EntidadePai c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}


 //Pega um arquivo Json e transforma em uma lista de entidade
   /*
         public ArrayList<T> MontaListaDeEntidades(T entidade) {
        ArrayList<T> listaArquivo = null;
        try {
            listaArquivo = listar();
            listaArquivo.add(entidade);
        } catch (IOException ex) {
            Logger.getLogger(GerenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaArquivo;
    }    
    */
