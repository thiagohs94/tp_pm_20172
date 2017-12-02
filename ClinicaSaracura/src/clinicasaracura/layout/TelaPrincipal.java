/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.layout;

import clinicasaracura.modelo.Cliente;
import clinicasaracura.registro.ClienteRegistro;
import clinicasaracura.registro.ExamesRegistro;
import clinicasaracura.registro.MedicoRegistro;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class TelaPrincipal {
    FrameSistema frame;
    JButton botaoMedicos, botaoClientes, botaoExames, botaoAdicionarCliente, botaoAgendamento;
    MedicoRegistro regMedicos;
    ClienteRegistro regClientes;
    ExamesRegistro regExames;

    public TelaPrincipal() {
        regMedicos = new MedicoRegistro();
        regClientes = new ClienteRegistro();
        regExames = new ExamesRegistro();
        
        frame = new FrameSistema("Clínica Saracura");
        frame.setLayout(new FlowLayout());
        
        botaoMedicos = new JButton("Médicos");
        botaoClientes = new JButton("Clientes");
        botaoExames = new JButton("Exames");
        botaoAdicionarCliente = new JButton("Adicionar Cliente");
        botaoAgendamento = new JButton("Agendamento");
        
        frame.add(botaoMedicos);
        frame.add(botaoClientes);
        frame.add(botaoExames);
        frame.add(botaoAdicionarCliente);
        frame.add(botaoAgendamento);
        
        setarAcoesBotoes();
        
        frame.setVisible(true);
    }  
    
    public void exibirCadastroCliente(){
        TelaCadastroCliente tela = new TelaCadastroCliente(this);
    }
    
    public void habilitar(){
        frame.setEnabled(true);
    }
    
    public void salvarCliente(Cliente cliente){
        regClientes.salvar(cliente);
    }
    
    private void setarAcoesBotoes(){
        botaoMedicos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                    regMedicos.toString(),
                    "Clínica Saracura - Lista de Médicos",
                    JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        botaoExames.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showMessageDialog(null,
                    regClientes.toString(),
                    "Clínica Saracura - Lista de Exames",
                    JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        botaoClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                    regClientes.toString(),
                    "Clínica Saracura - Lista de Clientes",
                    JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        botaoAdicionarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setEnabled(false);
                exibirCadastroCliente();
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
