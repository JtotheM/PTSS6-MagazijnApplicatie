package domain;

public class FactuurRegel {

    private final int factuurId;

    private final int onderdeelCode;

    private final int aantal;

    public FactuurRegel(int factuurId, int onderdeelCode, int aantal) {
        this.factuurId = factuurId;
        this.onderdeelCode = onderdeelCode;
        this.aantal = aantal;
    }

    public int getFactuurId() {
        return this.factuurId;
    }

    public int getOnderdeelCode() {
        return this.onderdeelCode;
    }

    public int getAantal() {
        return this.aantal;
    }
}
