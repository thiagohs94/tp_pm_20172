/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.registro;

import clinicasaracura.modelo.Especialidade;
import clinicasaracura.modelo.Medico;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EspecialidadeRegistro extends Registro {
    public EspecialidadeRegistro() {
        super();
        lerArquivo();
    }           
    
    @Override
    protected void lerArquivo(){
        String tmpLinha;
        String[] tmpCampos;
        Scanner entrada;
        
        try{
            entrada = new Scanner(new File("arquivos/especialidades"));
            limparLista();
            while(entrada.hasNext()){
                tmpLinha = entrada.nextLine();
                tmpCampos = tmpLinha.split(";");

                Especialidade especialidade = new Especialidade();
                especialidade.setId(Integer.parseInt(tmpCampos[0]));
                especialidade.setNome(tmpCampos[1]);

                adicionarNaLista(especialidade);
            }

            entrada.close();
        }
        catch(FileNotFoundException ex){
            System.err.println("Arquivo de especialidades não encontrado");
            System.exit(1);
        }        
    }
    
    public Especialidade buscarPorId(int idEspecialidade){
        for(int i=0;i<lista.size();i++){
            if(((Especialidade)lista.get(i)).getId() == idEspecialidade){
                return (Especialidade)lista.get(i);
            }
        }
        return null;
    }
}
