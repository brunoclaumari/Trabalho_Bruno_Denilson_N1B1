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

import entidades.Gerente;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    /**
     *
     * @return //Pega um arquivo Json e transforma em uma lista de entidade
     * Gerente
     * @throws java.io.IOException
     *
     */
    @Override
    public ArrayList<Gerente> transformaParaEntidade() throws IOException {
        ArrayList<Gerente> retornaLista = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        String caminho = getTipoArquivo();

        if (!new File(caminho).exists()) {
            new File(caminho).createNewFile();
            System.out.println("Arquivo " + caminho + " criado!");
        } else {
            try (JsonReader reader = new JsonReader(new FileReader(caminho))) {
                Type type2 = new TypeToken<ArrayList<Gerente>>() {
                }.getType();
                //lendo = br.readLine();
                retornaLista = gson.fromJson(reader, type2);
                System.out.println("O arquivo existe e foi lido");

            } catch (FileNotFoundException ex) {
                Logger.getLogger(GerenteDao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GerenteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return retornaLista;
    }

    @Override
    public boolean validaInclusaoDAO(Gerente entidade) throws IOException {

        boolean valorInvalido = super.validaInclusaoDAO(entidade);

        if (entidade.getNome().isEmpty()) {
            System.out.println("Não pode deixar nome vazio");
            valorInvalido = true;
        }
        return valorInvalido;
        //To change body of generated methods, choose Tools | Templates.

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
