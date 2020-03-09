/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import EnumsArquivo.EnumArquivoTexto;
import EnumsArquivo.EnumTypeToken;
import entidades.Produto;
import java.io.IOException;


/**
 *
 * @author BRUNOSILVA
 */
public class ProdutoDao extends PadraoDAO<Produto> {

    public ProdutoDao() {
        setTipoArquivo(EnumArquivoTexto.PRODUTO.getNomeDoArquivo());
        setTypeParaListas(EnumTypeToken.PRODUTO.getTypeToken());
    }

    public ProdutoDao(Class entidade) {
        super(entidade);
    }

    /**
     *
     * @param entidade
     * @return //Pega um arquivo Json e transforma em uma lista de entidade Produto
     * @throws java.io.IOException
     *
     */
    
    @Override
    public boolean validaInclusaoDAO(Produto entidade) throws IOException {

        boolean valorInvalido = super.validaInclusaoDAO(entidade);

        if (entidade.getNome().isEmpty()) {
            System.out.println("Não pode deixar nome vazio");
            valorInvalido = true;
        }
        return valorInvalido;
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

 /*
      @Override
    public ArrayList<Produto> transformaParaEntidade() throws IOException {
        ArrayList<Produto> retornaLista = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        String caminho = getTipoArquivo();

        if (!new File(caminho).exists()) {
            new File(caminho).createNewFile();
            System.out.println("Arquivo " + caminho + " criado!");
        } else {
            try (JsonReader reader = new JsonReader(new FileReader(caminho))) {
                Type type2 = new TypeToken<ArrayList<Produto>>() {
                }.getType();
                //lendo = br.readLine();
                retornaLista = gson.fromJson(reader, type2);
                System.out.println("O arquivo existe e foi lido");

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return retornaLista;
    }
    */
