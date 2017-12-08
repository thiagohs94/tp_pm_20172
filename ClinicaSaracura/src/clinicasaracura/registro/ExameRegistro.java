/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.registro;

import clinicasaracura.modelo.Exame;
import clinicasaracura.modelo.Medico;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class ExameRegistro extends Registro {

    public ExameRegistro() {
        super();
        lerArquivo();
    }           
    
    @Override
    protected void lerArquivo(){
        String tmpLinha;
        String[] tmpCampos;
        Scanner entrada;
        
        try{
            entrada = new Scanner(new File("arquivos/exames"));
            
            limparLista();
            while(entrada.hasNext()){
                tmpLinha = entrada.nextLine();
                tmpCampos = tmpLinha.split(";");

                Exame exame = new Exame();
                exame.setId(Integer.parseInt(tmpCampos[0]));
                exame.setNome(tmpCampos[1]);

                adicionarNaLista(exame);
            }

            entrada.close();
        }
        catch(FileNotFoundException ex){
            System.err.println("Arquivo de médicos não encontrado");
            System.exit(1);
        }
        
    }
    
    public Exame buscarPorId(int idExame){
        for(int i=0;i<lista.size();i++){
            if(((Exame)lista.get(i)).getId() == idExame){
                return (Exame)lista.get(i);
            }
        }
        return null;
    }
    
    public ArrayList<Exame> buscarPorNome(String nome){
        ArrayList<Exame> result = new ArrayList();
        for(int i=0;i<lista.size();i++){
            if(((Exame)lista.get(i)).getNome().contains(nome)){
                result.add((Exame)lista.get(i));
            }
        }
        return result;
    }
    
}
