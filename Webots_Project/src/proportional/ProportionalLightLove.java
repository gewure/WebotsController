package proportional;

import basic.ProportionalAbstract;
import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.LightSensor;

public class ProportionalLightLove extends ProportionalAbstract {

    /**
     * Constructor
     */
    public ProportionalLightLove() {

        // call super Constructor
        super();
        lightsensors = new LightSensor[]{
                getLightSensor("ls5"), // S_LEFT
                getLightSensor("ls6"), // S_MID_LEFT
                getLightSensor("ls7"), // S_FRONT_LEFT
                getLightSensor("ls2"), // S_RIGHT
                getLightSensor("ls1"), // S_MID_RIGHT
                getLightSensor("ls0")}; // S_FRONT_RIGHT

        for (int i = 0; i < 6; i++)
            lightsensors[i].enable(10);

        // get distanceSensors and save them in array
        distancesensors = new DistanceSensor[]{
                getDistanceSensor("ps7"), // S_FRONT_LEFT
                getDistanceSensor("ps0"), // S_FRONT_RIGHT
        };

        for (int i = 0; i < 2; i++)
            distancesensors[i].enable(10);

        matrix = new double[][]{new double[]{0.1, 0.2, 0.3, 0, 0, 0, -3.0, -2.0},
                new double[]{0, 0, 0, 0.1, 0.2, 0.3, -3.0, -2.0}};

        sensorVector = new double[lightsensors.length + distancesensors.length];

    }

    public static void main(String[] args) {
        ProportionalLightLove controller = new ProportionalLightLove();
        controller.run();
    }
}