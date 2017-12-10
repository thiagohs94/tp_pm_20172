/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.layout;

import clinicasaracura.modelo.Especialidade;
import clinicasaracura.modelo.Medico;
import clinicasaracura.registro.EspecialidadeRegistro;
import clinicasaracura.registro.MedicoRegistro;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PainelMedicoAtendimento extends JPanel{
    private TelaAgendamento telaPai;
    private JComboBox cbEspecialidadeMedico;
    private JButton btnBuscarMedico;
    private JButton btnConfirmarMedico;
    private JLabel lblNomeEspecialidade;
    private JLabel lblSelecionarMedico;
    private JLabel lblNenhumResultado;
    private JLabel lblInfoMedico;
    private JTextArea txtInfoMedico;
    private JList lstMedicos;
    private JPanel pnlBusca;
    private JPanel pnlBotoes;
    
    private MedicoRegistro regMedicos;
    private EspecialidadeRegistro regEspecialidades;
    
    public PainelMedicoAtendimento(TelaAgendamento telaPai,
        MedicoRegistro regMedicos,
        EspecialidadeRegistro regEspecialidades) {
        super();
        this.regMedicos = regMedicos;
        this.regEspecialidades = regEspecialidades;
        this.telaPai = telaPai;
        
        
        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        setBorder(BorderFactory.createTitledBorder("Médico")); 
        setLayout(layout);
        
        
        lblNomeEspecialidade = new JLabel("Especialidade:");
        lblSelecionarMedico = new JLabel("Selecione um médico para realizar um agendamento:");
        lblNenhumResultado = new JLabel("Nenhum resultado encontrado");
        lblInfoMedico = new JLabel("Informações do Médico:");
        txtInfoMedico = new JTextArea();
        btnBuscarMedico = new JButton("Buscar");
        btnConfirmarMedico = new JButton("Confirmar Médico");
        
        lblNenhumResultado.setVisible(false);
        

        configurarComboBoxEspecialidades();
        configurarListaMedicos();
        setarAcoesBotoes();
        
        pnlBusca = new JPanel();
        pnlBusca.add(lblNomeEspecialidade);
        pnlBusca.add(cbEspecialidadeMedico);
        pnlBusca.add(btnBuscarMedico);
        
        pnlBotoes = new JPanel();
        pnlBotoes.add(btnConfirmarMedico);        
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pnlBusca)
                        .addComponent(lblNenhumResultado)
                        .addComponent(lblSelecionarMedico)
                        .addComponent(lstMedicos)
                        .addComponent(lblInfoMedico)
                        .addComponent(txtInfoMedico)
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
                .addComponent(lblSelecionarMedico))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lstMedicos))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblInfoMedico))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(txtInfoMedico))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(pnlBotoes))
        );
        
        add(pnlBusca);
        add(lblNenhumResultado);
        add(lblSelecionarMedico);
        add(lstMedicos);
        add(lblInfoMedico);
        add(txtInfoMedico);
        add(pnlBotoes);
    }
    
    private void configurarComboBoxEspecialidades(){
        cbEspecialidadeMedico = new JComboBox(new Vector<Especialidade> (regEspecialidades.getListaRegistros()));
        cbEspecialidadeMedico.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof Especialidade) {
                    ((JLabel) renderer).setText(((Especialidade) value).getNome());
                }
                return renderer;
            }
        });
        
    }
    
    private void configurarListaMedicos(){
        lstMedicos = new JList(new Vector<Medico>(regMedicos.getListaRegistros()));
        lstMedicos.setVisibleRowCount(10);
        lstMedicos.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof Medico) {
                    ((JLabel) renderer).setText(((Medico) value).getNome());
                }
                return renderer;
            }
        });
        
        lstMedicos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(lstMedicos.getSelectedValue() != null){
                    txtInfoMedico.setText(((Medico)lstMedicos.getSelectedValue()).toString());
                }
            }
        });
    }
    
    public void setarAcoesBotoes(){
        btnBuscarMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Medico> result = regMedicos.buscarPorEspecialiade(cbEspecialidadeMedico.getSelectedIndex()+1);
                if(result.isEmpty()){
                lblNenhumResultado.setVisible(true);
                    lstMedicos.setVisible(false);
                }
                else{

                    lstMedicos.setListData(new Vector<Medico>(result));
                    lblNenhumResultado.setVisible(false);
                    lstMedicos.setVisible(true);
                }
            }
        });
        
        btnConfirmarMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lstMedicos.getSelectedValue() != null){
                    telaPai.confirmarMedico(((Medico)lstMedicos.getSelectedValue()));
                }
                else{
                    JOptionPane.showMessageDialog(null, "Selecione um dos médicos na lista",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    public void setarHabilitado(boolean status){
        setEnabled(status);
        cbEspecialidadeMedico.setEnabled(status);
        btnBuscarMedico.setEnabled(status);
        btnConfirmarMedico.setEnabled(status);
        lblNomeEspecialidade.setEnabled(status);
        lblSelecionarMedico.setEnabled(status);
        lblNenhumResultado.setEnabled(status);
        lblInfoMedico.setEnabled(status);
        txtInfoMedico.setEnabled(status);
        lstMedicos.setEnabled(status);
    }
    
    
}