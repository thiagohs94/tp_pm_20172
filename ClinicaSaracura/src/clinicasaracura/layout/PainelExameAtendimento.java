/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.layout;

import clinicasaracura.modelo.Exame;
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

public class PainelExameAtendimento extends JPanel{
    private TelaAgendamento telaPai;
    private JTextField txtNomeExame;
    private JButton btnBuscarExame;
    private JButton btnConfirmarExame;
    private JLabel lblNomeExame;
    private JLabel lblSelecionarExame;
    private JLabel lblNenhumResultado;
    private JTextArea txtInfoExame;
    private JList lstExames;
    private JPanel pnlBusca;
    private JPanel pnlBotoes;
    private ArrayList<Exame> arrayExames;
    
    public PainelExameAtendimento(TelaAgendamento telaPai, ArrayList<Exame> arrayExames) {
        super();
        this.arrayExames = arrayExames;
        this.telaPai = telaPai;
        
        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        setBorder(BorderFactory.createTitledBorder("Exame")); 
        setLayout(layout);
        
        txtNomeExame = new JTextField(20);
        lblNomeExame = new JLabel("Nome:");
        lblSelecionarExame = new JLabel("Selecione um exame para realizar um agendamento:");
        lblNenhumResultado = new JLabel("Nenhum resultado encontrado");
        txtInfoExame = new JTextArea();
        btnBuscarExame = new JButton("Buscar");
        btnConfirmarExame = new JButton("Confirmar Exame");
        
        lblNenhumResultado.setVisible(false);
        

        
        configurarListaExames();
        setarAcoesBotoes();
        
        pnlBusca = new JPanel();
        pnlBusca.add(lblNomeExame);
        pnlBusca.add(txtNomeExame);
        pnlBusca.add(btnBuscarExame);
        
        pnlBotoes = new JPanel();
        pnlBotoes.add(btnConfirmarExame);        
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pnlBusca)
                        .addComponent(lblNenhumResultado)
                        .addComponent(lblSelecionarExame)
                        .addComponent(lstExames)
                        .addComponent(txtInfoExame)
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
                .addComponent(lblSelecionarExame))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lstExames))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(txtInfoExame))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(pnlBotoes))
        );
        
        add(pnlBusca);
        add(lblNenhumResultado);
        add(lblSelecionarExame);
        add(lstExames);
        add(txtInfoExame);
        add(pnlBotoes);
    }
    
    private void configurarListaExames(){
        lstExames = new JList(new Vector<Exame>(arrayExames));
        lstExames.setVisibleRowCount(10);
        lstExames.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof Exame) {
                    ((JLabel) renderer).setText(((Exame) value).getNome());
                }
                return renderer;
            }
        });
        
        lstExames.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                txtInfoExame.setText(((Exame)lstExames.getSelectedValue()).toString());
            }
        });
    }
    
    public void setarAcoesBotoes(){
        btnBuscarExame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Exame> result = 
                    telaPai.buscarExame(txtNomeExame.getText());
                if(result.isEmpty()){
                    lblNenhumResultado.setVisible(true);
                    lstExames.setVisible(false);
                }
                else{
                    arrayExames = result;
                    lstExames.setListData(new Vector<Exame>(arrayExames));
                    lblNenhumResultado.setVisible(false);
                    lstExames.setVisible(true);
                }
            }
        });
        
        btnConfirmarExame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaPai.confirmarExame(((Exame)lstExames.getSelectedValue()));
            }
        });
    }
    
    
    
}

