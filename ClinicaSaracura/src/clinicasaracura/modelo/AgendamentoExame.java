/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.modelo;

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
    
    @Override
    public String toString() {
        return super.toString() + "\nExame: " + exame.getNome();
    }
    
}
