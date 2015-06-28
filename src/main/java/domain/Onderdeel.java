package domain;

public class Onderdeel {

    private final int code;

    private final String omschrijving;

    private int aantal;

    private final int prijs;

    public Onderdeel(int code, String omschrijving, int aantal, int prijs) {
        this.code = code;
        this.omschrijving = omschrijving;
        this.aantal = aantal;
        this.prijs = prijs;
    }

    public int getCode() {
        return this.code;
    }

    public String getOmschrijving() {
        return this.omschrijving;
    }

    public int getAantal() {
        return this.aantal;
    }

    public int getPrijs() {
        return this.prijs;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }
}
