/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura;

import clinicasaracura.layout.TelaCadastroCliente;
import clinicasaracura.layout.TelaPrincipal;
import clinicasaracura.registro.AgendamentoConsultaRegistro;
import clinicasaracura.registro.AgendamentoExameRegistro;
import clinicasaracura.registro.ClienteRegistro;
import clinicasaracura.registro.EspecialidadeRegistro;
import clinicasaracura.registro.ExameRegistro;
import clinicasaracura.registro.MedicoRegistro;

public class ClinicaSaracura {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //carregar todos os registros
        ClienteRegistro regClientes = new ClienteRegistro();
        EspecialidadeRegistro regEspecialidades = new EspecialidadeRegistro();
        ExameRegistro regExames = new ExameRegistro();
        MedicoRegistro regMedicos = new MedicoRegistro(regEspecialidades);
        AgendamentoConsultaRegistro regAgendamentoConsultas = new AgendamentoConsultaRegistro(regClientes, regMedicos);
        AgendamentoExameRegistro regAgendamentoExames = new AgendamentoExameRegistro(regClientes, regExames);
        
        TelaPrincipal tela = new TelaPrincipal(regClientes,regEspecialidades,regExames,regMedicos,regAgendamentoConsultas,regAgendamentoExames);
    }
    
}
