/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.layout;

import clinicasaracura.modelo.Cliente;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.MaskFormatter;

public class TelaCadastroCliente {
    private Cliente cliente;
    
    private FrameSistema frame;
    TelaPrincipal telaPrincipal;
    
    private GroupLayout layout;
    private JPanel pnlCampos;
    
    private JLabel lblNome;
    private JLabel lblIdentidade;
    private JLabel lblCpf;
    private JLabel lblEndereco;
    private JLabel lblTelefone;
    private JLabel lblDataNascimento;
    
    private JTextField txtNome;
    private JTextField txtIdentidade;
    private JFormattedTextField txtCpf;
    private JTextField txtEndereco;
    private JFormattedTextField txtTelefone;
    private JFormattedTextField txtDataNascimento;
    
    private JButton btnCancelar, btnConfirmar;
    
    private MaskFormatter mascaraData, mascaraTelefone, mascaraCpf;
    
    public TelaCadastroCliente(TelaPrincipal telaPrincipal){
        this();
        this.telaPrincipal = telaPrincipal;
    }
    
    public TelaCadastroCliente() {
        cliente = new Cliente();
        
        frame = new FrameSistema("Clínica Saracura - Cadastro de Cliente");
        frame.setLayout(new GridBagLayout());
        pnlCampos = new JPanel();
        
        layout = new GroupLayout(pnlCampos);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        pnlCampos.setLayout(layout);
        
        lblNome = new JLabel("Nome:");
        lblIdentidade = new JLabel("Identidade:");
        lblCpf = new JLabel("CPF:");
        lblEndereco = new JLabel("Endereço:");
        lblTelefone = new JLabel("Telefone:");
        lblDataNascimento = new JLabel("Data de Nascimento:");
        
        txtNome = new JTextField(20);
        txtIdentidade = new JTextField(20);
        txtCpf = new JFormattedTextField();
        txtEndereco = new JTextField(20);
        txtTelefone = new JFormattedTextField();
        txtDataNascimento = new JFormattedTextField();
        
        try{
            mascaraData = new MaskFormatter("##/##/####");
            mascaraTelefone = new MaskFormatter("(##)#####-####");
            mascaraCpf = new MaskFormatter("###.###.###-##");
            mascaraData.install(txtDataNascimento);
            mascaraTelefone.install(txtTelefone);
            mascaraCpf.install(txtCpf);
        }
        catch(ParseException ex){
            System.err.println("Erro ao criar máscaras para campos");
            System.exit(1);
        }
        
        btnCancelar = new JButton("Cancelar");
        btnConfirmar = new JButton("Confirmar");
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblNome)
                        .addComponent(lblIdentidade)
                        .addComponent(lblCpf)
                        .addComponent(lblEndereco)
                        .addComponent(lblTelefone)
                        .addComponent(lblDataNascimento)
                    )
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(txtNome)
                        .addComponent(txtIdentidade)
                        .addComponent(txtCpf)
                        .addComponent(txtEndereco)
                        .addComponent(txtTelefone)
                        .addComponent(txtDataNascimento)
                    )
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnCancelar)
                    .addComponent(btnConfirmar)
                )
            )
        );
 
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblNome)
                .addComponent(txtNome))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblIdentidade)
                .addComponent(txtIdentidade))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblCpf)
                .addComponent(txtCpf))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblEndereco)
                .addComponent(txtEndereco))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblTelefone)
                .addComponent(txtTelefone))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblDataNascimento)
                .addComponent(txtDataNascimento))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btnCancelar)
                .addComponent(btnConfirmar))
        );
        
        setarAcoesBotoes();
        setarAcaoFrame()    ;
        
        frame.add(pnlCampos);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    
    private void atualizarCamposCliente(){
        cliente.setId(telaPrincipal.proxIdCliente());
        cliente.setNome(txtNome.getText());
        cliente.setIdentidade(txtIdentidade.getText());
        cliente.setCpf(txtCpf.getText().replaceAll("[\\s().-]", ""));
        cliente.setEndereco(txtEndereco.getText());
        cliente.setTelefone(txtTelefone.getText().replaceAll("[\\s().-]", ""));
        cliente.setDataNascimento(txtDataNascimento.getText().replaceAll("[\\s().-]", ""));
    }
    
    private void setarAcoesBotoes(){
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarCadastro();
            }
        });
        
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarCamposCliente();
                telaPrincipal.salvarCliente(cliente);
                telaPrincipal.habilitar();
                frame.dispose();
            }
        });
    }
    
    private void setarAcaoFrame(){
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                cancelarCadastro();
            }
        });
    }
    
    private void cancelarCadastro(){
        telaPrincipal.habilitar();
        frame.dispose();
    }
}
