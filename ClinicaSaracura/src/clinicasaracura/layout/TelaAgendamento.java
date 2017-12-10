/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.layout;

 
import clinicasaracura.modelo.Agendamento;
import clinicasaracura.modelo.AgendamentoConsulta;
import clinicasaracura.modelo.AgendamentoExame;
import clinicasaracura.modelo.Autorizacao;
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
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class TelaAgendamento {    
    private Cliente clienteEscolhido;
    private Exame exameEscolhido;
    private Medico medicoEscolhido;
    
    private FrameSistema frame;
    TelaPrincipal telaPrincipal;

    private PainelClienteAgendamento pnlCliente;
    private PainelMedicoAtendimento pnlMedico;
    private PainelExameAtendimento pnlExame;
    private PainelFinalizacaoAgendamento pnlFinalizacao;
    
    private GroupLayout layout;
    private JPanel pnlCampos;
    private JLabel lblTitulo;
    
    private JPanel pnlAtendimento;
    private JLabel lblAtendimento;
    private ButtonGroup groupAtendimento;
    private JRadioButton rbConsulta, rbExame;
    
    private JButton btnCancelar;    
    
    ClienteRegistro regClientes;
    EspecialidadeRegistro regEspecialidades;
    ExameRegistro regExames;
    MedicoRegistro regMedicos;
    AgendamentoConsultaRegistro regAgendamentoConsultas;
    AgendamentoExameRegistro regAgendamentoExames;
    
    
    public TelaAgendamento(TelaPrincipal telaPrincipal, ClienteRegistro regClientes,
        EspecialidadeRegistro regEspecialidades,
        ExameRegistro regExames, MedicoRegistro regMedicos,
        AgendamentoConsultaRegistro regAgendamentoConsultas,
        AgendamentoExameRegistro regAgendamentoExames) {
        
        this.telaPrincipal = telaPrincipal;
        
        this.regClientes = regClientes;
        this.regEspecialidades = regEspecialidades;
        this.regExames = regExames;
        this.regMedicos = regMedicos;
        this.regAgendamentoConsultas = regAgendamentoConsultas;
        this.regAgendamentoExames = regAgendamentoExames;
         
        frame = new FrameSistema("Clínica Saracura - Agendamento de Consultas e Exames");
        frame.setLayout(new GridBagLayout());
        
        lblTitulo = new JLabel("Escolha sua especialidade:");
        
        btnCancelar = new JButton("Cancelar Agendamento");
        
        pnlCliente = new PainelClienteAgendamento(this, regClientes);
        pnlMedico = new PainelMedicoAtendimento(this, regMedicos, regEspecialidades);
        pnlExame = new PainelExameAtendimento(this, regExames);
        pnlFinalizacao = new PainelFinalizacaoAgendamento(this, regAgendamentoConsultas, regAgendamentoExames);
        
        pnlAtendimento = new JPanel();
        pnlAtendimento.setBorder(BorderFactory.createTitledBorder("Atendimento"));
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
                        .addComponent(pnlMedico)
                        .addComponent(pnlExame)
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
                .addComponent(pnlMedico))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(pnlExame))
        );
        
        pnlAtendimento.add(lblAtendimento);
        pnlAtendimento.add(rbConsulta);
        pnlAtendimento.add(rbExame);
        pnlAtendimento.add(pnlMedico);
        pnlAtendimento.add(pnlExame);
        
        setarPainelAtendimentoHabilitado(false);
        setarPainelFinalizacaoHabilitado(false);
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
        frame.add(pnlAtendimento,c);
        
        c.gridx = 2;
        frame.add(pnlFinalizacao,c);
        
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        frame.add(btnCancelar, c);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private void setarPainelClienteHabilitado(boolean status){
        pnlCliente.setarHabilitado(status);
    }
    
    private void setarPainelAtendimentoHabilitado(boolean status){
        pnlAtendimento.setEnabled(status);
        lblAtendimento.setEnabled(status);
        rbConsulta.setEnabled(status);
        rbExame.setEnabled(status);
        pnlMedico.setarHabilitado(status);
        pnlExame.setarHabilitado(status);
    }
    
    private void setarPainelFinalizacaoHabilitado(boolean status){
        pnlFinalizacao.setarHabilitado(status);
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
    
    private void setarAcoesBotoes(){
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaPrincipal.setarVisibilidade(true);
                frame.dispose();
            }
        });
        
     
    }
    
    private void alterarTipoAtendimento(){
        if(rbConsulta.isSelected()){
            pnlMedico.setVisible(true);
            pnlExame.setVisible(false);
        }
        else{
            pnlMedico.setVisible(false);
            pnlExame.setVisible(true);
        }
    }
    
    
    public void adicionarNovoCliente(){
        telaPrincipal.exibirCadastroCliente();
    }
    
    public void atualizarClientes(ClienteRegistro regClientes){
        this.regClientes = regClientes;
        pnlCliente.atualizarClientes(regClientes);
    }
    
    public void confirmarCliente(Cliente cliente){
        clienteEscolhido = cliente;
        setarPainelClienteHabilitado(false);
        setarPainelAtendimentoHabilitado(true);
        setarPainelFinalizacaoHabilitado(false);
    }
    
    public void confirmarMedico(Medico medico){
        medicoEscolhido = medico;
        exameEscolhido = null;
        setarPainelClienteHabilitado(false);
        setarPainelAtendimentoHabilitado(false);
        setarPainelFinalizacaoHabilitado(true);
        
        pnlFinalizacao.alterarAtendimento(medico.getId(), PainelFinalizacaoAgendamento.RECURSO_MEDICO);
    }
    
    public void confirmarExame(Exame exame){
        medicoEscolhido = null;
        exameEscolhido = exame;
        setarPainelClienteHabilitado(false);
        setarPainelAtendimentoHabilitado(false);
        setarPainelFinalizacaoHabilitado(true);
        
        pnlFinalizacao.alterarAtendimento(exame.getId(), PainelFinalizacaoAgendamento.RECURSO_EXAME);
    }
    
    public void salvarAgendamento(int dia, int horario, int tipo, int tipoPagamento){
        boolean autorizado = true;
        
        Autorizacao autorizacao = new Autorizacao();
        if(tipo == Agendamento.TIPO_CORTESIA){
            autorizado = autorizacao.autorizaCortesia();
        }
        else if(tipo == Agendamento.TIPO_CONVENIO){
            autorizado = autorizacao.autorizaConvenio();
        }
        else if(tipo == Agendamento.TIPO_PARTICULAR && tipoPagamento == Agendamento.TIPO_PAGAMENTO_DINHEIRO){
            autorizado = autorizacao.autorizaDinheiro();
        }
        else if(tipo == Agendamento.TIPO_PARTICULAR && tipoPagamento == Agendamento.TIPO_PAGAMENTO_CHEQUE){
            autorizado = autorizacao.autorizaCheque();
        }
        else if(tipo == Agendamento.TIPO_PARTICULAR && tipoPagamento == Agendamento.TIPO_PAGAMENTO_CARTAO){
            autorizado = autorizacao.autorizaCartao();
        }
        
        if(autorizado){
            if(medicoEscolhido != null){
                AgendamentoConsulta agendamento = new AgendamentoConsulta();
                agendamento.setCliente(clienteEscolhido);
                agendamento.setDia(dia);
                agendamento.setHorario(horario);
                agendamento.setTipo(tipo);
                agendamento.setMedico(medicoEscolhido);

                regAgendamentoConsultas.salvar(agendamento);
            }
            else if(exameEscolhido != null){
                AgendamentoExame agendamento = new AgendamentoExame();
                agendamento.setCliente(clienteEscolhido);
                agendamento.setDia(dia);
                agendamento.setHorario(horario);
                agendamento.setTipo(tipo);
                agendamento.setExame(exameEscolhido);

                regAgendamentoExames.salvar(agendamento);
            }

            JOptionPane.showMessageDialog(null, "Agendamento salvo com sucesso",
                "Clínica Saracura", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "Este agendamento não foi autorizado",
                "Clínica Saracura", JOptionPane.ERROR_MESSAGE);

        }
        telaPrincipal.setarVisibilidade(true);
        frame.dispose();
        
    }
}
