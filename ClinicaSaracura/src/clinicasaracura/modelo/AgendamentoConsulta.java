/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.modelo;

import clinicasaracura.registro.AgendamentoConsultaRegistro;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JList;

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

    public ArrayList<AgendamentoConsulta> buscaPorCliente(Cliente cliente, AgendamentoConsultaRegistro regAgendamentoExames) {
        ArrayList<AgendamentoConsulta> result = new ArrayList();
        JList lstExames = new JList(new Vector<AgendamentoConsulta>(regAgendamentoExames.getListaRegistros()));

        for (int i = 0; i < lstExames.getModel().getSize(); i++) {

            if (((AgendamentoConsulta) lstExames.getModel().getElementAt(i)).getCliente().getId() == cliente.getId()) {
                result.add(((AgendamentoConsulta) lstExames.getModel().getElementAt(i)));
            }
        }
        return result;
    }

}
