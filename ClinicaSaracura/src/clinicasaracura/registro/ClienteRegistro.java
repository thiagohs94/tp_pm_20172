/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.registro;

import clinicasaracura.modelo.Cliente;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class ClienteRegistro extends Registro {
    
    public ClienteRegistro() {
        super();
        lerArquivo();
    }           
    
    @Override
    protected void lerArquivo(){
        String tmpLinha;
        String[] tmpCampos;
        Scanner entrada;
        try{
            entrada = new Scanner(new File("arquivos/clientes"));
            limparLista();
            
            while(entrada.hasNext()){
                tmpLinha = entrada.nextLine();
                tmpCampos = tmpLinha.split(";");

                Cliente cliente = new Cliente();
                cliente.setId(Integer.parseInt(tmpCampos[0]));
                cliente.setNome(tmpCampos[1]);
                cliente.setIdentidade(tmpCampos[2]);
                cliente.setCpf(tmpCampos[3]);
                cliente.setEndereco(tmpCampos[4]);
                cliente.setTelefone(tmpCampos[5]);
                cliente.setDataNascimento(tmpCampos[6]);

                adicionarNaLista(cliente);
            }

            entrada.close();
        }
        catch(FileNotFoundException ex){
            System.err.println("Arquivo de clientes não encontrado");
            System.exit(1);
        }
    }

    public void salvar(Cliente cliente){
        try{
            FileWriter fileWriter = new FileWriter("arquivos/clientes", true);
            Formatter saida = new Formatter(fileWriter); 
            
            saida.format("%s;%s;%s;%s;%s;%s;%s\n",
                String.valueOf(cliente.getId()),
                cliente.getNome(),
                cliente.getIdentidade(),
                cliente.getCpf(),
                cliente.getEndereco(),
                cliente.getTelefone(),
                cliente.getDataNascimento());
            
            saida.close();
            
            adicionarNaLista(cliente);
        }
        catch(IOException ex){
            System.err.println("Não é possível salvar cliente");
            System.exit(1);
        }
    }
    
    public Cliente buscarPorId(int idCliente){
        for(int i=0;i<lista.size();i++){
            if(((Cliente)lista.get(i)).getId() == idCliente){
                return (Cliente)lista.get(i);
            }
        }
        return null;
    }
    
    public ArrayList<Cliente> buscarPorNome(String nome){
        ArrayList<Cliente> result = new ArrayList();
        for(int i=0;i<lista.size();i++){
            if(((Cliente)lista.get(i)).getNome().contains(nome)){
                result.add((Cliente)lista.get(i));
            }
        }
        return result;
    }
}
