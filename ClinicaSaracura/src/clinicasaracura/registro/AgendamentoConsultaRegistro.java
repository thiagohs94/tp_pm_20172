/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.registro;

import clinicasaracura.modelo.AgendamentoConsulta;
import clinicasaracura.modelo.AgendamentoExame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class AgendamentoConsultaRegistro extends Registro{
    private ClienteRegistro regClientes;
    private MedicoRegistro regMedicos;
    
    public AgendamentoConsultaRegistro(ClienteRegistro regClientes, MedicoRegistro regMedicos){
        super();
        this.regClientes = regClientes;
        this.regMedicos = regMedicos;
        lerArquivo();
    }
    
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
                agendamento.setCliente(regClientes.buscarPorId(Integer.parseInt(tmpCampos[2])));
                agendamento.setTipo(Integer.parseInt(tmpCampos[3]));
                agendamento.setMedico(regMedicos.buscarPorId(Integer.parseInt(tmpCampos[4])));
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
    
    public void remover(AgendamentoConsulta agendamento) {

        try {
            String file = "arquivos/agendamentos_consultas";
            File inFile = new File(file);

            if (!inFile.isFile()) {
                System.out.println("Parametro nao e um arquivo");
                return;
            }
            
            String lineToRemove = String.format("%s;%s;%s;%s;%s",
                String.valueOf(agendamento.getDia()),
                agendamento.getHorario(),
                agendamento.getCliente().getId(),
                agendamento.getTipo(),
                agendamento.getMedico().getId());

            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {

                if (!line.trim().equals(lineToRemove)) {

                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            //Delete the original file
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile)) {
                System.out.println("Could not rename file");
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        lerArquivo();
    }
    
    public AgendamentoConsulta buscarPorMedicoDiaHorario(int idMedico, int dia, int horario){
        for(int i=0;i<lista.size();i++){
            if(((AgendamentoConsulta)lista.get(i)).getMedico().getId() == idMedico
                && ((AgendamentoConsulta)lista.get(i)).getDia() == dia
                && ((AgendamentoConsulta)lista.get(i)).getHorario() == horario){
                return (AgendamentoConsulta)lista.get(i);
            }
        }
        return null;
    }
    
    public ArrayList<AgendamentoConsulta> buscarPorCliente(int idCliente){
        ArrayList<AgendamentoConsulta> result = new ArrayList();
        for(int i=0;i<lista.size();i++){
            if(((AgendamentoConsulta)lista.get(i)).getCliente().getId() == idCliente){
                result.add((AgendamentoConsulta)lista.get(i));
            }
        }
        return result;
    }
    
    
}
