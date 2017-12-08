/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.layout;

import clinicasaracura.modelo.Agendamento;
import clinicasaracura.modelo.AgendamentoExame;
import clinicasaracura.registro.AgendamentoConsultaRegistro;
import clinicasaracura.registro.AgendamentoExameRegistro;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PainelFinalizacaoAgendamento extends JPanel {
    public static final int RECURSO_MEDICO = 1;
    public static final int RECURSO_EXAME = 2;
    
    private TelaAgendamento telaPai;
    private JLabel lblDia;
    private JLabel lblHorario;
    private JLabel lblTipoAtendimento;
    private JLabel lblTipoPagamento;
    
    private JComboBox cbDias;
    private JComboBox cbHorarios;
    private JComboBox cbTipoAtendimento;
    private JComboBox cbTipoPagamento;
    
    private JButton btnSalvarAgendamento;
    
    private int diaSelecionado;
    private int horarioSelecionado;
    private int idRecurso; //medico ou exame
    private int tipoRecurso;
    
    private AgendamentoConsultaRegistro regAgendamentoConsulta;
    private AgendamentoExameRegistro regAgendamentoExame;

    public PainelFinalizacaoAgendamento(TelaAgendamento telaPai,
        AgendamentoConsultaRegistro regAgendamentoConsulta,
        AgendamentoExameRegistro regAgendamentoExame) {
        this.telaPai = telaPai;
        this.regAgendamentoConsulta = regAgendamentoConsulta;
        this.regAgendamentoExame = regAgendamentoExame;
        
        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        setBorder(BorderFactory.createTitledBorder("Horário e Tipo de Atendimento")); 
        setLayout(layout);
        
        diaSelecionado = 1;
        horarioSelecionado = 1;
        idRecurso = -1;
        tipoRecurso = -1;
        
        lblDia = new JLabel("Dia:");
        lblHorario = new JLabel("Horário:");
        lblTipoAtendimento = new JLabel("Tipo de Atendimento:");
        lblTipoPagamento = new JLabel("Tipo de Pagamento:");
        configurarComboBoxes();
        
        btnSalvarAgendamento = new JButton("Salvar Agendamento");
        btnSalvarAgendamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tipoRecurso == PainelFinalizacaoAgendamento.RECURSO_MEDICO
                    && regAgendamentoConsulta.buscarPorMedicoDiaHorario(idRecurso,
                        (int)cbDias.getSelectedItem(),
                        (int)cbHorarios.getSelectedItem()) == null){
                    
                    telaPai.salvarAgendamento((int)cbDias.getSelectedItem(),
                        (int)cbHorarios.getSelectedItem(),
                        (int)cbTipoAtendimento.getSelectedItem(),
                        (int)cbTipoPagamento.getSelectedItem());
                }
                else if(tipoRecurso == PainelFinalizacaoAgendamento.RECURSO_EXAME
                    && regAgendamentoExame.buscarPorExameDiaHorario(idRecurso,
                        (int)cbDias.getSelectedItem(),
                        (int)cbHorarios.getSelectedItem()) == null){
                    
                    telaPai.salvarAgendamento((int)cbDias.getSelectedItem(),
                        (int)cbHorarios.getSelectedItem(),
                        (int)cbTipoAtendimento.getSelectedItem(),
                        (int)cbTipoPagamento.getSelectedItem());
                }
                else{
                    JOptionPane.showMessageDialog(null, "Selecione um dos horários disponíveis para o atendimento",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblDia)
                        .addComponent(lblHorario)
                        .addComponent(lblTipoAtendimento)
                        .addComponent(lblTipoPagamento)
                    )
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(cbDias)
                        .addComponent(cbHorarios)
                        .addComponent(cbTipoAtendimento)
                        .addComponent(cbTipoPagamento)
                    )
                    
                )
                .addComponent(btnSalvarAgendamento)
            )
        );
 
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblDia)
                .addComponent(cbDias))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblHorario)
                .addComponent(cbHorarios))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblTipoAtendimento)
                .addComponent(cbTipoAtendimento))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblTipoPagamento)
                .addComponent(cbTipoPagamento))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btnSalvarAgendamento))
        );
        
        add(lblDia); 
        add(cbDias);
        add(lblHorario);
        add(cbHorarios);
        add(lblTipoAtendimento);
        add(cbTipoAtendimento);
        add(lblTipoPagamento);
        add(cbTipoPagamento);
    }

    public void alterarAtendimento(int idRecurso, int tipoRecurso){
        this.idRecurso = idRecurso;
        this.tipoRecurso = tipoRecurso;
        
        setarComboBoxHorariosRenderer();
    }
    
    private void configurarComboBoxes(){
        Integer[] dias = new Integer[30];
        Integer[] horarios = new Integer[18];
        Integer[] tiposAtendimento = new Integer[3];
        Integer[] tiposPagamento = new Integer[3];
        
        for(int i=0;i<30;i++){
            dias[i] = i+1;
            if(i<18){
                horarios[i] = i+1;
            }
            
            if(i<3){
                tiposAtendimento[i] = i+1;
                tiposPagamento[i] = i+1;
            }
            
        }
        
        cbDias = new JComboBox(new DefaultComboBoxModel(dias));
        cbHorarios = new JComboBox(new DefaultComboBoxModel(horarios));
        cbTipoAtendimento = new JComboBox(new DefaultComboBoxModel(tiposAtendimento)); 
        cbTipoPagamento = new JComboBox(new DefaultComboBoxModel(tiposPagamento));
        
        lblTipoPagamento.setVisible(false);
        cbTipoPagamento.setVisible(false);
        
        setarComboBoxHorariosRenderer();

        
        cbDias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diaSelecionado = (int) cbDias.getSelectedItem();
                setarComboBoxHorariosRenderer();
            }
        });
        
        cbHorarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                horarioSelecionado = (int) cbHorarios.getSelectedItem();
            }
        });
        
        cbTipoAtendimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((int)cbTipoAtendimento.getSelectedItem() == Agendamento.TIPO_PARTICULAR){
                    lblTipoPagamento.setVisible(true);
                    cbTipoPagamento.setVisible(true);
                }
                else{
                    lblTipoPagamento.setVisible(false);
                    cbTipoPagamento.setVisible(false);
                }
            }
        });
        
    }
    
    private void setarComboBoxHorariosRenderer(){
        cbHorarios.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                ((JLabel) renderer).setText(Agendamento.getTextoHorario((int) value));
                if(idRecurso != -1){
                    if(tipoRecurso == PainelFinalizacaoAgendamento.RECURSO_MEDICO){
                        if(regAgendamentoConsulta.buscarPorMedicoDiaHorario(idRecurso, diaSelecionado, (int) value) != null){
                            renderer.setEnabled(false);
                        }
                    }
                    else{
                        if(regAgendamentoExame.buscarPorExameDiaHorario(idRecurso, diaSelecionado, (int)value) != null){
                            renderer.setEnabled(false);
                        }
                    }
                }
                
                return renderer;
            }
        });
        
        cbTipoAtendimento.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                ((JLabel) renderer).setText(Agendamento.getTextoTipo((int) value));
                return renderer;
            }
        });
        
        cbTipoPagamento.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                ((JLabel) renderer).setText(Agendamento.getTextoTipoPagamento((int) value));
                return renderer;
            }
        });
    }
    
    public void setarHabilitado(boolean status){
        setEnabled(status);
        lblDia.setEnabled(status);
        lblHorario.setEnabled(status);
        lblTipoAtendimento.setEnabled(status);

        cbDias.setEnabled(status);
        cbHorarios.setEnabled(status);
        cbTipoAtendimento.setEnabled(status);

        btnSalvarAgendamento.setEnabled(status);
    }
    
}
