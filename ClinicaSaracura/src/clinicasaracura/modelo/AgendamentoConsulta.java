/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.modelo;

public class AgendamentoConsulta extends Agendamento {
    private Medico medico;
    
    public AgendamentoConsulta(int dia, int horario, Cliente cliente, int tipo) {
        super(dia, horario, cliente, tipo);
    }

    public AgendamentoConsulta() {
        super();
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
