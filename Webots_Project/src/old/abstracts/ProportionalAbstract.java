import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.LightSensor;

public abstract class ProportionalAbstract extends DifferentialWheels {

    protected static int TIMESTEP = 16;

    protected static double SCALE = 5.0;

    protected static double CONSTANT = 60;

    protected double sensorVector[];
    protected double matrix[][];

    protected double speedLeft;
    protected double speedRight;

    protected DistanceSensor[] distancesensors;
    protected LightSensor[] lightsensors;

    public void run() {

        while (step(TIMESTEP) != -1) {

            readSensor();
            calculateSpeeds();
        }

    }

    private void calculateSpeeds() {

        double[] calcVector = operateMatrixVector();

        speedLeft = calcVector[0] * CONSTANT;
        speedRight = calcVector[1] * CONSTANT;
        scaleSpeed();
        setSpeed(speedLeft, speedRight);

    }

    private double[] operateMatrixVector() {

        if (matrix[0].length != sensorVector.length) {
            throw new IllegalStateException();
        }

        double[] result = new double[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i] += matrix[i][j] * sensorVector[j];
            }
        }

        return result;
    }

    private void scaleSpeed() {
        // scale down speed
        while (speedLeft > 1000d || speedRight > 1000d) {
            speedLeft -= SCALE;
            speedRight -= SCALE;
        }

        if (speedLeft < 0d) {
            speedLeft = 0d;
        }

        if (speedRight < 0d) {
            speedRight = 0d;
        }
    }

    private void readSensor() {

        int i = 0;

        while (i < lightsensors.length) {
            sensorVector[i] = lightsensors[i].getValue();
            i++;
        }

        int maxLength = distancesensors.length + lightsensors.length;
        int j = 0;

        while (i < maxLength) {
            sensorVector[j] = distancesensors[j].getValue();
            j++;
            i++;
        }
    }

}