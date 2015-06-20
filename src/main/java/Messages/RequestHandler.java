package Messages;

import java.util.Arrays;

/**
 * Created by Laurence on 20/6/2015.
 */
public class RequestHandler {

    public String handleMessage(String request, String[] commands, String channel) {
        System.out.println(request + " on channel " + channel + " commands: " + Arrays.toString(commands));
        return "OKE!";
    }
}
