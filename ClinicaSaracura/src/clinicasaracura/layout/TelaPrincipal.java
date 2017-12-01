/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.layout;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author eliane
 */
public class TelaPrincipal {
    FrameSistema frame;
    JButton botaoMedicos, botaoClientes, botaoExames, botaoAgendamento;

    public TelaPrincipal() {
        frame = new FrameSistema("Clínica Saracura");
        frame.setLayout(new FlowLayout());
        
        
        botaoMedicos = new JButton("Médicos");
        botaoClientes = new JButton("Clientes");
        botaoExames = new JButton("Exames");
        botaoAgendamento = new JButton("Agendamento");
        
        frame.add(botaoMedicos);
        frame.add(botaoClientes);
        frame.add(botaoExames);
        frame.add(botaoAgendamento);
        
        setarAcoesBotoes();
        
        frame.setVisible(true);
    }  
    
    public void setarAcoesBotoes(){
        botaoMedicos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        botaoClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        botaoExames.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        botaoAgendamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
}
