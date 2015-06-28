
import GUI.APInterface;
import DAO.MagazijnImpl;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class APInterfaceTest {

    MagazijnImpl beheer;

    public APInterfaceTest() {
        beheer = new MagazijnImpl();
    }

    @Test
    public void testVraagOnderdeelOp() {
        System.out.println("VraagOnderdeelOp");
        APInterface instance = new MagazijnImpl();
        int onderdeelCode = beheer.VoegOnderdeelToe("Test", 25, 25);
        Object[] expResult = new Object[3];
        expResult[0] = "Test";
        expResult[1] = 25;
        expResult[2] = 25;
        Object[] result = instance.VraagOnderdeelOp(onderdeelCode);
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testFactuurToeVoegen() {
        System.out.println("FactuurToeVoegen");
        int code1 = beheer.VoegOnderdeelToe("Test", 25, 25);
        int code2 = beheer.VoegOnderdeelToe("Test", 25, 25);
        int klant1 = beheer.VoegKlantToe("Test", "Test");
        int[] on1 = new int[2];
        int[] on2 = new int[2];
        on1[0] = code1;
        on1[1] = 5;
        on2[0] = code2;
        on2[1] = 5;

        ArrayList<int[]> onderdelen = new ArrayList<>();
        onderdelen.add(on1);
        onderdelen.add(on2);
        APInterface instance = new MagazijnImpl();
        int factuurID = instance.FactuurToeVoegen(klant1, onderdelen);
        assertTrue(factuurID != -1);
    }
}
