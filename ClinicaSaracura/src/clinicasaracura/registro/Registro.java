/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.registro;

import java.util.ArrayList;

public abstract class Registro<T> {
    protected ArrayList<T> lista;
    
    public Registro() {
        lista = new ArrayList<>();
        lerArquivo();
    }    
    
    protected abstract void lerArquivo();
    
    public void adicionar(T objeto){
        lista.add(objeto);
    }
    
    public int getQuantidade(){
        return lista.size();
    }
    
    public ArrayList<T> getListaRegistros() {
        lerArquivo();
        return lista;
    }
            
    @Override
    public String toString() {
        String retorno = "";
        for(int i=0;i<lista.size();i++){
            retorno += lista.get(i).toString() + "\n\n";
        }
        return retorno;
    }
}
