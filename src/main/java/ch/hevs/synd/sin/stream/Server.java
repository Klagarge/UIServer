package ch.hevs.synd.sin.stream;

import ch.hevs.synd.sin.UICommands;
import ch.hevs.synd.sin.UIConstants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(UIConstants.UI_SERVER_PORT)){
            System.out.println("Server is listening on port " + UIConstants.UI_SERVER_PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                new ServerThread(socket).start();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
