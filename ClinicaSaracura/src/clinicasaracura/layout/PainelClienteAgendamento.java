/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.layout;

import clinicasaracura.modelo.Cliente;
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
    private JLabel lblNenhumResultado;
    private JTextArea txtInfoCliente;
    private JList lstClientes;
    private JPanel pnlBusca;
    private JPanel pnlBotoes;
    private ArrayList<Cliente> arrayClientes;
    
    public PainelClienteAgendamento(TelaAgendamento telaPai, ArrayList<Cliente> arrayClientes) {
        super();
        this.arrayClientes = arrayClientes;
        this.telaPai = telaPai;
        
        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        setBorder(BorderFactory.createTitledBorder("Cliente")); 
        setLayout(layout);
        
        txtNomeCliente = new JTextField(20);
        lblNomeCliente = new JLabel("Nome:");
        lblSelecionarCliente = new JLabel("Selecione um cliente para realizar um agendamento:");
        lblNenhumResultado = new JLabel("Nenhum resultado encontrado");
        txtInfoCliente = new JTextArea();
        btnBuscarCliente = new JButton("Buscar");
        btnAddCliente = new JButton("Adicionar novo Cliente");
        btnConfirmarCliente = new JButton("Confirmar Cliente");
        
        lblNenhumResultado.setVisible(false);
        

        
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
                .addComponent(txtInfoCliente))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(pnlBotoes))
        );
        
        add(pnlBusca);
        add(lblNenhumResultado);
        add(lblSelecionarCliente);
        add(lstClientes);
        add(txtInfoCliente);
        add(pnlBotoes);
    }
    
    private void configurarListaClientes(){
        lstClientes = new JList(new Vector<Cliente>(arrayClientes));
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
                txtInfoCliente.setText(((Cliente)lstClientes.getSelectedValue()).toString());
            }
        });
    }
    
    public void setarAcoesBotoes(){
        btnBuscarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Cliente> result = 
                    telaPai.buscarCliente(txtNomeCliente.getText());
                if(result.isEmpty()){
                    lblNenhumResultado.setVisible(true);
                    lstClientes.setVisible(false);
                }
                else{
                    arrayClientes = result;
                    lstClientes.setListData(new Vector<Cliente>(arrayClientes));
                    lblNenhumResultado.setVisible(false);
                    lstClientes.setVisible(true);
                }
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
                telaPai.confirmarCliente(((Cliente)lstClientes.getSelectedValue()));
            }
        });
    }
    
    
    
}
