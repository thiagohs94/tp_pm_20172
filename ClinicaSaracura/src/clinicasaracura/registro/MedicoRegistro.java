/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.registro;

import clinicasaracura.modelo.Medico;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MedicoRegistro {
    ArrayList<Medico> listaMedicos;
    Scanner entrada;

    public MedicoRegistro() {
        listaMedicos = new ArrayList<>();
    }           
    
    private void lerArquivo(){
        String tmpLinha;
        String[] tmpCampos;
        
        try{
            entrada = new Scanner(new File("arquivos/medicos"));
        }
        catch(FileNotFoundException ex){
            System.err.println("Arquivo de médicos não encontrado");
            System.exit(1);
        }
        
        while(entrada.hasNext()){
            tmpLinha = entrada.nextLine();
            tmpCampos = tmpLinha.split(",");
            
            Medico medico = new Medico();
            medico.setId(Integer.parseInt(tmpCampos[0]));
            medico.setNome(tmpCampos[1]);
            medico.setEspecialidade(tmpCampos[2]);
            
            listaMedicos.add(medico);
        }
        
        entrada.close();
    }

    public ArrayList<Medico> getListaMedicos() {
        lerArquivo();
        return listaMedicos;
    }
}



