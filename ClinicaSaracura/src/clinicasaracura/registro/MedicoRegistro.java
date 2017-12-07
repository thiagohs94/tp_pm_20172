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

public class MedicoRegistro extends Registro {
    public MedicoRegistro() {
        super();
    }           
    
    @Override
    protected void lerArquivo(){
        String tmpLinha;
        String[] tmpCampos;
        Scanner entrada;
        
        try{
            entrada = new Scanner(new File("arquivos/medicos"));
            
            limparLista();
            while(entrada.hasNext()){
                tmpLinha = entrada.nextLine();
                tmpCampos = tmpLinha.split(";");

                Medico medico = new Medico();
                medico.setId(Integer.parseInt(tmpCampos[0]));
                medico.setNome(tmpCampos[1]);
                medico.setIdentidade(tmpCampos[2]);
                medico.setCpf(tmpCampos[3]);
                medico.setEndereco(tmpCampos[4]);
                medico.setTelefone(tmpCampos[5]);
                medico.setDataNascimento(tmpCampos[6]);
                medico.setEspecialidade(new Especialidade(Integer.parseInt(tmpCampos[7]),""));

                adicionarNaLista(medico);
            }

            entrada.close();
        }
        catch(FileNotFoundException ex){
            System.err.println("Arquivo de médicos não encontrado");
            System.exit(1);
        }
    }
    
    public ArrayList<Medico> buscarPorNome(String nome){
        ArrayList<Medico> result = new ArrayList();
        for(int i=0;i<lista.size();i++){
            if(((Medico)lista.get(i)).getNome().contains(nome)){
                result.add((Medico)lista.get(i));
            }
        }
        return result;
    }
    
    public ArrayList<Medico> buscarPorEspecialiade(int idEspecialidade){
        ArrayList<Medico> result = new ArrayList();
        for(int i=0;i<lista.size();i++){
            if(((Medico)lista.get(i)).getEspecialidade().getId() == idEspecialidade){
                result.add((Medico)lista.get(i));
            }
        }
        return result;
    }
}



