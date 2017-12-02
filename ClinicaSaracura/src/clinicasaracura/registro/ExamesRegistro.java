/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.registro;

import clinicasaracura.modelo.Exames;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ExamesRegistro extends Registro {
    Scanner entrada;

    public ExamesRegistro() {
        super();
    }           
    
    @Override
    protected void lerArquivo(){
        String tmpLinha;
        String[] tmpCampos;
        
        try{
            entrada = new Scanner(new File("arquivos/exames"));
        }
        catch(FileNotFoundException ex){
            System.err.println("Arquivo de médicos não encontrado");
            System.exit(1);
        }
        
        while(entrada.hasNext()){
            tmpLinha = entrada.nextLine();
            tmpCampos = tmpLinha.split(";");
            
            Exames exame = new Exames();
            exame.setId(Integer.parseInt(tmpCampos[0]));
            exame.setMedico(tmpCampos[1]);
            exame.setEspecialidade(tmpCampos[2]);
            exame.setData(tmpCampos[3]);
            exame.setHorario(tmpCampos[4]);

            adicionarNaLista(exame);
        }
        
        entrada.close();
    }
}
