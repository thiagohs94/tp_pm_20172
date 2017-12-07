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
import clinicasaracura.modelo.Medico;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
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
                
                agendamento.setDia(Integer.parseInt(tmpCampos[0]));
                agendamento.setHorario(Integer.parseInt(tmpCampos[1]));
                agendamento.setCliente(new Cliente(Integer.parseInt(tmpCampos[2])));
                agendamento.setTipo(Integer.parseInt(tmpCampos[3]));
                agendamento.setMedico(new Medico(Integer.parseInt(tmpCampos[4])));
                adicionarNaLista(agendamento);
            }
            
            entrada.close();
        }
        catch(FileNotFoundException ex){
            System.err.println("Arquivo de agendamentos de consultas não encontrado");
            System.exit(1);
        }        
    }
    
    public void salvar(AgendamentoConsulta agendamento){
        try{
            FileWriter fileWriter = new FileWriter("arquivos/agendamentos_consultas", true);
            Formatter saida = new Formatter(fileWriter); 
            
            saida.format("%s;%s;%s;%s;%s\n",
                String.valueOf(agendamento.getDia()),
                agendamento.getHorario(),
                agendamento.getCliente().getId(),
                agendamento.getTipo(),
                agendamento.getMedico().getId());
            
            saida.close();
            
            adicionarNaLista(agendamento);
        }
        catch(IOException ex){
            System.err.println("Não é possível salvar agendamento");
            System.exit(1);
        }
    }
    
    public ArrayList<AgendamentoConsulta> pesquisarPorMedicoDiaHorario(int idMedico, int dia, int horario){
        ArrayList<AgendamentoConsulta> result = new ArrayList();
        for(int i=0;i<lista.size();i++){
            if(((AgendamentoConsulta)lista.get(i)).getMedico().getId() == idMedico
                && ((AgendamentoConsulta)lista.get(i)).getDia() == dia
                && ((AgendamentoConsulta)lista.get(i)).getHorario() == horario){
                result.add((AgendamentoConsulta)lista.get(i));
            }
        }
        return result;
    }
}
