package GUI;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

public class MagazijnApplicatieApp extends SingleFrameApplication {

    @Override
    protected void startup() {
        try {
            show(new Magazijn());
        } catch (Exception ex) {
            Logger.getLogger(MagazijnApplicatieApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void configureWindow(java.awt.Window root) {
    }

    public static MagazijnApplicatieApp getApplication() {
        return Application.getInstance(MagazijnApplicatieApp.class);
    }

    public static void main(String[] args) {
        launch(MagazijnApplicatieApp.class, args);
    }
}
