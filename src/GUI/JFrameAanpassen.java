package GUI;

import domain.Klant;
import domain.Onderdeel;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class JFrameAanpassen extends javax.swing.JFrame {

    private final Magazijn main;
    private final String soort;

    private ArrayList<Klant> klanten;
    private ArrayList<Onderdeel> onderdelen;
    private JTextField tfNaam, tfAdres, tfAantal, tfOmsch, tfPrijs;

    public JFrameAanpassen(String soort, Magazijn main, ArrayList<Klant> klanten, ArrayList<Onderdeel> onderdelen) {
        initComponents();
        this.setLocation(400, 250);

        this.main = main;
        this.soort = soort;

        if (soort.equals("Klant")) {
            this.klanten = klanten;
            CreateKlantGUI();
        } else if (soort.equals("Onderdeel")) {
            this.onderdelen = onderdelen;
            CreateOnderdeelGUI();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCbSelect = new javax.swing.JComboBox();
        jBtSluiten = new javax.swing.JButton();
        jBtAanpassen = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setName("Form"); // NOI18N

        jCbSelect.setName("jCbSelect"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(GUI.MagazijnApplicatieApp.class).getContext().getResourceMap(JFrameAanpassen.class);
        jBtSluiten.setText(resourceMap.getString("jBtSluiten.text")); // NOI18N
        jBtSluiten.setName("jBtSluiten"); // NOI18N
        jBtSluiten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtSluitenActionPerformed(evt);
            }
        });

        jBtAanpassen.setText(resourceMap.getString("jBtAanpassen.text")); // NOI18N
        jBtAanpassen.setName("jBtAanpassen"); // NOI18N
        jBtAanpassen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtAanpassenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCbSelect, 0, 242, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jBtSluiten)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jBtAanpassen)
                        .addGap(86, 86, 86))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCbSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                .addComponent(jBtAanpassen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtSluiten)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CreateKlantGUI() {
        jCbSelect.removeAllItems();
        for (Klant kl : klanten) {
            String temp = Integer.toString(kl.getId()) + " : " + kl.getNaam() + " : " + kl.getAdres();
            jCbSelect.addItem(temp);
        }

        JLabel lbNaam = new JLabel("Naam:");
        lbNaam.setBounds(20, 50, 150, 30);
        lbNaam.setFont(new Font("Times New Roman", 0, 15));
        add(lbNaam);

        JLabel lbAdres = new JLabel("Adres:");
        lbAdres.setBounds(20, 80, 150, 30);
        lbAdres.setFont(new Font("Times New Roman", 0, 15));
        add(lbAdres);

        tfNaam = new JTextField();
        tfNaam.setBounds(110, 55, 140, 20);
        tfNaam.setFont(new Font("Times New Roman", 0, 15));
        add(tfNaam);

        tfAdres = new JTextField();
        tfAdres.setBounds(110, 85, 140, 20);
        tfAdres.setFont(new Font("Times New Roman", 0, 15));
        add(tfAdres);
    }

    private void CreateOnderdeelGUI() {
        jCbSelect.removeAllItems();
        for (Onderdeel on : onderdelen) {
            double pRijs = on.getPrijs() / 100.00;
            String prijs = String.format("%.2f", pRijs);
            String temp = Integer.toString(on.getCode()) + " : " + on.getOmschrijving() + " : " + on.getAantal() + " : €" + prijs;
            jCbSelect.addItem(temp);
        }

        JLabel lbNaam = new JLabel("Omschrijving:");
        lbNaam.setBounds(20, 50, 150, 30);
        lbNaam.setFont(new Font("Times New Roman", 0, 15));
        add(lbNaam);

        JLabel lbAantal = new JLabel("Aantal:");
        lbAantal.setBounds(20, 80, 150, 30);
        lbAantal.setFont(new Font("Times New Roman", 0, 15));
        add(lbAantal);

        JLabel lbPrijs = new JLabel("Prijs in centen:");
        lbPrijs.setBounds(20, 110, 150, 30);
        lbPrijs.setFont(new Font("Times New Roman", 0, 15));
        add(lbPrijs);

        tfOmsch = new JTextField();
        tfOmsch.setBounds(110, 55, 140, 20);
        tfOmsch.setFont(new Font("Times New Roman", 0, 15));
        add(tfOmsch);

        tfAantal = new JTextField();
        tfAantal.setBounds(110, 85, 140, 20);
        tfAantal.setFont(new Font("Times New Roman", 0, 15));
        add(tfAantal);

        tfPrijs = new JTextField();
        tfPrijs.setBounds(110, 115, 140, 20);
        tfPrijs.setFont(new Font("Times New Roman", 0, 15));
        add(tfPrijs);
    }

    private void jBtSluitenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtSluitenActionPerformed
        main.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jBtSluitenActionPerformed

    private void jBtAanpassenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtAanpassenActionPerformed
        if (soort.equals("Klant")) {
            try {
                int id = klanten.get(jCbSelect.getSelectedIndex()).getId();
                String naam, adres;
                if (!tfNaam.getText().equals("")) {
                    naam = tfNaam.getText();
                } else {
                    naam = klanten.get(jCbSelect.getSelectedIndex()).getNaam();
                }
                if (!tfAdres.getText().equals("")) {
                    adres = tfAdres.getText();
                } else {
                    adres = klanten.get(jCbSelect.getSelectedIndex()).getAdres();
                }

                Klant kl = new Klant(id, naam, adres);
                if (main.getMagazijn().VeranderKlant(kl)) {
                    JOptionPane op = new JOptionPane();
                    op.showMessageDialog(null, "De verandering(en) zijn correct toegepast.", "Gelukt", JOptionPane.OK_OPTION);
                } else {
                    JOptionPane op = new JOptionPane();
                    op.showMessageDialog(null, "De verandering(en) zijn niet correct toegepast.", "Fout", JOptionPane.OK_OPTION);
                }

            } catch (Exception e) {
                JOptionPane op = new JOptionPane();
                op.showMessageDialog(null, "De waardes zijn niet correct ingevuld!", "Fout", JOptionPane.ERROR_MESSAGE);
            }
        } else if (soort.equals("Onderdeel")) {
            try {
                int code = onderdelen.get(jCbSelect.getSelectedIndex()).getCode();
                String omsch;
                int aantal, prijs;

                if (!tfOmsch.getText().equals("")) {
                    omsch = tfOmsch.getText();
                } else {
                    omsch = onderdelen.get(jCbSelect.getSelectedIndex()).getOmschrijving();
                }

                if (!tfAantal.getText().equals("")) {
                    aantal = Integer.parseInt(tfAantal.getText());
                } else {
                    aantal = onderdelen.get(jCbSelect.getSelectedIndex()).getAantal();
                }

                if (!tfPrijs.getText().equals("")) {
                    prijs = Integer.parseInt(tfPrijs.getText());
                } else {
                    prijs = onderdelen.get(jCbSelect.getSelectedIndex()).getPrijs();
                }

                Onderdeel od = new Onderdeel(code, omsch, aantal, prijs);
                if (main.getMagazijn().VeranderOnderdeel(od)) {
                    JOptionPane op = new JOptionPane();
                    op.showMessageDialog(null, "De verandering(en) zijn correct toegepast.", "Gelukt", JOptionPane.OK_OPTION);
                } else {
                    JOptionPane op = new JOptionPane();
                    op.showMessageDialog(null, "De verandering(en) zijn niet correct toegepast.", "Fout", JOptionPane.OK_OPTION);
                }

            } catch (Exception e) {
                JOptionPane op = new JOptionPane();
                op.showMessageDialog(null, "De waardes zijn niet correct ingevuld!", "Fout", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jBtAanpassenActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtAanpassen;
    private javax.swing.JButton jBtSluiten;
    private javax.swing.JComboBox jCbSelect;
    // End of variables declaration//GEN-END:variables
}
