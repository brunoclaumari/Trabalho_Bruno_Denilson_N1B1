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
import entidades.EntidadePai;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BRUNOSILVA
 * @param <T>
 */
public abstract class PadraoDAO<T extends EntidadePai> implements Icadastro {
    //Para definir o tipo de arquivo

    private String tipoArquivo;

    protected Class<T> entidade;

    //Pega uma entidade e transforma em arquivo Json
    protected abstract String MontaJson(ArrayList<T> listaEntidades);

    //Pega um arquivo Json e transforma em uma entidade
    protected abstract ArrayList<T> MontaListaDeEntidades(T entidade);

    public PadraoDAO() {

    }

    public PadraoDAO(Class<T> entidade) {
        this.entidade = entidade;
    }

    protected String getTipoArquivo() {
        return tipoArquivo;
    }

    protected void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    /**
     *
     * @return Método retorna uma lista da entidade atual a partir do arquivo
     * Json
     * @throws IOException
     */
    public ArrayList<T> listar() throws IOException {
        Gson gson = new GsonBuilder().create();
        ArrayList<T> listaArquivo = new ArrayList<>();
        String caminho = getTipoArquivo();

        if (!new File(caminho).exists()) {
            new File(caminho).createNewFile();
            //System.out.println("O arquivo não existe e foi criado");

        } else {
            try (JsonReader reader = new JsonReader(new FileReader(caminho))) {
                Type type2 = new TypeToken<ArrayList<T>>() {
                }.getType();
                //lendo = br.readLine();
                listaArquivo = gson.fromJson(reader, type2);
                System.out.println("O arquivo existe e foi lido");

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
