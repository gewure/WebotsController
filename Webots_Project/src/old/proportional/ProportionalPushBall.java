import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.LightSensor;

public class A01cPushBallProportional extends A01ProportionalAbstract {

    // A02cPushBallProportional constructor
    public A01cPushBallProportional() {
        super();

        // get distanceSensors and save them in array
        _distancesensors = new DistanceSensor[] {
                getDistanceSensor("ps6"), // S_LEFT
                getDistanceSensor("ps7"), // S_FRONT_LEFT
                getDistanceSensor("ps0"), // S_FRONT_RIGHT
                getDistanceSensor("ps1") // S_RIGHT
        };

        for (int i = 0; i < _distancesensors.length; i++)
            _distancesensors[i].enable(10);

        _lightsensors = new LightSensor[0];

        _sensorVector = new double[_distancesensors.length];
        _matrix = new double[][] {
                new double[] { 0d, 1d, 2d, 3d },
                new double[] { 3d, 2d, 1d, 0d }
        };
    }

    public static void main(String[] args) {
        A01cPushBallProportional controller = new A01cPushBallProportional();
        controller.run();
    }
}