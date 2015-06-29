package FontysApp;

import Frames.FontysAppFrame;

/**
 * RunFontysApp 
 * created by @author RY Jin on Jun 20, 2015
 */
public class RunFontysApp {

    public static void main(String[] args) {
        try {

            FontysAppFrame frame = new FontysAppFrame();

            frame.setVisible(
                    true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
