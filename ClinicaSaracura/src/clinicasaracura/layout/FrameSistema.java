/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.layout;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class FrameSistema extends JFrame{

    public FrameSistema(String titulo) throws HeadlessException {
        super(titulo);
        configurarFrame();
    }
    
    private void configurarFrame(){
        Dimension tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) tamanhoTela.getWidth()-200, (int)tamanhoTela.getHeight()-200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }   
}
