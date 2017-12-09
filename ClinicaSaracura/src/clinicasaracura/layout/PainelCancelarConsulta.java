/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.layout;

import clinicasaracura.modelo.Exame;
import clinicasaracura.registro.AgendamentoConsultaRegistro;
import clinicasaracura.registro.ExameRegistro;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author wanderson
 */
public class PainelCancelarConsulta extends JPanel{
    
    private TelaCancelarAgendamento telaPai;
    private JTextField txtNomeCliente;
    private JButton btnBuscarExame;
    private JButton btnConfirmarExame;
    private JLabel lblNomeCliente;
    private JLabel lblSelecionarExames;
    private JLabel lblNenhumResultado;
    private JTextArea txtInfoConsultas;
    private JList lstConsultas;
    private JPanel pnlBusca;
    private JPanel pnlBotoes;
    private AgendamentoConsultaRegistro regAgendamentoConsultas;
    
    
    public PainelCancelarConsulta(TelaCancelarAgendamento telaPai, AgendamentoConsultaRegistro regAgendamentoConsultas) {
        super();
        this.regAgendamentoConsultas = regAgendamentoConsultas;
        this.telaPai = telaPai;
        
        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        setBorder(BorderFactory.createTitledBorder("Consulta")); 
        setLayout(layout);
        
        txtNomeCliente = new JTextField(20);
        lblNomeCliente = new JLabel("Nome do cliente:");
        lblSelecionarExames = new JLabel("Selecione uma consulta para cancelar:");
        lblNenhumResultado = new JLabel("Nenhum resultado encontrado");
        txtInfoConsultas = new JTextArea();
        btnBuscarExame = new JButton("Buscar");
        btnConfirmarExame = new JButton("Confirmar Exame");
        
        lblNenhumResultado.setVisible(false);
        

        
        configurarListaExames();
        setarAcoesBotoes();
        
        pnlBusca = new JPanel();
        pnlBusca.add(lblNomeCliente);
        pnlBusca.add(txtNomeCliente);
        pnlBusca.add(btnBuscarExame);
        
        pnlBotoes = new JPanel();
        pnlBotoes.add(btnConfirmarExame);        
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pnlBusca)
                        .addComponent(lblNenhumResultado)
                        .addComponent(lblSelecionarExames)
                        .addComponent(lstConsultas)
                        .addComponent(txtInfoConsultas)
                        .addComponent(pnlBotoes)
                    )
                )
            )
        );
 
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(pnlBusca))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblNenhumResultado))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblSelecionarExames))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lstConsultas))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(txtInfoConsultas))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(pnlBotoes))
        );
        
        add(pnlBusca);
        add(lblNenhumResultado);
        add(lblSelecionarExames);
        add(lstConsultas);
        add(txtInfoConsultas);
        add(pnlBotoes);
    }
    
    private void configurarListaExames(){
        lstConsultas = new JList(new Vector<AgendamentoConsultaRegistro>(regAgendamentoConsultas.getListaRegistros()));
        lstConsultas.setVisibleRowCount(10);
        lstConsultas.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof Exame) {
                    ((JLabel) renderer).setText(((Exame) value).getNome());
                }
                return renderer;
            }
        });
        
        lstConsultas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(lstConsultas.getSelectedValue() != null){
                    txtInfoConsultas.setText(((Exame)lstConsultas.getSelectedValue()).toString());
                }
            }
        });
    }
    
    public void setarAcoesBotoes(){
        btnBuscarExame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*ArrayList<Exame> result = regAgendamentoConsultas.buscarPorNome(txtNomeCliente.getText());
                if(result.isEmpty()){
                    lblNenhumResultado.setVisible(true);
                    regAgendamentoConsultas.setVisible(false);
                }
                else{
                    regAgendamentoConsultas.setListData(new Vector<Exame>(result));
                    lblNenhumResultado.setVisible(false);
                    regAgendamentoConsultas.setVisible(true);
                }*/
            }
        });
        
        
        btnConfirmarExame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*if(lstExames.getSelectedValue() != null){
                    telaPai.confirmarCliente(((Exame)regExames.getSelectedValue()));
                }
                else{
                    JOptionPane.showMessageDialog(null, "Selecione um dos clientes na lista",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                }*/
            }
        });
    }
    
    public void setarHabilitado(boolean status){
        txtNomeCliente.setEnabled(status);
        btnBuscarExame.setEnabled(status);
        btnConfirmarExame.setEnabled(status);
        lblNomeCliente.setEnabled(status);
        lblSelecionarExames.setEnabled(status);
        lblNenhumResultado.setEnabled(status);
        txtInfoConsultas.setEnabled(status);
        lstConsultas.setEnabled(status);
    }
    
    
   
    
}
