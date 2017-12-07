/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.modelo;

public class Medico extends Pessoa{
    private Especialidade especialidade;

    public Medico(int id, String nome, String identidade, String cpf, String endereco, String telefone, String dataNascimento, Especialidade especialidade) {
        super(id, nome, identidade, cpf, endereco, telefone, dataNascimento);
        this.especialidade = especialidade;
    }
    
    public Medico(int id){
        super(id);
    }
    
    public Medico(){
        super();
    }
    
    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return super.toString() + "\nEspecialidade: " + especialidade.getNome();
    }
}