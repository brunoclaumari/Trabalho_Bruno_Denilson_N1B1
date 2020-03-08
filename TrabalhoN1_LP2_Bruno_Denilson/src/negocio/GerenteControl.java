/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;




import entidades.Gerente;
import java.io.IOException;

/**
 *
 * @author BRUNOSILVA
 */
public class GerenteControl extends PadraoControl<Gerente> {  

  

       
    @Override
    public boolean validaInclusao(Gerente entidade) throws IOException {
        boolean valorInvalido=super.validaInclusao(entidade);
        
        if(entidade.getNome().isEmpty()){
            System.out.println("Não pode deixar nome vazio");
            valorInvalido=true;            
        }
        return valorInvalido;
        //To change body of generated methods, choose Tools | Templates.
        
    }

   

  

  

}
