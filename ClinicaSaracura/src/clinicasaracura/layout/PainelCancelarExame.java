/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.layout;

import clinicasaracura.modelo.Agendamento;
import clinicasaracura.modelo.AgendamentoExame;
import clinicasaracura.modelo.Cliente;
import clinicasaracura.registro.AgendamentoExameRegistro;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author wanderson
 */
public class PainelCancelarExame extends JPanel {
    
    private TelaCancelarAgendamento telaPai;
    private JButton btnConfirmarExame;
    private JLabel lblSelecionarExames;
    private JLabel lblNenhumResultado;
    private JTextArea txtInfoExame;
    private JList lstExames;
    private JPanel pnlBotoes;
    private AgendamentoExameRegistro regAgendamentoExames;
    
    
    public PainelCancelarExame(TelaCancelarAgendamento telaPai, AgendamentoExameRegistro regAgendamentoExames) {
        super();
        this.regAgendamentoExames = regAgendamentoExames;
        this.telaPai = telaPai;
        
        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        setBorder(BorderFactory.createTitledBorder("Exame")); 
        setLayout(layout);
        
        lblSelecionarExames = new JLabel("Selecione um exame para cancelar:");
        lblNenhumResultado = new JLabel("Nenhum resultado encontrado");
        txtInfoExame = new JTextArea();
        btnConfirmarExame = new JButton("Cancelar Exame");
        
        lblNenhumResultado.setVisible(false);
        btnConfirmarExame.setEnabled(false);

        
        configurarListaExames();
        setarAcoesBotoes();
        
        pnlBotoes = new JPanel();
        pnlBotoes.add(btnConfirmarExame);        
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblNenhumResultado)
                        .addComponent(lblSelecionarExames)
                        .addComponent(lstExames)
                        .addComponent(txtInfoExame)
                        .addComponent(pnlBotoes)
                    )
                )
            )
        );
 
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblNenhumResultado))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblSelecionarExames))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lstExames))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(txtInfoExame))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(pnlBotoes))
        );
        
        add(lblNenhumResultado);
        add(lblSelecionarExames);
        add(lstExames);
        add(txtInfoExame);
        add(pnlBotoes);
    }
    
    private void configurarListaExames(){
        
        lstExames = new JList(new Vector<AgendamentoExame>(regAgendamentoExames.getListaRegistros()));
        lstExames.setVisibleRowCount(10);
        lstExames.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof AgendamentoExame) {
                    String texto = new String();
                    texto = "Dia: " + ((AgendamentoExame) value).getDia() + " Horário: " + Agendamento.getTextoHorario(((AgendamentoExame) value).getHorario());
                    ((JLabel) renderer).setText(  texto ) ;
                }
                return renderer;
            }
        });
        
        lstExames.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(lstExames.getSelectedValue() != null){
                    txtInfoExame.setText( ((AgendamentoExame)lstExames.getSelectedValue()).toString());
                }
            }
        });
    }
    
    public void setarAcoesBotoes(){
        
        btnConfirmarExame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(lstExames.getSelectedValue() != null){
                    Object[] options = {"Sim", "Não"};

                    if ( JOptionPane.showOptionDialog(null, "Você quer cancelar essa consulta?",
                        "Cancelar Consulta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                         null, options, options[0]) == 0 ){

                    regAgendamentoExames.remover((AgendamentoExame)lstExames.getSelectedValue());
                    JOptionPane.showMessageDialog(null, "Consulta cancelada com sucesso");
                    atualizarLista(telaPai.getCliente());

                    }
                    else
                        JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Selecione um exame para cancelar","Erro", JOptionPane.ERROR_MESSAGE);
                }
                
                
                
            }  
        });
    }
    
    public void setarHabilitado(boolean status){
        btnConfirmarExame.setEnabled(status);
        lblSelecionarExames.setEnabled(status);
        lblNenhumResultado.setEnabled(status);
        txtInfoExame.setEnabled(status);
        lstExames.setEnabled(status);
    }
    
    public void atualizarLista(Cliente cliente){
        
        ArrayList<AgendamentoExame> result = regAgendamentoExames.buscarPorCliente(cliente.getId());
        if(result.isEmpty()){
            lblNenhumResultado.setVisible(true);
            lstExames.setVisible(false);
            lblSelecionarExames.setVisible(false);
            txtInfoExame.setVisible(false);
            btnConfirmarExame.setEnabled(false);
        }
        else{
            lstExames.setListData(new Vector<AgendamentoExame>(result));
            lblNenhumResultado.setVisible(false);
            lstExames.setVisible(true);
            btnConfirmarExame.setEnabled(true);
            txtInfoExame.setVisible(true);
        }
        
    }
    
    
}
