// File:          A01dWallFollowingProportional.java
// Date:
// Description:
// Author:
// Modifications:

import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.LightSensor;

public class A01dWallFollowingProportional extends A01ProportionalAbstract {

    // A01aLightProportional constructor
    public A01dWallFollowingProportional() {
        super();

        _distancesensors = new DistanceSensor[] { getDistanceSensor("ps0"),
                getDistanceSensor("ps1"),
                getDistanceSensor("ps2"),
                getDistanceSensor("ps3"),
                getDistanceSensor("ps4"),
                getDistanceSensor("ps5"),
                getDistanceSensor("ps6"),
                getDistanceSensor("ps7"),
        };

        for (int i = 0; i < _distancesensors.length; i++) {
            _distancesensors[i].enable(10);
        }

        _lightsensors = new LightSensor[0];

        _matrix = new double[][] { new double[] { 0, 0, 0, 0, 0, 1, 0, 0 },
                new double[] {  -1, 0, 0, 0, 0, 1, 0, -1 } };

        _sensorVector = new double[_distancesensors.length];
    }

    public static void main(String[] args) {
        A01dWallFollowingProportional controller = new A01dWallFollowingProportional();
        controller.run();
    }
}