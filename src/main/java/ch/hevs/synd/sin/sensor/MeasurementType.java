package ch.hevs.synd.sin.sensor;

/**
 * Definition of all available types of measurement
 *
 * @author  Patrice Rudaz
 * @date    December 2021
 * @version 1.0.0
 */
public enum MeasurementType {
    Voltage,
    Current;

    public String toString() {

        if (equals(Voltage))
            return " [V]";
        else if (equals(Current))
            return " [A]";
        else
            return "Unknown Type";
    }
}
