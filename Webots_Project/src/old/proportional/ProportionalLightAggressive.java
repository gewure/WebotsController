

import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.LightSensor;

public class ProportionalLightAggressive extends ProportionalAbstract {

    /**
     * Constructor
     */
    public ProportionalLightAggressive() {
        // call super Constructor
        super();

        _lightsensors = new LightSensor[] { getLightSensor("ls5"), // S_LEFT
                getLightSensor("ls6"), // S_MID_LEFT
                getLightSensor("ls7"), // S_FRONT_LEFT
                getLightSensor("ls2"), // S_RIGHT
                getLightSensor("ls1"), // S_MID_RIGHT
                getLightSensor("ls0") }; // S_FRONT_RIGHT

        _distancesensors = new DistanceSensor[0];

        for (int i = 0; i < 6; i++) {
            _lightsensors[i].enable(10);
        }

        _matrix = new double[][] { new double[] { 0.1, 0.2, 0.3, 0, 0, 0 },
                new double[] {  0, 0, 0, 0.1, 0.2, 0.3 } };

        _sensorVector = new double[_lightsensors.length];
    }

    public static void main(String[] args) {
        ProportionalLightAggressive controller = new ProportionalLightAggressive();
        controller.run();
    }
}