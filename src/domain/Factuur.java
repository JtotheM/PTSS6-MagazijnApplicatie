package domain;

import java.util.ArrayList;

public class Factuur {

    private final String datum;

    private final int klantId;

    private final int factuurId;

    private final ArrayList<FactuurRegel> onderdelen;

    public Factuur(String datum, int klantId, int factuurId, ArrayList<FactuurRegel> onderdelen) {
        this.datum = datum;
        this.klantId = klantId;
        this.factuurId = factuurId;
        if (onderdelen != null) {
            this.onderdelen = onderdelen;
        } else {
            this.onderdelen = new ArrayList<FactuurRegel>();
        }
    }

    public String getDatum() {
        return this.datum;
    }

    public int getKlantId() {
        return this.klantId;
    }

    public int getFactuurId() {
        return this.factuurId;
    }

    public ArrayList<FactuurRegel> getOnderdelen() {
        return this.onderdelen;
    }
}
