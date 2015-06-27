package DAO;

import domain.Factuur;
import domain.FactuurRegel;
import domain.Klant;
import domain.Onderdeel;
import java.util.ArrayList;

public interface Magazijn {

    public ArrayList<Onderdeel> GetOnderdelen();

    public Onderdeel GetOnderdeel(int code);

    public ArrayList<Klant> GetKlanten();

    public ArrayList<Factuur> GetFacturen();

    public int VoegOnderdeelToe(String omschrijving, int aantal, int prijs);

    public int VoegKlantToe(String naam, String adres);

    public int VoegFactuurToe(int klantId, ArrayList<FactuurRegel> onderdelen);

    public boolean VerwijderKlant(int klantId);

    public boolean VerwijderOnderdeel(int onderdeelCode);

    public boolean VeranderKlant(Klant klant);

    public boolean VeranderOnderdeel(Onderdeel onderdeel);
}
