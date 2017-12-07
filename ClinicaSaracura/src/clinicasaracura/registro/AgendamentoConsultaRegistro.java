/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.registro;

import clinicasaracura.modelo.Agendamento;
import clinicasaracura.modelo.AgendamentoConsulta;
import clinicasaracura.modelo.AgendamentoExame;
import clinicasaracura.modelo.Cliente;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AgendamentoConsultaRegistro extends Registro{
    
    @Override
    protected void lerArquivo(){
        String tmpLinha;
        String[] tmpCampos;
        Scanner entrada;
        
        try{
            entrada = new Scanner(new File("arquivos/agendamentos_consultas"));
            limparLista();
            
            while(entrada.hasNext()){
                tmpLinha = entrada.nextLine();
                tmpCampos = tmpLinha.split(";");
                
                AgendamentoConsulta agendamento = new AgendamentoConsulta();
                agendamento.setMedico(null);
                agendamento.setDia(Integer.parseInt(tmpCampos[1]));
                agendamento.setHorario(Integer.parseInt(tmpCampos[2]));
                agendamento.setCliente(null);
                agendamento.setTipo(Integer.parseInt(tmpCampos[3]));
                adicionarNaLista(agendamento);
            }
            
            entrada.close();
        }
        catch(FileNotFoundException ex){
            System.err.println("Arquivo de agendamentos de consultas não encontrado");
            System.exit(1);
        }        
    }
}
