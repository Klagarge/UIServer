package ch.hevs.synd.sin.network.server;

import ch.hevs.synd.sin.UICommands;
import ch.hevs.synd.sin.UIConstants;
import ch.hevs.synd.sin.sensor.MeasurementType;
import ch.hevs.synd.sin.sensor.Sensor;
import ch.hevs.utils.Utility;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Transaction {

    private final InputStream _in;
    private final OutputStream _out;

    private final Sensor _uSensor;
    private final Sensor _iSensor;


    /* **************************************************************************************************************** */
    /*                                                                                                                  */
    /* Constructors                                                                                                     */
    /*                                                                                                                  */
    /* **************************************************************************************************************** */
    /**
     * Simple constructor
     * @param in		The input stream to read from
     * @param out		The output stream to write to
     * @param uSensor	Pointer to the Voltage sensor
     * @param iSensor	Pointer to the Ampere sensor
     */
    public Transaction(InputStream in, OutputStream out, Sensor uSensor, Sensor iSensor) {
        _in = in;
        _out = out;
        _uSensor = uSensor;
        _iSensor = iSensor;
    }


    /* **************************************************************************************************************** */
    /*                                                                                                                  */
    /* Public methods                                                                                                   */
    /*                                                                                                                  */
    /* **************************************************************************************************************** */
    public boolean processTransaction() {

        boolean r = true;
        while (r) {
            try {
                r = transaction(new String(Utility.readLine(_in)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }


    /* **************************************************************************************************************** */
    /*                                                                                                                  */
    /* Private methods                                                                                                  */
    /*                                                                                                                  */
    /* **************************************************************************************************************** */
    // Private method which will interpret the given command and react in to it...
    private boolean transaction(String dataIn) throws IOException {
        // TODO: Analyze here the given action (dataIn) and react in to it by sending the correct reply to the output stream
        String s;
        switch (dataIn){
            case "getu":
                s = String.valueOf(_uSensor.getMeasurement().getValue());
                s += _uSensor.getMeasurement().getType();
                Utility.writeLine(_out, s.getBytes());
                return true;
            case "geti":
                s = String.valueOf(_iSensor.getMeasurement().getValue());
                s += _iSensor.getMeasurement().getType();
                Utility.writeLine(_out, s.getBytes());
                return true;
            case "stop":
                return false;
            default:
                s = UICommands.INVALID_COMMND;
                Utility.writeLine(_out, s.getBytes());
                return true;
        }
    }


    /* **************************************************************************************************************** */
    /*                                                                                                                  */
    /* MAIN -- MAIN -- MAIN -- MAIN -- MAIN -- MAIN -- MAIN -- MAIN -- MAIN -- MAIN -- MAIN -- MAIN -- MAIN -- MAIN --  */
    /*                                                                                                                  */
    /* **************************************************************************************************************** */
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(UIConstants.UI_SERVER_PORT);
        Sensor uS = new Sensor(MeasurementType.Voltage,0);
        Sensor iS = new Sensor(MeasurementType.Current, 90);

        boolean process = true;
        while (true){
            Socket socket = ss.accept();
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            Transaction t = new Transaction(is, os, uS, iS);
            process = t.processTransaction();
            if(socket.isClosed()) continue;
            socket.close();

        }

        //uS.shutdown();
        //iS.shutdown();


    }
}
