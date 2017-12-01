/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura;

import clinicasaracura.layout.TelaPrincipal;
import clinicasaracura.registro.MedicoRegistro;
import clinicasaracura.registro.ExamesRegistro;

public class ClinicaSaracura {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TelaPrincipal tela = new TelaPrincipal();
        MedicoRegistro reg = new MedicoRegistro();
        ExamesRegistro exam = new ExamesRegistro();
        
        exam.getListaRegistros();
        reg.getListaMedicos();
    }
    
}
