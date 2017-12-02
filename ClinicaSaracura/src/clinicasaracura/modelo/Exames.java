/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.modelo;

public class Exames {
    
    private int id;
    private String medico;
    private String especialidade;
    private String data;
    private String horario;
    
    public Exames(){
    }
    
    public Exames(String medico, String especialidade) {
        this.medico = medico;
        this.especialidade = especialidade;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Id: " + id + "\nMédico:" + medico + "\nEspecialidade:" + especialidade + "\nData: " + data + "\nHorario:" + horario;
    }
}
