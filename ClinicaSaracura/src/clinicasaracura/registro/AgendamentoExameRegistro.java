/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.registro;

import clinicasaracura.modelo.AgendamentoConsulta;
import clinicasaracura.modelo.AgendamentoExame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AgendamentoExameRegistro extends Registro{

    @Override
    protected void lerArquivo(){
        String tmpLinha;
        String[] tmpCampos;
        Scanner entrada;
        
        try{
            entrada = new Scanner(new File("arquivos/agendamentos_exames"));
            limparLista();
            
            while(entrada.hasNext()){
                tmpLinha = entrada.nextLine();
                tmpCampos = tmpLinha.split(";");
                
                AgendamentoExame agendamento = new AgendamentoExame();
                agendamento.setExame(null);
                agendamento.setDia(Integer.parseInt(tmpCampos[1]));
                agendamento.setHorario(Integer.parseInt(tmpCampos[2]));
                agendamento.setCliente(null);
                agendamento.setTipo(Integer.parseInt(tmpCampos[3]));
                adicionarNaLista(agendamento);
            }
            
            entrada.close();
        }
        catch(FileNotFoundException ex){
            System.err.println("Arquivo de agendamentos de exames não encontrado");
            System.exit(1);
        }        
    }
    
}
