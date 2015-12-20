package proportional;

import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.LightSensor;

import basic.ProportionalAbstract;

public class ProportionalPushBall extends ProportionalAbstract {

    /**
     * Constructor
     */
    public ProportionalPushBall() {
        // call super Constructor
        super();

        distancesensors = new DistanceSensor[] {
                getDistanceSensor("ps6"), // S_LEFT
                getDistanceSensor("ps7"), // S_FRONT_LEFT
                getDistanceSensor("ps0"), // S_FRONT_RIGHT
                getDistanceSensor("ps1") // S_RIGHT
        };

        for (int i = 0; i < distancesensors.length; i++)
            distancesensors[i].enable(10);

        lightsensors = new LightSensor[0];

        sensorVector = new double[distancesensors.length];
        matrix = new double[][] {
                new double[] { 0d, 1d, 2d, 3d },
                new double[] { 3d, 2d, 1d, 0d }
        };
    }

    public static void main(String[] args) {
        ProportionalPushBall controller = new ProportionalPushBall();
        controller.run();
    }
}