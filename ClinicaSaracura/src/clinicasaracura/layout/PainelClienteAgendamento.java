/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.layout;

import clinicasaracura.modelo.Cliente;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PainelClienteAgendamento extends JPanel{
    private JTextField txtNomeCliente;
    private JButton btnBuscarCliente;
    private JButton btnAddCliente;
    private JLabel lblNomeCliente;
    private JTextArea txtInfoCliente;
    private JList lstClientes;
    private ArrayList<Cliente> arrayClientes;
    
    public PainelClienteAgendamento(ArrayList<Cliente> arrayClientes) {
        super();
        this.arrayClientes = arrayClientes;
        
        setBorder(BorderFactory.createTitledBorder("Cliente")); 
        txtNomeCliente = new JTextField(20);
        lblNomeCliente = new JLabel("Nome:");
        txtInfoCliente = new JTextArea();
        btnBuscarCliente = new JButton("Buscar");
        btnAddCliente = new JButton("Adicionar novo Cliente"); 
        
        configurarListaClientes();
        setarAcoesBotoes();
        
        add(lblNomeCliente);
        add(txtNomeCliente);
        add(btnBuscarCliente);
        add(lstClientes);
        add(txtInfoCliente);
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
                
            }
        });
    }
    
    
    
}
