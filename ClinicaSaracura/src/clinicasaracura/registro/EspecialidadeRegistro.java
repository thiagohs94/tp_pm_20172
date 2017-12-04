/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.registro;

import clinicasaracura.modelo.Especialidade;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EspecialidadeRegistro extends Registro {
    
    Scanner entrada;

    public EspecialidadeRegistro() {
        super();
    }           
    
    @Override
    protected void lerArquivo(){
        String tmpLinha;
        String[] tmpCampos;
        
        try{
            entrada = new Scanner(new File("arquivos/especialidades"));
        }
        catch(FileNotFoundException ex){
            System.err.println("Arquivo de especialidades não encontrado");
            System.exit(1);
        }
        
        limparLista();
        while(entrada.hasNext()){
            tmpLinha = entrada.nextLine();
            tmpCampos = tmpLinha.split(";");
            
            Especialidade especialidade = new Especialidade();
            especialidade.setId(Integer.parseInt(tmpCampos[0]));
            especialidade.setEspecialidade(tmpCampos[1]);

            adicionarNaLista(especialidade);
        }
        
        entrada.close();
    }
   
}
