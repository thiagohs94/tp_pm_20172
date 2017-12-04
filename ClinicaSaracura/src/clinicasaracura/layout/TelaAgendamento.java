/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.layout;

 
import clinicasaracura.modelo.Cliente;
import clinicasaracura.modelo.Medico;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private FrameSistema frame;
    TelaPrincipal telaPrincipal;
    
    private ArrayList<Cliente> lstCliente;
    private ArrayList<Medico> lstMedicos;
    private PainelClienteAgendamento pnlCliente;
    private PainelMedicoAgendamento pnlMedico;
    
    private GroupLayout layout;
    private JPanel pnlCampos;
    private JLabel lblTitulo;
    
    private JPanel pnlAtendimento;
    private JLabel lblAtendimento;
    private ButtonGroup groupAtendimento;
    private JRadioButton rbConsulta, rbExame;
    
    private JButton btnCancelar, btnConfirmar;    
    
    
    public TelaAgendamento(TelaPrincipal telaPrincipal, ArrayList<Cliente> lstCliente, ArrayList<Medico> lstMedicos) {
        this.telaPrincipal = telaPrincipal;
        this.lstCliente = lstCliente;
        this.lstMedicos = lstMedicos;
         
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
        pnlMedico = new PainelMedicoAgendamento(this, lstMedicos);
        
        pnlAtendimento = new JPanel();
        pnlAtendimento.setBorder(BorderFactory.createTitledBorder("Atendimento"));
        lblAtendimento = new JLabel("Escolha o tipo de atendimento: ");
        rbConsulta = new JRadioButton("Consulta");
        rbExame = new JRadioButton("Exame");
        rbConsulta.setSelected(true);
        
        groupAtendimento = new ButtonGroup();
        groupAtendimento.add(rbConsulta);
        groupAtendimento.add(rbExame);
        
        pnlAtendimento.add(lblAtendimento);
        pnlAtendimento.add(rbConsulta);
        pnlAtendimento.add(rbExame);
        pnlAtendimento.add(pnlMedico);
        
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
        
        setarAcoesBotoes();
        
        frame.add(pnlCliente);
        frame.add(pnlAtendimento);

        //frame.add(pnlCampos);
        //frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
    
    private void destruir(){
        frame.dispose();
    }
    
    public ArrayList<Cliente> buscarCliente(String nome){
        return telaPrincipal.regClientes.buscarPorNome(nome);
    }
    
    public ArrayList<Medico> buscarMedico(String nome){
        return telaPrincipal.regMedicos.buscarPorNome(nome);
    }
    
    public void adicionarNovoCliente(){
        telaPrincipal.exibirCadastroCliente();
    }
    
    public void confirmarCliente(){
        
    }
    
    public void confirmarMedico(){
        
    }
    
}
