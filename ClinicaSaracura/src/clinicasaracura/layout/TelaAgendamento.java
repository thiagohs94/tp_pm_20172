/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.layout;

 
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaAgendamento {
    
    private String Teste;
    
    private FrameSistema frame;
    TelaPrincipal telaPrincipal;
    
    private GroupLayout layout;
    private JPanel pnlCampos;
    
    private JLabel lblTitulo;
    
    
    private JButton btnCancelar, btnConfirmar;

    
    public TelaAgendamento(TelaPrincipal telaPrincipal){
        this();
        this.telaPrincipal = telaPrincipal;
    }
    
    
    
    public TelaAgendamento() {
        
         
        frame = new FrameSistema("Clínica Saracura - Agendamento de Exames");
        frame.setLayout(new GridBagLayout());
        pnlCampos = new JPanel();
        
        layout = new GroupLayout(pnlCampos);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        pnlCampos.setLayout(layout);
        
        lblTitulo = new JLabel("Excolha sua especialidade:");
        
        btnCancelar = new JButton("Cancelar");
        btnConfirmar = new JButton("Continuar");
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
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
        );
        
        setarAcoesBotoes();
        
        frame.add(pnlCampos);
        frame.pack();
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
    
    
    
}
