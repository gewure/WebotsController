import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.LightSensor;

public abstract class A01ProportionalAbstract extends
        DifferentialWheels {

    protected static int TIME_STEP = 32;

    protected static double SCALE = 5.0;

    protected static double CONSTANT = 60;

    protected double _sensorVector[];
    protected double _matrix[][];

    protected double _speedLeft;
    protected double _speedRight;

    protected DistanceSensor[] _distancesensors;
    protected LightSensor[] _lightsensors;

    public void run() {

        while (step(TIME_STEP) != -1) {

            readSensor();
            calculateSpeeds();
        }

    }

    private void calculateSpeeds() {

        double[] calcVector = operateMatrixVector();

        _speedLeft = calcVector[0] * CONSTANT;
        _speedRight = calcVector[1] * CONSTANT;
        scaleSpeed();
        setSpeed(_speedLeft, _speedRight);

    }

    private double[] operateMatrixVector() {

        if (_matrix[0].length != _sensorVector.length) {
            throw new IllegalStateException();
        }

        double[] result = new double[_matrix.length];

        for (int i = 0; i < _matrix.length; i++) {
            for (int j = 0; j < _matrix[i].length; j++) {
                result[i] += _matrix[i][j] * _sensorVector[j];
            }
        }

        return result;
    }

    private void scaleSpeed() {
        // scale down speed
        while (_speedLeft > 1000d || _speedRight > 1000d) {
            _speedLeft -= SCALE;
            _speedRight -= SCALE;
        }

        if (_speedLeft < 0d) {
            _speedLeft = 0d;
        }

        if (_speedRight < 0d) {
            _speedRight = 0d;
        }
    }

    private void readSensor() {

        int i = 0;

        while (i < _lightsensors.length) {
            _sensorVector[i] = _lightsensors[i].getValue();
            i++;
        }

        int maxLength = _distancesensors.length + _lightsensors.length;
        int j = 0;

        while (i < maxLength ) {
            _sensorVector[j] = _distancesensors[j].getValue();
            j++;
            i++;
        }
    }

}