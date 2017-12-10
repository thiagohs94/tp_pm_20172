/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.layout;

import clinicasaracura.modelo.Agendamento;
import clinicasaracura.modelo.AgendamentoConsulta;
import clinicasaracura.modelo.AgendamentoExame;
import clinicasaracura.modelo.Cliente;
import clinicasaracura.registro.AgendamentoConsultaRegistro;
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
public class PainelCancelarConsulta extends JPanel{
    
    private TelaCancelarAgendamento telaPai;
    private JButton btnConfirmarExame;
    private JLabel lblSelecionarExames;
    private JLabel lblNenhumResultado;
    private JTextArea txtInfoConsultas;
    private JList lstConsultas;
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
        
       
        lblSelecionarExames = new JLabel("Selecione uma consulta para cancelar:");
        lblNenhumResultado = new JLabel("Nenhum resultado encontrado");
        txtInfoConsultas = new JTextArea();
        btnConfirmarExame = new JButton("Cancelar Consulta");
        
        lblNenhumResultado.setVisible(false);
        btnConfirmarExame.setEnabled(false);

        
        configurarListaConsultas();
        setarAcoesBotoes();
        
        
        pnlBotoes = new JPanel();
        pnlBotoes.add(btnConfirmarExame);        
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
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
        
        add(lblNenhumResultado);
        add(lblSelecionarExames);
        add(lstConsultas);
        add(txtInfoConsultas);
        add(pnlBotoes);
    }
    
    private void configurarListaConsultas(){
        lstConsultas = new JList(new Vector<AgendamentoConsulta>(regAgendamentoConsultas.getListaRegistros()));
        lstConsultas.setVisibleRowCount(10);
        lstConsultas.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof AgendamentoConsulta) {
                    String texto = new String();
                    texto = "Dia: " + ((AgendamentoConsulta) value).getDia() + " Horário: " + Agendamento.getTextoHorario(((AgendamentoConsulta) value).getHorario());
                    ((JLabel) renderer).setText(  texto ) ;
                }
                return renderer;
            }
        });
        
        lstConsultas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(lstConsultas.getSelectedValue() != null){
                    txtInfoConsultas.setText( ((AgendamentoConsulta)lstConsultas.getSelectedValue()).toString());
                }
            }
        });
    }
    
    public void setarAcoesBotoes(){
        
        btnConfirmarExame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(lstConsultas.getSelectedValue() != null){
                    Object[] options = {"Sim", "Não"};

                    if ( JOptionPane.showOptionDialog(null, "Você quer cancelar essa consulta?",
                        "Cancelar Consulta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                         null, options, options[0]) == 0 ){
                        regAgendamentoConsultas.remover((AgendamentoConsulta)lstConsultas.getSelectedValue());
                        JOptionPane.showMessageDialog(null, "Consulta cancelada com sucesso");
                        atualizarLista(telaPai.getCliente());

                    }
                    else
                        JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Selecione uma consulta para cancelar","Erro", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
    }
    
    public void setarHabilitado(boolean status){
        btnConfirmarExame.setEnabled(status);
        lblSelecionarExames.setEnabled(status);
        lblNenhumResultado.setEnabled(status);
        txtInfoConsultas.setEnabled(status);
        lstConsultas.setEnabled(status);
    }    
    
   public void atualizarLista(Cliente cliente){
        ArrayList<AgendamentoConsulta> result = regAgendamentoConsultas.buscarPorCliente(cliente.getId());
        if(result.isEmpty()){
            lblNenhumResultado.setVisible(true);
            lstConsultas.setVisible(false);
            lblSelecionarExames.setVisible(false);
            txtInfoConsultas.setVisible(false);
            btnConfirmarExame.setEnabled(false);
        }
        else{
            lstConsultas.setListData(new Vector<AgendamentoConsulta>(result));
            lblNenhumResultado.setVisible(false);
            lstConsultas.setVisible(true);
            btnConfirmarExame.setEnabled(true);
            txtInfoConsultas.setVisible(true);
        }
        
    }
}
