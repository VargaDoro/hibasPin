package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import modell.PinModell;

public class PinController {
    private PinModell modell;
    private JPanel jPanel1;
    private JCheckBox chbMutat;

    public PinController(PinModell modell, JPanel jPanel1, JCheckBox chbMutat) {
        this.modell = modell;
        this.jPanel1 = jPanel1;
        this.chbMutat = chbMutat;
        hozzaadGomb();
        chbMutat.setEnabled(false);
        chbMutat.addActionListener(this::chbMutatActionPerformed);
    }
    
    private void hozzaadGomb(){
        for (int i = 0; i < jPanel1.getComponentCount(); i++) {
            JButton btn = (JButton) jPanel1.getComponent(i);
            btn.setText(String.valueOf(i));
            btn.addActionListener(this::gombKattintas);
            btn.setBackground(Color.LIGHT_GRAY);
        }
    }
    
    private void gombKattintas(ActionEvent e) {
        if (!modell.isComplete()) {
            modell.addDigit(e.getActionCommand());
            szinValasztas(Color.LIGHT_GRAY);
        }
        if (modell.isComplete()) {
            chbMutat.setEnabled(true);
            try {
                modell.mentesFileba();
                JOptionPane.showMessageDialog(jPanel1, "Pin mentve!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(jPanel1, "Hiba a mentés során!");
                ex.printStackTrace();
            }
        }
    }
    
    private void chbMutatActionPerformed(ActionEvent evt) {
        if(chbMutat.isSelected()){
            szinValasztas(Color.red);
        }else{
            chbMutat.setEnabled(false);
            modell.reset();
            szinValasztas(Color.LIGHT_GRAY);
        }
    }
    
    private void szinValasztas(Color szin){
        String pin = modell.getPin();
        for (int i = 0; i < pin.length(); i++) {
            int gomb = Integer.parseInt(pin.charAt(i)+"");
            jPanel1.getComponent(gomb).setBackground(szin);
        }
    }
}