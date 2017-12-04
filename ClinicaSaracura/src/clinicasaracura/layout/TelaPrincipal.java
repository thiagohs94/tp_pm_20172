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
import clinicasaracura.registro.EspecialidadeRegistro;
import clinicasaracura.registro.ExameRegistro;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class TelaPrincipal {
    FrameSistema frame;
    JButton botaoMedicos, botaoClientes, botaoExames, botaoAdicionarCliente, botaoAgendamento;
    JScrollPane scrollExames, scrollMedicos, scrollClientes;
    JTextArea texAreaExames, texAreaMedicos, texAreaClientes;
    MedicoRegistro regMedicos;
    ClienteRegistro regClientes;
    ExamesRegistro regExames;
    ExameRegistro regExame;
    EspecialidadeRegistro regEspecialidade;

    public TelaPrincipal() {
        regMedicos = new MedicoRegistro();
        regClientes = new ClienteRegistro();
        regExames = new ExamesRegistro();
        regExame = new ExameRegistro();
        regEspecialidade = new EspecialidadeRegistro();
        
        frame = new FrameSistema("Clínica Saracura");
        frame.setLayout(new FlowLayout());
        
        texAreaExames = new JTextArea(regExames.toString());
        scrollExames = new JScrollPane(texAreaExames);
        texAreaExames.setLineWrap(true);  
        texAreaExames.setWrapStyleWord(true); 
        scrollExames.setPreferredSize( new Dimension( 500, 500 ) );
        
        texAreaMedicos = new JTextArea(regMedicos.toString());
        scrollMedicos = new JScrollPane(texAreaMedicos);
        texAreaMedicos.setLineWrap(true);  
        texAreaMedicos.setWrapStyleWord(true); 
        scrollMedicos.setPreferredSize( new Dimension( 500, 500 ) );
        
        texAreaClientes = new JTextArea(regClientes.toString());
        scrollClientes = new JScrollPane(texAreaClientes);
        texAreaClientes.setLineWrap(true);  
        texAreaClientes.setWrapStyleWord(true); 
        scrollClientes.setPreferredSize( new Dimension( 500, 500 ) );
        
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
    
    public void exibirAgendamento(){
        TelaAgendamento tela = new TelaAgendamento(this,
            regClientes.getListaRegistros(),
            regMedicos.getListaRegistros(),
            regExame.getListaRegistros(),
            regEspecialidade.getListaRegistros());
    }
    
    public void habilitar(){
        frame.setEnabled(true);
    }
    
    public void salvarCliente(Cliente cliente){
        regClientes.salvar(cliente);
    }
    
    public int proxIdCliente(){
        return (regClientes.getQuantidade() +1);
    }
    
    
    public int quantEspecialidades(){
        return (regEspecialidade.getQuantidade());
    }
    
    private void setarAcoesBotoes(){
        botaoMedicos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                    scrollMedicos,
                    "Clínica Saracura - Lista de Médicos",
                    JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        botaoExames.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showMessageDialog(null,
                    scrollExames,
                    "Clínica Saracura - Lista de Exames",
                    JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        botaoClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                texAreaClientes.setText(regClientes.toString());
                JOptionPane.showMessageDialog(null,
                    scrollClientes,
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
                frame.setEnabled(false);
                exibirAgendamento();
            }
        });
    }
}
