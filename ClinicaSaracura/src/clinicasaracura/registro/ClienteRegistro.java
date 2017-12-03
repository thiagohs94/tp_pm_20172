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
import java.util.Formatter;
import java.util.Scanner;

public class ClienteRegistro extends Registro {
    Scanner entrada;
    Formatter saida;
    
    public ClienteRegistro() {
        super();
    }           
    
    protected void lerArquivo(){
        String tmpLinha;
        String[] tmpCampos;
        
        try{
            entrada = new Scanner(new File("arquivos/clientes"));
        }
        catch(FileNotFoundException ex){
            System.err.println("Arquivo de clientes não encontrado");
            System.exit(1);
        }
        
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

    public void salvar(Cliente cliente){
        try{
            FileWriter fileWriter = new FileWriter("arquivos/clientes", true);
            saida = new Formatter(fileWriter); 
            
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
}