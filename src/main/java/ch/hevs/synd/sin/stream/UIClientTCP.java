package ch.hevs.synd.sin.stream;

import ch.hevs.synd.sin.UICommands;
import ch.hevs.synd.sin.UIConstants;
import ch.hevs.utils.Utility;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Arrays;
import java.util.Objects;

public class UIClientTCP {
    public static void main(String[] args) {
        // Open a TCP socket
        try {
            Socket socket = new Socket("sdi.hevs.ch", UIConstants.UI_SERVER_PORT);
            getui(socket, 5, 5);
            //System.out.println("Receiv: \n" + getui(socket, 5, 5));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Server can not be reached");
            System.exit(-1);
        }
    }

    public static String get(Socket s, String cmd) throws IOException {
        OutputStream os = s.getOutputStream();
        InputStream is = s.getInputStream();
        Utility.writeLine(os, cmd.getBytes());
        return new String(Utility.readLine(is));
    }
    public static String getu(Socket s) throws IOException {
        return get(s, UICommands.GET_U_SHORT_COMMAND);
    }
    public static String geti(Socket s) throws IOException {
        return get(s, UICommands.GET_I_SHORT_COMMAND);
    }
    public static String getui(Socket s) throws IOException {
        String received = "";
        received += getu(s);
        received += "\n";
        received += geti(s);
        return received;
    }

    public static void getui(Socket s, int z, int n) throws IOException {
        OutputStream os = s.getOutputStream();
        InputStream is = s.getInputStream();
        String cmdU = UICommands.GET_U_SHORT_COMMAND + ", " + z*1000 + ", " + n;
        String cmdI = UICommands.GET_I_SHORT_COMMAND + ", " + z*1000 + ", " + n;
        //getu(s);
        Utility.writeLine(os, cmdU.getBytes());
        Utility.writeLine(os, cmdI.getBytes());

        while (true){
            System.out.println(new String(Utility.readLine(is)));
        }

    }
}
