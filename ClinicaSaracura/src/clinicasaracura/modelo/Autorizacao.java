/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.modelo;

import java.util.Random;


public class Autorizacao {
    
    private boolean autorizacao;
    private int cortesia;
    private int convenio;
    
    public Autorizacao(){
        this.autorizacao = false;
        this.cortesia = 0;
        this.convenio = 0;
    }
    
    public boolean autorizaDinheiro(){
        return true;
    }
    
    public boolean autorizaCartao(){
        return true;
    }
    
    public boolean autorizaCortesia(){
        if (this.cortesia == 0 || this.cortesia % 5 != 0)
            this.autorizacao = true;
        
        return this.autorizacao;
    }
    
    public boolean autorizaCheque(){
        Random rand = new Random();
        
        if (rand.nextInt(2) == 1)
            this.autorizacao = true;
        
        return this.autorizacao;
    }
    
    public boolean autorizaConvenio(){
        Random rand = new Random();
        
        if (this.convenio == 0 || this.convenio % 10 != 0)
            this.autorizacao = true;
        
        else
            if (rand.nextInt(2) == 1)
                this.autorizacao = true;
        
        return this.autorizacao;
    }
    
}
