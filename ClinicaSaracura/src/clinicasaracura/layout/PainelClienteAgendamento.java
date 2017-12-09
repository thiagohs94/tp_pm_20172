/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.layout;

import clinicasaracura.modelo.Cliente;
import clinicasaracura.registro.ClienteRegistro;
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

public class PainelClienteAgendamento extends JPanel{
    private TelaAgendamento telaPai;
    private JTextField txtNomeCliente;
    private JButton btnBuscarCliente;
    private JButton btnAddCliente;
    private JButton btnConfirmarCliente;
    private JLabel lblNomeCliente;
    private JLabel lblSelecionarCliente;
    private JLabel lblInfoCliente;
    private JLabel lblNenhumResultado;
    private JTextArea txtInfoCliente;
    private JList lstClientes;
    private JPanel pnlBusca;
    private JPanel pnlBotoes;
    private ClienteRegistro regClientes;
    
    public PainelClienteAgendamento(TelaAgendamento telaPai, ClienteRegistro regClientes) {
        super();
        this.regClientes = regClientes;
        this.telaPai = telaPai;
        
        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        setBorder(BorderFactory.createTitledBorder("Cliente")); 
        setLayout(layout);
        
        txtNomeCliente = new JTextField(20);
        lblNomeCliente = new JLabel("Nome:");
        lblSelecionarCliente = new JLabel("Selecione um cliente para realizar um agendamento:");
        lblNenhumResultado = new JLabel("Nenhum resultado encontrado.");
        lblInfoCliente = new JLabel("Informações do Cliente:");
        txtInfoCliente = new JTextArea();
        btnBuscarCliente = new JButton("Buscar");
        btnAddCliente = new JButton("Adicionar novo Cliente");
        btnConfirmarCliente = new JButton("Confirmar Cliente");
        
        lblNenhumResultado.setVisible(false);
        lblInfoCliente.setVisible(false);
        txtInfoCliente.setVisible(false);
        btnConfirmarCliente.setEnabled(false);
        
        configurarListaClientes();
        setarAcoesBotoes();
        
        pnlBusca = new JPanel();
        pnlBusca.add(lblNomeCliente);
        pnlBusca.add(txtNomeCliente);
        pnlBusca.add(btnBuscarCliente);
        
        pnlBotoes = new JPanel();
        pnlBotoes.add(btnAddCliente);
        pnlBotoes.add(btnConfirmarCliente);        
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pnlBusca)
                        .addComponent(lblNenhumResultado)
                        .addComponent(lblSelecionarCliente)
                        .addComponent(lstClientes)
                        .addComponent(lblInfoCliente)
                        .addComponent(txtInfoCliente)
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
                .addComponent(lblSelecionarCliente))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lstClientes))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblInfoCliente))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(txtInfoCliente))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(pnlBotoes))
        );
        
        add(pnlBusca);
        add(lblNenhumResultado);
        add(lblSelecionarCliente);
        add(lstClientes);
        add(lblInfoCliente);
        add(txtInfoCliente);
        add(pnlBotoes);
    }
    
    private void configurarListaClientes(){
        lstClientes = new JList(new Vector<Cliente>(regClientes.getListaRegistros()));
        lstClientes.setVisibleRowCount(10);
        lstClientes.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof Cliente) {
                    ((JLabel) renderer).setText(((Cliente) value).getNome());
                }
                return renderer;
            }
        });
        
        lstClientes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(lstClientes.getSelectedValue() != null){
                    txtInfoCliente.setText(((Cliente)lstClientes.getSelectedValue()).toString());
                    btnConfirmarCliente.setEnabled(true);
                    txtInfoCliente.setVisible(true);
                    lblInfoCliente.setVisible(true);
                }
            }
        });
    }
    
    public void setarAcoesBotoes(){
        btnBuscarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Cliente> result = regClientes.buscarPorNome(txtNomeCliente.getText());
                if(result.isEmpty()){
                    lblNenhumResultado.setVisible(true);
                    lstClientes.setVisible(false);
                    lblSelecionarCliente.setVisible(false);
                }
                else{
                    lstClientes.setListData(new Vector<Cliente>(result));
                    lblNenhumResultado.setVisible(false);
                    lstClientes.setVisible(true);
                    lblSelecionarCliente.setVisible(true);
                }
                btnConfirmarCliente.setEnabled(false);
                txtInfoCliente.setVisible(false);
                lblInfoCliente.setVisible(false);
            }
        });
        
        btnAddCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaPai.adicionarNovoCliente();
            }
        });
        
        btnConfirmarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lstClientes.getSelectedValue() != null){
                    telaPai.confirmarCliente(((Cliente)lstClientes.getSelectedValue()));
                }
                else{
                    JOptionPane.showMessageDialog(null, "Selecione um dos clientes na lista",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    public void atualizarClientes(ClienteRegistro regClientes){
        this.regClientes = regClientes;
        lstClientes.setListData(new Vector<Cliente>(regClientes.getListaRegistros()));
    }
    
    public void setarHabilitado(boolean status){
        setEnabled(status);
        txtNomeCliente.setEnabled(status);
        btnBuscarCliente.setEnabled(status);
        btnAddCliente.setEnabled(status);
        btnConfirmarCliente.setEnabled(status);
        lblNomeCliente.setEnabled(status);
        lblSelecionarCliente.setEnabled(status);
        lblNenhumResultado.setEnabled(status);
        lblInfoCliente.setEnabled(status);
        txtInfoCliente.setEnabled(status);
        lstClientes.setEnabled(status);
    }
}
