package GUI;

import domain.Onderdeel;
import domain.Klant;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import java.util.ArrayList;

public class JFrameVerwijderen extends javax.swing.JFrame implements ActionListener {

    JComboBox jCbSelect;
    ArrayList<Component> componenten;
    ArrayList<Klant> klanten;
    ArrayList<Onderdeel> onderdelen;
    String soort;
    Magazijn main;
    JOptionPane op;

    public JFrameVerwijderen(String soort, ArrayList<Onderdeel> onderdelen, ArrayList<Klant> klanten, Magazijn main) {
        initComponents();

        op = new JOptionPane();
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setTitle("Onderdeel:");
        this.main = main;
        this.setLocation(400, 250);
        jBtClose.setText("Sluit Venster");
        componenten = new ArrayList<Component>();
        jCbSelect = new JComboBox();
        jCbSelect.setBounds(20, 20, 200, 20);

        add(jCbSelect);
        JButton button = new JButton("Verwijder");
        button.addActionListener(this);
        button.setBounds(70, 100, 100, 30);
        button.setActionCommand("Button verwijderen.");
        add(button);

        try {
            this.soort = soort;
            this.onderdelen = onderdelen;
            this.klanten = klanten;
            if (soort.equals("Klant")) {
                for (Klant kl : klanten) {
                    jCbSelect.addItem(Integer.toString(kl.getId()) + ":   " + kl.getNaam());
                }
            } else if (soort.equals("Onderdeel")) {
                for (Onderdeel ond : onderdelen) {
                    jCbSelect.addItem(Integer.toString(ond.getCode()) + ":   " + ond.getOmschrijving());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBtClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(GUI.MagazijnApplicatieApp.class).getContext().getResourceMap(JFrameVerwijderen.class);
        jBtClose.setText(resourceMap.getString("jBtClose.text")); // NOI18N
        jBtClose.setName("jBtClose"); // NOI18N
        jBtClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtClose, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(170, Short.MAX_VALUE)
                .addComponent(jBtClose, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jBtCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtCloseActionPerformed
        main.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jBtCloseActionPerformed

    public void actionPerformed(ActionEvent e) {
        if (soort.equals("Onderdeel")) {
            int index = jCbSelect.getSelectedIndex();
            main.getMagazijn().VerwijderOnderdeel(onderdelen.get(index).getCode());
            op.showMessageDialog(null, "Het Onderdeel is correct verwijderd.", "Gelukt!", JOptionPane.OK_OPTION);
            this.setVisible(false);
            this.dispose();
            main.setVisible(true);
        } else if (soort.equals("Klant")) {
            int index = jCbSelect.getSelectedIndex();
            main.getMagazijn().VerwijderKlant(klanten.get(index).getId());
            op.showMessageDialog(null, "De klant is correct verwijderd.", "Gelukt!", JOptionPane.OK_OPTION);
            this.setVisible(false);
            this.dispose();
            main.setVisible(true);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtClose;
    // End of variables declaration//GEN-END:variables
}
