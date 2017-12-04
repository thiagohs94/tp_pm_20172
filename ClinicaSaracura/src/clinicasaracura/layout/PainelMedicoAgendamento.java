/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.layout;

import clinicasaracura.modelo.Medico;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PainelMedicoAgendamento extends JPanel{
    private TelaAgendamento telaPai;
    private JTextField txtNomeMedico;
    private JButton btnBuscarMedico;
    private JButton btnConfirmarMedico;
    private JLabel lblNomeMedico;
    private JLabel lblSelecionarMedico;
    private JLabel lblNenhumResultado;
    private JTextArea txtInfoMedico;
    private JList lstMedicos;
    private JPanel pnlBusca;
    private JPanel pnlBotoes;
    private ArrayList<Medico> arrayMedicos;
    
    public PainelMedicoAgendamento(TelaAgendamento telaPai, ArrayList<Medico> arrayMedicos) {
        super();
        this.arrayMedicos = arrayMedicos;
        this.telaPai = telaPai;
        
        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        setBorder(BorderFactory.createTitledBorder("Médico")); 
        setLayout(layout);
        
        txtNomeMedico = new JTextField(20);
        lblNomeMedico = new JLabel("Nome:");
        lblSelecionarMedico = new JLabel("Selecione um médico para realizar um agendamento:");
        lblNenhumResultado = new JLabel("Nenhum resultado encontrado");
        txtInfoMedico = new JTextArea();
        btnBuscarMedico = new JButton("Buscar");
        btnConfirmarMedico = new JButton("Confirmar Médico");
        
        lblNenhumResultado.setVisible(false);
        

        
        configurarListaMedicos();
        setarAcoesBotoes();
        
        pnlBusca = new JPanel();
        pnlBusca.add(lblNomeMedico);
        pnlBusca.add(txtNomeMedico);
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
                .addComponent(txtInfoMedico))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(pnlBotoes))
        );
        
        add(pnlBusca);
        add(lblNenhumResultado);
        add(lblSelecionarMedico);
        add(lstMedicos);
        add(txtInfoMedico);
        add(pnlBotoes);
    }
    
    private void configurarListaMedicos(){
        lstMedicos = new JList(new Vector<Medico>(arrayMedicos));
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
                txtInfoMedico.setText(((Medico)lstMedicos.getSelectedValue()).toString());
            }
        });
    }
    
    public void setarAcoesBotoes(){
        btnBuscarMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Medico> result = 
                    telaPai.buscarMedico(txtNomeMedico.getText());
                if(result.isEmpty()){
                    lblNenhumResultado.setVisible(true);
                    lstMedicos.setVisible(false);
                }
                else{
                    arrayMedicos = result;
                    lstMedicos.setListData(new Vector<Medico>(arrayMedicos));
                    lblNenhumResultado.setVisible(false);
                    lstMedicos.setVisible(true);
                }
            }
        });
        
        btnConfirmarMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaPai.confirmarMedico();
            }
        });
    }
    
    
    
}