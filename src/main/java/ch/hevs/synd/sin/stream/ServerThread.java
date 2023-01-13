package ch.hevs.synd.sin.stream;

import ch.hevs.synd.sin.network.server.Transaction;
import ch.hevs.synd.sin.sensor.MeasurementType;
import ch.hevs.synd.sin.sensor.Sensor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;
    static private Sensor uS = new Sensor(MeasurementType.Voltage,0);
    static private Sensor iS = new Sensor(MeasurementType.Current, 90);
    public ServerThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            Transaction t = new Transaction(is, os, uS, iS);
            if(!t.processTransaction()) socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
