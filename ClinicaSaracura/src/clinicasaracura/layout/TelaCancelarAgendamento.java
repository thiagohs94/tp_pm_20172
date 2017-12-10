/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.layout;

import clinicasaracura.modelo.Cliente;
import clinicasaracura.modelo.Exame;
import clinicasaracura.modelo.Medico;
import clinicasaracura.registro.AgendamentoConsultaRegistro;
import clinicasaracura.registro.AgendamentoExameRegistro;
import clinicasaracura.registro.ClienteRegistro;
import clinicasaracura.registro.EspecialidadeRegistro;
import clinicasaracura.registro.ExameRegistro;
import clinicasaracura.registro.MedicoRegistro;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author wanderson
 */
public class TelaCancelarAgendamento {
   
    private Cliente cliente; 
    
    private FrameSistema frame;
    TelaPrincipal telaPrincipal;

    private PainelClienteCancelCons pnlCliente;
    private PainelCancelarConsulta pnlConsulta;
    private PainelCancelarExame pnlExame;
  
    private GroupLayout layout;
    private JPanel pnlCampos;
    private JLabel lblTitulo;
    
    private JPanel pnlAtendimento;
    private JLabel lblAtendimento;
    private ButtonGroup groupAtendimento;
    private JRadioButton rbConsulta, rbExame;
    
    private JButton btnCancelar, btnTrocarCliente;    
    
    AgendamentoConsultaRegistro regAgendamentoConsultas;
    AgendamentoExameRegistro regAgendamentoExames;
    
    
    public TelaCancelarAgendamento(TelaPrincipal telaPrincipal, ClienteRegistro regClientes,
        EspecialidadeRegistro regEspecialidades,
        ExameRegistro regExames, MedicoRegistro regMedicos,
        AgendamentoConsultaRegistro regAgendamentoConsultas,
        AgendamentoExameRegistro regAgendamentoExames) {
        
        this.telaPrincipal = telaPrincipal;
        
        
        
        this.regAgendamentoConsultas = regAgendamentoConsultas;
        this.regAgendamentoExames = regAgendamentoExames;
         
        frame = new FrameSistema("Clínica Saracura - Cancelamento de Consultas e Exames");
        frame.setLayout(new GridBagLayout());
        
        lblTitulo = new JLabel("Escolha sua especialidade:");
        
        btnCancelar = new JButton("Voltar");
        btnTrocarCliente = new JButton("Alterar Cliente");
        
        pnlCliente = new PainelClienteCancelCons(this, regClientes);
        pnlConsulta = new PainelCancelarConsulta(this, regAgendamentoConsultas);
        pnlExame = new PainelCancelarExame(this, regAgendamentoExames);
        
        pnlAtendimento = new JPanel();
        pnlAtendimento.setBorder(BorderFactory.createTitledBorder("Cancelamento"));
        lblAtendimento = new JLabel("Escolha o tipo de atendimento: ");
        rbConsulta = new JRadioButton("Consulta");
        rbExame = new JRadioButton("Exame");
        rbConsulta.setSelected(true);
        
        groupAtendimento = new ButtonGroup();
        groupAtendimento.add(rbConsulta);
        groupAtendimento.add(rbExame);
        
        GroupLayout layout = new GroupLayout(pnlAtendimento);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        pnlAtendimento.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lblAtendimento)
                            .addComponent(rbConsulta)
                            .addComponent(rbExame)))
                        .addComponent(pnlConsulta)
                        .addComponent(pnlExame)
                        .addComponent(btnTrocarCliente)
                    )
                )
            )
        );
 
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblAtendimento)
                    .addComponent(rbConsulta)
                    .addComponent(rbExame)
                )
            )   
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(pnlConsulta))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(pnlExame))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btnTrocarCliente))
        );
        
        pnlAtendimento.add(lblAtendimento);
        pnlAtendimento.add(rbConsulta);
        pnlAtendimento.add(rbExame);
        pnlAtendimento.add(pnlConsulta);
        pnlAtendimento.add(pnlExame);
        pnlAtendimento.add(btnTrocarCliente);
        
        setarPainelAtendimentoHabilitado(false);
        pnlExame.setVisible(false);
        
        setarAcaoRadio();
        setarAcoesBotoes();
        
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0;
        frame.add(pnlCliente, c);
        
        
        
        c.gridx = 1;
        c.gridy = 0;
        c.weighty = 0;
        frame.add(pnlAtendimento, c);
        
        
        
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        frame.add(btnCancelar, c);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
    
    private void setarPainelAtendimentoHabilitado(boolean status){
        pnlAtendimento.setEnabled(status);
        lblAtendimento.setEnabled(status);
        rbConsulta.setEnabled(status);
        rbExame.setEnabled(status);
        pnlConsulta.setarHabilitado(status);
        pnlExame.setarHabilitado(status);
        btnTrocarCliente.setEnabled(status);
    }
    
    private void setarAcaoRadio(){
        rbConsulta.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                alterarTipoAtendimento();
            }
        });
        
        rbExame.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                alterarTipoAtendimento();
            }
        });
    }
    
    private void alterarTipoAtendimento(){
        if(rbConsulta.isSelected()){
            pnlConsulta.setVisible(true);
            pnlExame.setVisible(false);
        }
        else{
            pnlConsulta.setVisible(false);
            pnlExame.setVisible(true);
        }
    }
    
    
     private void setarAcoesBotoes(){
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaPrincipal.habilitar();
                destruir();
            }
        });
        
        btnTrocarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setarPainelAtendimentoHabilitado(false);
                pnlCliente.setarHabilitado(true);
            }
        });
     
    }
     
      private void destruir(){
        frame.dispose();
    }
    
      public void confirmarCliente(Cliente cliente){
          this.cliente = cliente;
          pnlExame.atualizarLista();
          pnlConsulta.atualizarLista();
          setarPainelAtendimentoHabilitado(true);
          pnlCliente.setarHabilitado(false);
          
      }
      
      public Cliente getCliente(){
          return this.cliente;
      }
      
      
    
      
      
      
}
