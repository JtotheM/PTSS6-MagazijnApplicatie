package GUI;

import DAO.MagazijnImpl;
import Magazijn.JMSConnectie;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Magazijn extends javax.swing.JFrame {

    private DAO.Magazijn magazijn;
    private JMSConnectie jms;

    public DAO.Magazijn getMagazijn() {
        return magazijn;
    }

    public JMSConnectie getJms() {
        return jms;
    }

    public Magazijn() {
        initComponents();
        this.setTitle("Magazijn Applicatie");

        magazijn = new MagazijnImpl();
        jms = new JMSConnectie(magazijn);

        JLabel lbl1 = new JLabel("Magazijn Applicatie");
        lbl1.setBounds(50, 10, 200, 30);
        lbl1.setFont(new Font("Times New Roman", 1, 18));
        JLabel lbl2 = new JLabel("VSA door Rob Maas");
        lbl2.setBounds(70, 30, 150, 30);
        lbl2.setFont(new Font("Times New Roman", 1, 12));
        JLabel lbl3 = new JLabel("Maak gebruik van bovenstaand menu:");
        lbl3.setBounds(30, 60, 200, 30);
        lbl3.setFont(new Font("Times New Roman", 0, 12));
        JLabel lbl4 = new JLabel("om objecten te beheren.");
        lbl4.setBounds(70, 75, 200, 30);
        lbl4.setFont(new Font("Times New Roman", 0, 12));
        jPnMain.add(lbl3);
        jPnMain.add(lbl2);
        jPnMain.add(lbl1);
        jPnMain.add(lbl4);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPnMain = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMnOnd = new javax.swing.JMenu();
        jMnOndKijk = new javax.swing.JMenuItem();
        jMnOndVoeg = new javax.swing.JMenuItem();
        jMnOndAanpas = new javax.swing.JMenuItem();
        jMnOndVerw = new javax.swing.JMenuItem();
        jMnKlant = new javax.swing.JMenu();
        jMnKlantKijk = new javax.swing.JMenuItem();
        jMnKlantVoeg = new javax.swing.JMenuItem();
        jMnKlantAanpas = new javax.swing.JMenuItem();
        jMnKlantVerw = new javax.swing.JMenuItem();
        jMnFactuur = new javax.swing.JMenu();
        jMnFacKijk = new javax.swing.JMenuItem();
        jMnFacVoeg = new javax.swing.JMenuItem();
        jMnQuit = new javax.swing.JMenu();
        jMnStop = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(GUI.MagazijnApplicatieApp.class).getContext().getResourceMap(Magazijn.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        jPnMain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPnMain.setName("jPnMain"); // NOI18N

        javax.swing.GroupLayout jPnMainLayout = new javax.swing.GroupLayout(jPnMain);
        jPnMain.setLayout(jPnMainLayout);
        jPnMainLayout.setHorizontalGroup(
            jPnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 241, Short.MAX_VALUE)
        );
        jPnMainLayout.setVerticalGroup(
            jPnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
        );

        jMenuBar1.setName("jMenuBar1"); // NOI18N

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N

        jMnOnd.setText(resourceMap.getString("jMnOnd.text")); // NOI18N
        jMnOnd.setName("jMnOnd"); // NOI18N

        jMnOndKijk.setText(resourceMap.getString("jMnOndKijk.text")); // NOI18N
        jMnOndKijk.setName("jMnOndKijk"); // NOI18N
        jMnOndKijk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnOndKijkActionPerformed(evt);
            }
        });
        jMnOnd.add(jMnOndKijk);

        jMnOndVoeg.setText(resourceMap.getString("jMnOndVoeg.text")); // NOI18N
        jMnOndVoeg.setName("jMnOndVoeg"); // NOI18N
        jMnOndVoeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnOndVoegActionPerformed(evt);
            }
        });
        jMnOnd.add(jMnOndVoeg);

        jMnOndAanpas.setText(resourceMap.getString("jMnOndAanpas.text")); // NOI18N
        jMnOndAanpas.setName("jMnOndAanpas"); // NOI18N
        jMnOndAanpas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnOndAanpasActionPerformed(evt);
            }
        });
        jMnOnd.add(jMnOndAanpas);

        jMnOndVerw.setText(resourceMap.getString("jMnOndVerw.text")); // NOI18N
        jMnOndVerw.setName("jMnOndVerw"); // NOI18N
        jMnOndVerw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnOndVerwActionPerformed(evt);
            }
        });
        jMnOnd.add(jMnOndVerw);

        jMenu1.add(jMnOnd);

        jMnKlant.setText(resourceMap.getString("jMnKlant.text")); // NOI18N
        jMnKlant.setName("jMnKlant"); // NOI18N

        jMnKlantKijk.setText(resourceMap.getString("jMnKlantKijk.text")); // NOI18N
        jMnKlantKijk.setName("jMnKlantKijk"); // NOI18N
        jMnKlantKijk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnKlantKijkActionPerformed(evt);
            }
        });
        jMnKlant.add(jMnKlantKijk);

        jMnKlantVoeg.setText(resourceMap.getString("jMnKlantVoeg.text")); // NOI18N
        jMnKlantVoeg.setName("jMnKlantVoeg"); // NOI18N
        jMnKlantVoeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnKlantVoegActionPerformed(evt);
            }
        });
        jMnKlant.add(jMnKlantVoeg);

        jMnKlantAanpas.setText(resourceMap.getString("jMnKlantAanpas.text")); // NOI18N
        jMnKlantAanpas.setName("jMnKlantAanpas"); // NOI18N
        jMnKlantAanpas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnKlantAanpasActionPerformed(evt);
            }
        });
        jMnKlant.add(jMnKlantAanpas);

        jMnKlantVerw.setText(resourceMap.getString("jMnKlantVerw.text")); // NOI18N
        jMnKlantVerw.setName("jMnKlantVerw"); // NOI18N
        jMnKlantVerw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnKlantVerwActionPerformed(evt);
            }
        });
        jMnKlant.add(jMnKlantVerw);

        jMenu1.add(jMnKlant);

        jMnFactuur.setText(resourceMap.getString("jMnFactuur.text")); // NOI18N
        jMnFactuur.setName("jMnFactuur"); // NOI18N

        jMnFacKijk.setText(resourceMap.getString("jMnFacKijk.text")); // NOI18N
        jMnFacKijk.setName("jMnFacKijk"); // NOI18N
        jMnFacKijk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnFacKijkActionPerformed(evt);
            }
        });
        jMnFactuur.add(jMnFacKijk);

        jMnFacVoeg.setText(resourceMap.getString("jMnFacVoeg.text")); // NOI18N
        jMnFacVoeg.setName("jMnFacVoeg"); // NOI18N
        jMnFacVoeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnFacVoegActionPerformed(evt);
            }
        });
        jMnFactuur.add(jMnFacVoeg);

        jMenu1.add(jMnFactuur);

        jMenuBar1.add(jMenu1);

        jMnQuit.setText(resourceMap.getString("jMnQuit.text")); // NOI18N
        jMnQuit.setName("jMnQuit"); // NOI18N

        jMnStop.setText(resourceMap.getString("jMnStop.text")); // NOI18N
        jMnStop.setName("jMnStop"); // NOI18N
        jMnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnStopActionPerformed(evt);
            }
        });
        jMnQuit.add(jMnStop);

        jMenuBar1.add(jMnQuit);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPnMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPnMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnStopActionPerformed
        int temp = JOptionPane.showConfirmDialog(null, "De applicatie word afgesloten! Weet u het zeker?", "Warning!", JOptionPane.YES_NO_OPTION);
        if (temp == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMnStopActionPerformed

    private void jMnOndKijkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnOndKijkActionPerformed
        try {
            JFrameBekijken bekijken = new JFrameBekijken("Onderdeel", magazijn.GetOnderdelen(), null, null, this);
            bekijken.setVisible(true);
            this.setVisible(false);
        } catch (Exception e) {
            System.out.println("Er is iets fout gegaan bij het bekijken van de onderdelen." + e.getMessage());
        }
    }//GEN-LAST:event_jMnOndKijkActionPerformed

    private void jMnOndVoegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnOndVoegActionPerformed
        try {
            JFrameToevoegen toevoeg = new JFrameToevoegen("Onderdeel", this);
            toevoeg.setVisible(true);
            this.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMnOndVoegActionPerformed

    private void jMnOndAanpasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnOndAanpasActionPerformed
        try {
            JFrameAanpassen pas = new JFrameAanpassen("Onderdeel", this, null, magazijn.GetOnderdelen());
            pas.setVisible(true);
            this.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMnOndAanpasActionPerformed

    private void jMnOndVerwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnOndVerwActionPerformed
        try {
            JFrameVerwijderen verwijder = new JFrameVerwijderen("Onderdeel", magazijn.GetOnderdelen(), null, this);
            verwijder.setVisible(true);
            this.setVisible(false);
        } catch (Exception e) {
            System.out.println("Er is iets fout gegaan bij het verwijderen van het onderdeel." + e.getMessage());
        }
    }//GEN-LAST:event_jMnOndVerwActionPerformed

    private void jMnKlantKijkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnKlantKijkActionPerformed
        try {
            JFrameBekijken bekijken = new JFrameBekijken("Klant", null, magazijn.GetKlanten(), null, this);
            bekijken.setVisible(true);
            this.setVisible(false);
        } catch (Exception e) {
            System.out.println("Er is iets fout gegaan bij het bekijken van de klanten." + e.getMessage());
        }
    }//GEN-LAST:event_jMnKlantKijkActionPerformed

    private void jMnKlantVoegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnKlantVoegActionPerformed
        try {
            JFrameToevoegen toevoeg = new JFrameToevoegen("Klant", this);
            toevoeg.setVisible(true);
            this.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMnKlantVoegActionPerformed

    private void jMnKlantAanpasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnKlantAanpasActionPerformed
        try {
            JFrameAanpassen pas = new JFrameAanpassen("Klant", this, magazijn.GetKlanten(), null);
            pas.setVisible(true);
            this.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMnKlantAanpasActionPerformed

    private void jMnKlantVerwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnKlantVerwActionPerformed
        try {
            JFrameVerwijderen verwijder = new JFrameVerwijderen("Klant", null, magazijn.GetKlanten(), this);
            verwijder.setVisible(true);
            this.setVisible(false);
        } catch (Exception e) {
            System.out.println("Er is iets fout gegaan bij het bekijken van de klanten." + e.getMessage());
        }
    }//GEN-LAST:event_jMnKlantVerwActionPerformed

    private void jMnFacKijkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnFacKijkActionPerformed
        try {
            JFrameBekijken bekijken = new JFrameBekijken("Factuur", null, null, magazijn.GetFacturen(), this);
            bekijken.setVisible(true);
            this.setVisible(false);
        } catch (Exception e) {
            System.out.println("Er is iets fout gegaan bij het bekijken van de onderdelen." + e.getMessage());
        }
    }//GEN-LAST:event_jMnFacKijkActionPerformed

    private void jMnFacVoegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnFacVoegActionPerformed
        try {
            JFrameToevoegen toevoeg = new JFrameToevoegen("Factuur", this, magazijn.GetKlanten());
            toevoeg.setVisible(true);
            this.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMnFacVoegActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Magazijn().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMnFacKijk;
    private javax.swing.JMenuItem jMnFacVoeg;
    private javax.swing.JMenu jMnFactuur;
    private javax.swing.JMenu jMnKlant;
    private javax.swing.JMenuItem jMnKlantAanpas;
    private javax.swing.JMenuItem jMnKlantKijk;
    private javax.swing.JMenuItem jMnKlantVerw;
    private javax.swing.JMenuItem jMnKlantVoeg;
    private javax.swing.JMenu jMnOnd;
    private javax.swing.JMenuItem jMnOndAanpas;
    private javax.swing.JMenuItem jMnOndKijk;
    private javax.swing.JMenuItem jMnOndVerw;
    private javax.swing.JMenuItem jMnOndVoeg;
    private javax.swing.JMenu jMnQuit;
    private javax.swing.JMenuItem jMnStop;
    private javax.swing.JPanel jPnMain;
    // End of variables declaration//GEN-END:variables

}
