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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author eliane
 */
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
    private JTextField txtCpf;
    private JTextField txtEndereco;
    private JTextField txtTelefone;
    private JTextField txtDataNascimento;
    
    private JButton btnCancelar, btnConfirmar;
    
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
        txtCpf = new JTextField(20);
        txtEndereco = new JTextField(20);
        txtTelefone = new JTextField(20);
        txtDataNascimento = new JTextField(20);
        
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
        
        frame.add(pnlCampos);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private void atualizarCamposCliente(){
        cliente.setId(0);
        cliente.setNome(txtNome.getText());
        cliente.setIdentidade(txtIdentidade.getText());
        cliente.setCpf(txtCpf.getText());
        cliente.setEndereco(txtEndereco.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.setDataNascimento(txtDataNascimento.getText());
    }
    
    private void setarAcoesBotoes(){
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarCamposCliente();
                telaPrincipal.salvarCliente(cliente);
                telaPrincipal.habilitar();
                destruir();
            }
        });
    }
    
    private void destruir(){
        frame.dispose();
    }
    
}
