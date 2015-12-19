// File:          A01bLightStopProportional.java
// Date:
// Description:
// Author:
// Modifications:

// You may need to add other webots classes such as
//  import com.cyberbotics.webots.controller.DistanceSensor;
//  import com.cyberbotics.webots.controller.LED;
// or more simply:
//  import com.cyberbotics.webots.controller.*;
import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.LightSensor;

public class A01bLightStopProportional extends A01ProportionalAbstract {

    // A01aLightProportional constructor
    public A01bLightStopProportional() {
        super();
        _lightsensors = new LightSensor[] {
                getLightSensor("ls5"), // S_LEFT
                getLightSensor("ls6"), // S_MID_LEFT
                getLightSensor("ls7"), // S_FRONT_LEFT
                getLightSensor("ls2"), // S_RIGHT
                getLightSensor("ls1"), // S_MID_RIGHT
                getLightSensor("ls0") }; // S_FRONT_RIGHT

        for (int i = 0; i < 6; i++)
            _lightsensors[i].enable(10);

        // get distanceSensors and save them in array
        _distancesensors = new DistanceSensor[] {
                getDistanceSensor("ps7"), // S_FRONT_LEFT
                getDistanceSensor("ps0"), // S_FRONT_RIGHT
        };

        for (int i = 0; i < 2; i++)
            _distancesensors[i].enable(10);

        _matrix = new double[][] { new double[] { 0.1, 0.2, 0.3, 0, 0, 0, -3.0, -2.0 },
                new double[] {  0, 0, 0, 0.1, 0.2, 0.3, -3.0, -2.0 } };

        _sensorVector = new double[_lightsensors.length + _distancesensors.length];

    }

    public static void main(String[] args) {
        A01bLightStopProportional controller = new A01bLightStopProportional();
        controller.run();
    }
}