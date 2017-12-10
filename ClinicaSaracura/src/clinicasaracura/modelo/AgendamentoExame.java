/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.modelo;

import clinicasaracura.registro.AgendamentoExameRegistro;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JList;

public class AgendamentoExame extends Agendamento{
    private Exame exame;
    
    public AgendamentoExame(int dia, int horario, Cliente cliente, Exame exame, int tipo) {
        super(dia, horario, cliente, tipo);
        this.exame = exame;
    }
    
    public AgendamentoExame(){
        
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }
    
    
    public ArrayList<AgendamentoExame> buscaPorCliente( Cliente cliente, AgendamentoExameRegistro regAgendamentoExames){
        ArrayList<AgendamentoExame> result = new ArrayList();
        JList lstExames = new JList(new Vector<AgendamentoExame>(regAgendamentoExames.getListaRegistros()));
        
        for(int i=0; i< lstExames.getModel().getSize() ;i++){
            
            if( ((AgendamentoExame)lstExames.getModel().getElementAt(i)).getCliente().getId() == cliente.getId() ){
                result.add( ((AgendamentoExame)lstExames.getModel().getElementAt(i)) );
            }
        }
        return result;
    }
    
}
