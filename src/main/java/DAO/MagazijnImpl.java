package DAO;

import domain.Onderdeel;
import domain.Klant;
import domain.Factuur;
import GUI.APInterface;
import Magazijn.DatabaseConnectie;
import domain.FactuurRegel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MagazijnImpl implements Magazijn, APInterface {

    private DatabaseConnectie db;

    public MagazijnImpl() {
        try {
            db = new DatabaseConnectie();
        } catch (Exception ex) {
            Logger.getLogger(MagazijnImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Onderdeel> GetOnderdelen() {
        ArrayList<Onderdeel> ond = new ArrayList<Onderdeel>();
        try {
            ond = db.GetOnderdelen();
        } catch (Exception e) {
            System.out.println("Er is iets fout gegaan bij het ophalen van de onderdelen.  " + e.getMessage());
        }
        return ond;
    }

    public Onderdeel GetOnderdeel(int code) {
        Onderdeel ond = null;
        try {
            ond = db.GetOnderdeel(code);
        } catch (Exception e) {
            System.out.println("Er is iets fout gegaan bij het ophalen van het onderdeel.   " + e.getMessage());
        }
        return ond;
    }

    public ArrayList<Klant> GetKlanten() {
        ArrayList<Klant> klanten = null;
        try {
            klanten = db.GetKlanten();
        } catch (Exception e) {
            System.out.println("Er is iets fout gegaan bij het ophalen van de klanten.  " + e.getMessage());
        }
        return klanten;
    }

    public ArrayList<Factuur> GetFacturen() {
        ArrayList<Factuur> facturen = null;
        try {
            facturen = db.GetFacturen();
        } catch (Exception e) {
            System.out.println("Er is iets fout gegaan bij het ophalen van de klanten.  " + e.getMessage());
        }
        return facturen;
    }

    public int VoegOnderdeelToe(String omschrijving, int aantal, int prijs) {
        if (aantal < 0 || prijs < 0 || omschrijving == null || omschrijving.isEmpty()) {
            return -1;
        }

        try {
            ArrayList<Onderdeel> onderdelen = db.GetOnderdelen();
            int highest = 0;
            for (Onderdeel ond : onderdelen) {
                if (ond.getCode() > highest) {
                    highest = ond.getCode();
                }
            }
            Onderdeel ond = new Onderdeel((highest + 1), omschrijving, aantal, prijs);
            if (db.VoegOnderdeelToe(ond)) {
                return ond.getCode();
            }
        } catch (Exception e) {
            System.out.println("Er is iets fout gegaan met het toevoegen van het onderdeel.   " + e.getMessage());
        }
        return -1;
    }

    public int VoegKlantToe(String naam, String adres) {
        if (naam == null || naam.isEmpty() || adres == null || adres.isEmpty()) {
            return -1;
        }

        try {
            ArrayList<Klant> klanten = db.GetKlanten();
            int highest = 0;
            for (Klant kl : klanten) {
                if (kl.getId() > highest) {
                    highest = kl.getId();
                }
            }
            Klant klant = new Klant((highest + 1), naam, adres);
            if (db.VoegKlantToe(klant)) {
                return klant.getId();
            }
        } catch (Exception e) {
            System.out.println("Er is iets fout gegaan met het toevoegen van de klant.    " + e.getMessage());
        }
        return -1;
    }

    public int VoegFactuurToe(int klantId, ArrayList<FactuurRegel> onderdelen) {
        try {
            ArrayList<Factuur> facturen = db.GetFacturen();
            int factuurNr = 0;
            for (Factuur fc : facturen) {
                if (fc.getFactuurId() > factuurNr) {
                    factuurNr = fc.getFactuurId();
                }
            }

            ArrayList<Klant> klanten = db.GetKlanten();
            Klant klant = null;
            for (Klant klanten1 : klanten) {
                if (klanten1.getId() == klantId) {
                    klant = klanten1;
                }
            }
            if (klant == null) {
                return -1;
            }

            ArrayList<Onderdeel> ond = db.GetOnderdelen();
            for (FactuurRegel fr : onderdelen) {
                if (fr.getOnderdeelCode() < 1) {
                    return -1;
                }
                Onderdeel odd = null;
                for (Onderdeel ond1 : ond) {
                    if (ond1.getCode() == fr.getOnderdeelCode()) {
                        odd = ond1;
                    }
                }
                if (odd != null) {
                    if (odd.getAantal() < fr.getAantal()) {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }

            for (FactuurRegel fr : onderdelen) {
                Onderdeel on = null;
                for (Onderdeel ondr : ond) {
                    if (ondr.getCode() == fr.getOnderdeelCode()) {
                        int temp = ondr.getAantal() - fr.getAantal();
                        on = new Onderdeel(ondr.getCode(), ondr.getOmschrijving(), temp, ondr.getPrijs());
                    }
                }
                if (!db.VeranderOnderdeel(on.getCode(), on)) {
                    System.out.println("Er is iets fout gegaan bij db.VeranderOnderdeel");
                }
            }

            Calendar cal = new GregorianCalendar();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int day = cal.get(Calendar.DAY_OF_MONTH);
            String date = Integer.toString(day) + "-" + Integer.toString(month) + "-" + Integer.toString(year);

            if (db.VoegFactuurToe(new Factuur(date, klantId, (factuurNr + 1), onderdelen))) {
                return factuurNr;
            }
        } catch (Exception e) {
            System.out.println("Er is iets fout gegaan met het toevoegen van de factuur.   " + e.getMessage());
        }
        return -1;
    }

    public boolean VerwijderKlant(int klantId) {
        if (klantId >= 0) {
            try {
                ArrayList<Klant> klanten = db.GetKlanten();
                for (Klant kl : klanten) {
                    if (kl.getId() == klantId) {
                        if (db.VerwijderKlant(kl.getId())) {
                            return true;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Er is iets fout gegaan met het verwijderen van de klant.   " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean VerwijderOnderdeel(int onderdeelCode) {
        if (onderdeelCode >= 0) {
            try {
                ArrayList<Onderdeel> onderdelen = db.GetOnderdelen();
                for (Onderdeel odd : onderdelen) {
                    if (odd.getCode() == onderdeelCode) {
                        if (db.VerwijderOnderdeel(odd.getCode())) {
                            return true;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Er is iets fout gegaan met het verwijderen van het onderdeel.    " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean VeranderKlant(Klant klant) {
        if (klant != null && klant.getId() > 0 && klant.getAdres() != null && !klant.getAdres().equals("") && klant.getNaam() != null && !klant.getNaam().equals("")) {
            try {
                ArrayList<Klant> klanten = db.GetKlanten();
                for (Klant kl : klanten) {
                    if (kl.getId() == klant.getId()) {
                        if (db.VeranderKlant(klant.getId(), klant)) {
                            return true;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Er is iets fout gegaan met het veranderen van de klant.    " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean VeranderOnderdeel(Onderdeel onderdeel) {
        if (onderdeel.getCode() > 0 && onderdeel.getOmschrijving() != null && !onderdeel.getOmschrijving().equals("") && onderdeel.getAantal() > 0 && onderdeel.getPrijs() > 0) {
            try {
                ArrayList<Onderdeel> onderdelen = db.GetOnderdelen();
                for (Onderdeel odd : onderdelen) {
                    if (odd.getCode() == onderdeel.getCode()) {
                        if (db.VeranderOnderdeel(onderdeel.getCode(), onderdeel)) {
                            return true;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Er is iets fout gegaan met het veranderen van het onderdeel.    " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    public Object[] VraagOnderdeelOp(int onderdeelCode) {
        Object[] temp = null;
        try {
            temp = db.VraagOnderdeelOp(onderdeelCode);
        } catch (Exception e) {
            System.out.println("Er is iets fout gegaan bij het ophalen van het onderdeel.   " + e.getMessage());
        }
        return temp;
    }

    public int FactuurToeVoegen(int klantId, ArrayList<int[]> onderdelen) {
        try {
            ArrayList<Factuur> facturen = db.GetFacturen();
            int factuurNr = 0;
            for (Factuur fc : facturen) {
                if (fc.getFactuurId() > factuurNr) {
                    factuurNr = fc.getFactuurId();
                }
            }

            ArrayList<Klant> klanten = db.GetKlanten();
            Klant klant = null;
            for (Klant klanten1 : klanten) {
                if (klanten1.getId() == klantId) {
                    klant = klanten1;
                }
            }
            if (klant == null) {
                return -1;
            }

            ArrayList<Onderdeel> ond = db.GetOnderdelen();
            for (int[] fr : onderdelen) {
                if (fr[0] < 1) {
                    return -1;
                }
                Onderdeel odd = null;
                for (Onderdeel ond1 : ond) {
                    if (ond1.getCode() == fr[0]) {
                        odd = ond1;
                    }
                }
                if (odd != null) {
                    if (odd.getAantal() < fr[1]) {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }

            for (int[] fr : onderdelen) {
                Onderdeel on = null;
                for (Onderdeel ondr : ond) {
                    if (ondr.getCode() == fr[0]) {
                        int temp = ondr.getAantal() - fr[1];
                        on = new Onderdeel(ondr.getCode(), ondr.getOmschrijving(), temp, ondr.getPrijs());
                    }
                }
                if (!db.VeranderOnderdeel(on.getCode(), on)) {
                    System.out.println("Er is iets fout gegaan bij db.VeranderOnderdeel");
                }
            }

            Calendar cal = new GregorianCalendar();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int day = cal.get(Calendar.DAY_OF_MONTH);
            String date = Integer.toString(day) + "-" + Integer.toString(month) + "-" + Integer.toString(year);

            if (db.FactuurToevoegen(factuurNr + 1, klantId, date, onderdelen)) {
                return factuurNr;
            }
        } catch (Exception e) {
            System.out.println("Er is iets fout gegaan met het toevoegen van de factuur.   " + e.getMessage());
        }
        return -1;
    }
}
