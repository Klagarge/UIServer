package ch.hevs.synd.sin.network.server;

import ch.hevs.synd.sin.sensor.Sensor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
        // TODO: Put your code here...
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
        return false;
    }


    /* **************************************************************************************************************** */
    /*                                                                                                                  */
    /* MAIN -- MAIN -- MAIN -- MAIN -- MAIN -- MAIN -- MAIN -- MAIN -- MAIN -- MAIN -- MAIN -- MAIN -- MAIN -- MAIN --  */
    /*                                                                                                                  */
    /* **************************************************************************************************************** */
    public static void main(String[] args) {
        // TODO: Put your code here
    }
}
