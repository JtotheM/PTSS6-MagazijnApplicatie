package Magazijn;

import domain.Klant;
import domain.Onderdeel;
import domain.Factuur;
import domain.FactuurRegel;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DatabaseConnectie {

    private Connection conn = null;

    private String url;

    private String username;

    private String password;

    private String serverEnPort;

    public DatabaseConnectie() {
        try {
            Properties pr = new Properties();
            pr.load(new FileInputStream("db.properties"));
            serverEnPort = pr.getProperty("ServerEnPort");
            username = pr.getProperty("Username");
            password = pr.getProperty("Password");
            url = "jdbc:oracle:thin:@" + serverEnPort + ":xe";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, this.username, this.password);
            conn.setAutoCommit(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "De database connectie is niet goed verlopen, fout bij IP:Port, Username of Password! \r\n" + ex.getMessage(), "Database - Error!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public ArrayList<Onderdeel> GetOnderdelen() {
        ArrayList<Onderdeel> ond = new ArrayList<Onderdeel>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            String sql = "SELECT * FROM ONDERDEEL";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                int code = rs.getInt("Code");
                String omschr = rs.getString("Omschrijving");
                int aantal = rs.getInt("Aantal");
                int prijs = rs.getInt("Prijs");
                ond.add(new Onderdeel(code, omschr, aantal, prijs));
            }
        } catch (Exception e) {
            System.out.println("FOUT!" + e.getMessage());
        } finally {
            try {
                st.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnectie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ond;
    }

    public ArrayList<FactuurRegel> GetFactuurRegels() {
        ArrayList<FactuurRegel> frs = new ArrayList<FactuurRegel>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            String sql = "SELECT * FROM FACTUURREGEL";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                int factuurid = rs.getInt("FactuurId");
                int onderdeelcode = rs.getInt("OnderdeelCode");
                int aantal = rs.getInt("Aantal");
                frs.add(new FactuurRegel(factuurid, onderdeelcode, aantal));
            }
        } catch (Exception e) {
            System.out.println("FOUT!!  " + e.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnectie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return frs;
    }

    public ArrayList<Factuur> GetFacturen() {
        ArrayList<Factuur> facturen = new ArrayList<Factuur>();
        ArrayList<FactuurRegel> regels = GetFactuurRegels();
        ResultSet rs = null;
        Statement st = null;
        try {
            st = conn.createStatement();
            String sql = "SELECT * FROM FACTUUR";
            rs = st.executeQuery(sql);
            int count = 0;
            while (rs.next()) {
                int code = rs.getInt("FactuurCode");
                int klantid = rs.getInt("KlantId");
                String datum = rs.getString("Datum");
                ArrayList<FactuurRegel> regelz = new ArrayList<FactuurRegel>();
                for (FactuurRegel rgl : regels) {
                    if (rgl.getFactuurId() == code) {
                        regelz.add(rgl);
                    }
                }
                facturen.add(new Factuur(datum, klantid, code, regelz));
                count++;
            }
        } catch (Exception e) {
            System.out.println("FOUT!  " + e.getMessage());
        } finally {
            try {
                st.close();
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnectie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return facturen;
    }

    public ArrayList<Klant> GetKlanten() {
        ArrayList<Klant> klanten = new ArrayList<Klant>();
        ResultSet rs = null;
        Statement st = null;
        try {
            st = conn.createStatement();
            String sql = "SELECT * FROM KLANT";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                int code = rs.getInt("Id");
                String naam = rs.getString("Naam");
                String adres = rs.getString("Adres");
                Klant kl = new Klant(code, naam, adres);
                klanten.add(kl);
            }

        } catch (Exception e) {
            System.out.println("FOUT!  " + e.getMessage());
        } finally {
            try {
                st.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnectie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return klanten;
    }

    public Onderdeel GetOnderdeel(int onderdeelCode) {
        Onderdeel od = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            String sql = "SELECT * FROM ONDERDEEL WHERE CODE = " + Integer.toString(onderdeelCode);
            rs = st.executeQuery(sql);
            while (rs.next()) {
                int code = rs.getInt("Code");
                String omschr = rs.getString("Omschrijving");
                int aantal = rs.getInt("Aantal");
                int prijs = rs.getInt("Prijs");
                od = new Onderdeel(code, omschr, aantal, prijs);
            }
        } catch (Exception e) {
            System.out.println("FOUT!!   " + e.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnectie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return od;
    }

    public boolean VoegOnderdeelToe(Onderdeel onderdeel) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO ONDERDEEL VALUES(?,?,?,?)");
            st.setInt(1, onderdeel.getCode());
            st.setString(2, onderdeel.getOmschrijving());
            st.setInt(3, onderdeel.getAantal());
            st.setInt(4, onderdeel.getPrijs());
            st.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("FOUT!!!   " + ex.getMessage());
            return false;
        } finally {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnectie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean VoegKlantToe(Klant klant) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO KLANT VALUES (?,?,?)");
            st.setInt(1, klant.getId());
            st.setString(2, klant.getNaam());
            st.setString(3, klant.getAdres());
            st.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("FOUT!!  " + e.getMessage());
            return false;
        } finally {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnectie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void VoegFactuurRegelToe(int factuurid, FactuurRegel fr) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO FACTUURREGEL VALUES (?,?,?)");
            st.setInt(1, factuurid);
            st.setInt(2, fr.getOnderdeelCode());
            st.setInt(3, fr.getAantal());
            st.executeUpdate();
        } catch (Exception ex) {
            System.out.println("FOUT!!!    " + ex.getMessage());
        } finally {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnectie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean VoegFactuurToe(Factuur factuur) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO FACTUUR VALUES (?,?,?)");
            st.setInt(1, factuur.getFactuurId());
            st.setInt(2, factuur.getKlantId());
            st.setString(3, factuur.getDatum());
            st.executeUpdate();

            for (FactuurRegel fr : factuur.getOnderdelen()) {
                VoegFactuurRegelToe(factuur.getFactuurId(), fr);
            }
            return true;
        } catch (Exception e) {
            System.out.println("FOUT!!!   " + e.getMessage());
            return false;
        } finally {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnectie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean VerwijderOnderdeel(int id) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("DELETE FROM ONDERDEEL WHERE CODE = ?");
            pst.setInt(1, id);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("FOUT!!  " + e.getMessage());
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnectie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean VerwijderKlant(int id) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("DELETE FROM KLANT WHERE ID = ?");
            pst.setInt(1, id);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("FOUT!!!    " + e.getMessage());
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnectie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean VeranderOnderdeel(int onderdeelCode, Onderdeel nieuweGegevens) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE ONDERDEEL SET OMSCHRIJVING = ?, AANTAL = ?, PRIJS = ? WHERE CODE = ?");
            st.setString(1, nieuweGegevens.getOmschrijving());
            st.setInt(2, nieuweGegevens.getAantal());
            st.setInt(3, nieuweGegevens.getPrijs());
            st.setInt(4, onderdeelCode);
            st.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("FOUT!!!  " + e.getMessage());
            return false;
        } finally {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnectie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean VeranderKlant(int klantcode, Klant nieuweGegevens) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE KLANT SET NAAM = ?, ADRES = ? WHERE ID = ?");
            st.setString(1, nieuweGegevens.getNaam());
            st.setString(2, nieuweGegevens.getAdres());
            st.setInt(3, klantcode);
            st.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("FOUT!!!   " + e.getMessage());
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnectie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Object[] VraagOnderdeelOp(int onderdeelCode) {
        Object[] od = new Object[3];
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            String sql = "SELECT * FROM ONDERDEEL WHERE CODE = " + Integer.toString(onderdeelCode);
            rs = st.executeQuery(sql);
            while (rs.next()) {
                int code = rs.getInt("Code");
                String omschr = rs.getString("Omschrijving");
                int aantal = rs.getInt("Aantal");
                int prijs = rs.getInt("Prijs");
                od[0] = omschr;
                od[1] = prijs;
                od[2] = aantal;
            }
        } catch (Exception e) {
            System.out.println("FOUT!!   " + e.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnectie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return od;
    }

    public boolean FactuurToevoegen(int factuurid, int klantid, String datum, ArrayList<int[]> onderdelen) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO FACTUUR VALUES (?,?,?)");
            st.setInt(1, factuurid);
            st.setInt(2, klantid);
            st.setString(3, datum);
            st.executeUpdate();

            for (int[] ob : onderdelen) {
                FactuurRegelToevoegen(factuurid, ob[0], ob[1]);
            }
            return true;
        } catch (Exception e) {
            System.out.println("FOUT!!!   " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnectie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void FactuurRegelToevoegen(int factuurid, int onderdeelcode, int aantal) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO FACTUURREGEL VALUES (?,?,?)");
            st.setInt(1, factuurid);
            st.setInt(2, onderdeelcode);
            st.setInt(3, aantal);
            st.executeUpdate();
        } catch (Exception ex) {
            System.out.println("FOUT!!!    " + ex.getMessage());
        } finally {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnectie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
