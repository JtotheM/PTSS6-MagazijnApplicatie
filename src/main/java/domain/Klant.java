package domain;

public class Klant {

    private final int id;

    private final String naam;

    private final String adres;

    public Klant(int id, String naam, String adres) {
        this.id = id;
        this.naam = naam;
        this.adres = adres;
    }

    public String getNaam() {
        return this.naam;
    }

    public int getId() {
        return this.id;
    }

    public String getAdres() {
        return this.adres;
    }
}
