package GUI;

import java.util.ArrayList;

public interface APInterface {

    public Object[] VraagOnderdeelOp(int onderdeelCode);

    public int FactuurToeVoegen(int klantId, ArrayList<int[]> onderdelen);

}
