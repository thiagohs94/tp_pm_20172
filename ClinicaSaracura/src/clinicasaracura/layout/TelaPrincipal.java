/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.layout;

import clinicasaracura.modelo.Cliente;
import clinicasaracura.registro.AgendamentoConsultaRegistro;
import clinicasaracura.registro.AgendamentoExameRegistro;
import clinicasaracura.registro.ClienteRegistro;
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
    JButton botaoMedicos, botaoClientes, botaoExames, botaoAdicionarCliente, botaoAgendamento, botaoCancelarAgendamento;
    JScrollPane scrollExames, scrollMedicos, scrollClientes;
    JTextArea texAreaExames, texAreaMedicos, texAreaClientes;
    
    ClienteRegistro regClientes;
    EspecialidadeRegistro regEspecialidades;
    ExameRegistro regExames;
    MedicoRegistro regMedicos;
    AgendamentoConsultaRegistro regAgendamentoConsultas;
    AgendamentoExameRegistro regAgendamentoExames;

    public TelaPrincipal(ClienteRegistro regClientes,
        EspecialidadeRegistro regEspecialidades,
        ExameRegistro regExames, MedicoRegistro regMedicos,
        AgendamentoConsultaRegistro regAgendamentoConsultas,
        AgendamentoExameRegistro regAgendamentoExames) {
        
        this.regClientes = regClientes;
        this.regEspecialidades = regEspecialidades;
        this.regExames = regExames;
        this.regMedicos = regMedicos;
        this.regAgendamentoConsultas = regAgendamentoConsultas;
        this.regAgendamentoExames = regAgendamentoExames;
        
        frame = new FrameSistema("Clínica Saracura");
        frame.setLayout(new FlowLayout());
        
        texAreaExames = new JTextArea();
        scrollExames = new JScrollPane(texAreaExames);
        texAreaExames.setLineWrap(true);  
        texAreaExames.setWrapStyleWord(true); 
        scrollExames.setPreferredSize( new Dimension( 500, 500 ) );
        
        texAreaMedicos = new JTextArea();
        scrollMedicos = new JScrollPane(texAreaMedicos);
        texAreaMedicos.setLineWrap(true);  
        texAreaMedicos.setWrapStyleWord(true); 
        scrollMedicos.setPreferredSize( new Dimension( 500, 500 ) );
        
        texAreaClientes = new JTextArea();
        scrollClientes = new JScrollPane(texAreaClientes);
        texAreaClientes.setLineWrap(true);  
        texAreaClientes.setWrapStyleWord(true); 
        scrollClientes.setPreferredSize( new Dimension( 500, 500 ) );
        
        botaoMedicos = new JButton("Listar Médicos");
        botaoClientes = new JButton("Listar Clientes");
        botaoExames = new JButton("Listar Exames");
        botaoAdicionarCliente = new JButton("Adicionar Cliente");
        botaoAgendamento = new JButton("Agendamento");
        botaoCancelarAgendamento = new JButton("Cancelar Agendamento");
        
        frame.add(botaoMedicos);
        frame.add(botaoClientes);
        frame.add(botaoExames);
        frame.add(botaoAdicionarCliente);
        frame.add(botaoAgendamento);
        frame.add(botaoCancelarAgendamento);
        
        setarAcoesBotoes();
        
        frame.setVisible(true);
    }  
    
    public void exibirCadastroCliente(){
        TelaCadastroCliente tela = new TelaCadastroCliente(this);
    }
    
    public void exibirAgendamento(){
        TelaAgendamento tela = new TelaAgendamento(this,
            regClientes,
            regEspecialidades,
            regExames,
            regMedicos,
            regAgendamentoConsultas,
            regAgendamentoExames);
    }
    
    public void exibirCancelarAgendamento(){
        TelaCancelarAgendamento tela = new TelaCancelarAgendamento(this,
            regExames,
            regMedicos,
            regAgendamentoConsultas,
            regAgendamentoExames);
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
        return (regEspecialidades.getQuantidade());
    }
    
    private void setarAcoesBotoes(){
        botaoMedicos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                texAreaMedicos.setText(regMedicos.toString());
                JOptionPane.showMessageDialog(null,
                    scrollMedicos,
                    "Clínica Saracura - Lista de Médicos",
                    JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        botaoExames.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                texAreaExames.setText(regExames.toString());
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
        
        botaoCancelarAgendamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setEnabled(false);
                exibirCancelarAgendamento();
            }
        });
        
    }
}
