/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.modelo;



public class Especialidade {
    
    private int id;
    private String especialidade;
    
    public Especialidade() {
    }

    public Especialidade(int id, String especialidade) {
        this.id = id;
        this.especialidade = especialidade;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    
    @Override
    public String toString() {
        return especialidade;
    }
    
}
