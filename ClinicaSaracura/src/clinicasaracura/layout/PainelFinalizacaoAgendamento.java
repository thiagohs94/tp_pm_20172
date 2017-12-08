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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class PainelFinalizacaoAgendamento extends JPanel {
    public static final int RECURSO_MEDICO = 1;
    public static final int RECURSO_EXAME = 2;
    
    private TelaAgendamento telaPai;
    private JLabel lblDia;
    private JLabel lblHorario;
    private JComboBox cbDias;
    private JComboBox cbHorarios;
    
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
        
        setBorder(BorderFactory.createTitledBorder("Horário e Pagamento")); 
        
        diaSelecionado = 1;
        horarioSelecionado = 1;
        idRecurso = -1;
        tipoRecurso = -1;
        
        lblDia = new JLabel("Dia:");
        lblHorario = new JLabel("Horário:");
        configurarComboBoxes();
        
        add(lblDia); 
        add(cbDias);
        add(lblHorario);
        add(cbHorarios);
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public int getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(int tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }
    
    private void configurarComboBoxes(){
        Integer[] dias = new Integer[30];
        Integer[] horarios = new Integer[18];
        
        for(int i=0;i<30;i++){
            dias[i] = i+1;
            if(i<18){
                horarios[i] = i+1;
            }
        }
        
        cbDias = new JComboBox(new DefaultComboBoxModel(dias));
        cbHorarios = new JComboBox(new DefaultComboBoxModel(horarios));
        
        setComboBoxHorariosRenderer();
        
        cbDias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diaSelecionado = (int) cbDias.getSelectedItem();
            }
        });
        
        cbHorarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                horarioSelecionado = (int) cbHorarios.getSelectedItem();
            }
        });
        
    }
    
    private void setComboBoxHorariosRenderer(){
        cbHorarios.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                ((JLabel) renderer).setText(Agendamento.getTextoHorario((int) value));
                if(idRecurso != -1){
                    if(tipoRecurso == PainelFinalizacaoAgendamento.RECURSO_MEDICO){
                        regAgendamentoConsulta.buscarPorMedicoDiaHorario(idRecurso, diaSelecionado, horarioSelecionado);
                    }
                    else{
                        regAgendamentoExame.buscarPorExameDiaHorario(idRecurso, diaSelecionado, horarioSelecionado);
                    }
                }
                
                return renderer;
            }
        });
    }
    
}
