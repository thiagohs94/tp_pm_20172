/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.layout;

 
import clinicasaracura.modelo.Cliente;
import clinicasaracura.modelo.Especialidade;
import clinicasaracura.modelo.Exame;
import clinicasaracura.modelo.Medico;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class TelaAgendamento {    
    private Cliente clienteEscolhido;
    private Exame exameEscolhido;
    private Medico medicoEscolhido;
    
    private FrameSistema frame;
    TelaPrincipal telaPrincipal;
    
    private ArrayList<Cliente> lstCliente;
    private ArrayList<Medico> lstMedicos;
    private ArrayList<Exame> lstExames;
    private ArrayList<Especialidade> lstEspecialidades;
    private PainelClienteAgendamento pnlCliente;
    private PainelMedicoAtendimento pnlMedico;
    private PainelExameAtendimento pnlExame;
    
    private GroupLayout layout;
    private JPanel pnlCampos;
    private JLabel lblTitulo;
    
    private JPanel pnlAtendimento;
    private JLabel lblAtendimento;
    private ButtonGroup groupAtendimento;
    private JRadioButton rbConsulta, rbExame;
    
    private JButton btnCancelar, btnConfirmar;    
    
    
    public TelaAgendamento(TelaPrincipal telaPrincipal,
        ArrayList<Cliente> lstCliente,
        ArrayList<Medico> lstMedicos,
        ArrayList<Exame> lstExames,
        ArrayList<Especialidade> lstEspecialidades) {
        
        this.telaPrincipal = telaPrincipal;
        this.lstCliente = lstCliente;
        this.lstMedicos = lstMedicos;
        this.lstExames = lstExames;
        this.lstEspecialidades = lstEspecialidades;
         
        frame = new FrameSistema("Clínica Saracura - Agendamento de Exames");
        frame.setLayout(new GridBagLayout());
        //pnlCampos = new JPanel();
        
        /*layout = new GroupLayout(pnlCampos);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);*/
        //pnlCampos.setLayout(layout);
        
        
        
        lblTitulo = new JLabel("Escolha sua especialidade:");
        
        btnCancelar = new JButton("Cancelar");
        btnConfirmar = new JButton("Continuar");
        
        pnlCliente = new PainelClienteAgendamento(this, lstCliente);
        pnlMedico = new PainelMedicoAtendimento(this, lstMedicos, lstEspecialidades);
        pnlExame = new PainelExameAtendimento(this, lstExames);
        
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
        
        pnlExame.setVisible(false);
        
        /*layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblTitulo)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnCancelar)
                    .addComponent(btnConfirmar)
                )
            )
        );
 
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblTitulo))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btnCancelar)
                .addComponent(btnConfirmar))
        );*/
        
        setarAcaoRadio();
        setarAcoesBotoes();
        
        frame.add(pnlCliente);
        frame.add(pnlAtendimento);

        //frame.add(pnlCampos);
        //frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
                telaPrincipal.habilitar();
                destruir();
            }
        });
        
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaPrincipal.habilitar();
                destruir();
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
    
    private void destruir(){
        frame.dispose();
    }
    
    public ArrayList<Cliente> buscarCliente(String nome){
        return telaPrincipal.regClientes.buscarPorNome(nome);
    }
    
    public ArrayList<Medico> buscarMedico(String nome){
        return telaPrincipal.regMedicos.buscarPorNome(nome);
    }
    
    public ArrayList<Medico> buscarMedicoEspec(int id){
        return telaPrincipal.regMedicos.buscarPorEspecialiade(id);
    }
    
    public ArrayList<clinicasaracura.modelo.Exame> buscarExame(String nome){
        return telaPrincipal.regExame.buscarPorNome(nome);
    }
    
    public void adicionarNovoCliente(){
        telaPrincipal.exibirCadastroCliente();
    }
    
    public void confirmarCliente(Cliente cliente){
        clienteEscolhido = cliente;
        System.out.println(clienteEscolhido.toString()); 
    }
    
    public void confirmarMedico(Medico medico){
        medicoEscolhido = medico;
        System.out.println(medicoEscolhido.toString());
    }
    
    public void confirmarExame(Exame exame){
        exameEscolhido = exame;
        System.out.println(exameEscolhido.toString());
    }
    
}
