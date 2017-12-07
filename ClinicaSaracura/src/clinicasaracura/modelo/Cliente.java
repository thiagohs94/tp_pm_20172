/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.modelo;

public class Cliente extends Pessoa{
    public Cliente(int id, String nome, String identidade, String cpf, String endereco, String telefone, String dataNascimento) {
        super(id, nome, identidade, cpf, endereco, telefone, dataNascimento);
    }
    
    public Cliente(int id){
        super (id);
    }
    
    public Cliente(){
        super();
    }
    
}
