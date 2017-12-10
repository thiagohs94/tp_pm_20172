/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.registro;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

public class AutorizacaoRegistro {
    private int quantidadeCortesia;
    private int quantidadeConvenio;
    
    public AutorizacaoRegistro(){
        quantidadeCortesia = 0;
        quantidadeConvenio = 0;
        lerArquivo();
    }
    
    public void aumentaQuantidadeCortesia() {
        this.quantidadeCortesia ++;
    }
    
    public void aumentaQuantidadeConvenio() {
        this.quantidadeConvenio ++;
    }

    public int getQuantidadeCortesia() {
        return quantidadeCortesia;
    }

    public int getQuantidadeConvenio() {
        return quantidadeConvenio;
    }

    protected void lerArquivo(){
        String tmpLinha;
        String[] tmpCampos;
        Scanner entrada;
        try{
            entrada = new Scanner(new File("arquivos/autorizacao"));
            
            while(entrada.hasNext()){
                tmpLinha = entrada.nextLine();
                tmpCampos = tmpLinha.split(";");

                quantidadeCortesia = Integer.parseInt(tmpCampos[0]);
                quantidadeConvenio = Integer.parseInt(tmpCampos[1]);
            }

            entrada.close();
        }
        catch(FileNotFoundException ex){
            System.err.println("Arquivo de autorizacao não encontrado");
            System.exit(1);
        }
    }
    
    public void salvar(){
        try{
            FileWriter fileWriter = new FileWriter("arquivos/autorizacao", false);
            Formatter saida = new Formatter(fileWriter); 
            
            saida.format("%s;%s\n",
                String.valueOf(quantidadeCortesia),
                String.valueOf(quantidadeConvenio));
            
            saida.close();
        }
        catch(IOException ex){
            System.err.println("Não é possível salvar no arquivo de autorização");
            System.exit(1);
        }
    }
}
