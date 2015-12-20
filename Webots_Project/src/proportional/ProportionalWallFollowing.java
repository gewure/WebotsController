package proportional;

import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.LightSensor;

public class ProportionalWallFollowing extends ProportionalAbstract {

    // A01aLightProportional constructor
    public ProportionalWallFollowing() {
        super();

        distancesensors = new DistanceSensor[] { getDistanceSensor("ps0"),
                getDistanceSensor("ps1"),
                getDistanceSensor("ps2"),
                getDistanceSensor("ps3"),
                getDistanceSensor("ps4"),
                getDistanceSensor("ps5"),
                getDistanceSensor("ps6"),
                getDistanceSensor("ps7"),
        };

        for (int i = 0; i < distancesensors.length; i++) {
            distancesensors[i].enable(10);
        }

        _lightsensors = new LightSensor[0];

        _matrix = new double[][] { new double[] { 0, 0, 0, 0, 0, 1, 0, 0 },
                new double[] {  -1, 0, 0, 0, 0, 1, 0, -1 } };

        _sensorVector = new double[distancesensors.length];
    }

    public static void main(String[] args) {
        ProportionalWallFollowing controller = new ProportionalWallFollowing();
        controller.run();
    }
}