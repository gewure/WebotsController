/**
 * @author Martin Muench, Sonja Kopf
 * @date   09-12-2013
 *
 */
import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;

public class BangBangBallFollowing extends DifferentialWheels {

    private static int TIME_STEP = 16;

    private static int S_FRONT_LEFT = 0; // Sensor front left
    private static int S_FRONT_RIGHT = 1; // Sensor front right
    private static int S_SIDEFRONT_RIGHT = 2; // Sensor back right
    private static int S_SIDEFRONT_LEFT = 3; // Sensor back left

    private static int MOV_AVERAGE_SIZE = 8;
    private static int VARIATION = 40;

    private double[] _frontLeft;
    private double[] _frontRight;
    private double[] _sideFrontRight;
    private double[] _sideFrontLeft;

    private int _i;

    private static int MIN_SPEED = 0; // min. motor speed
    private static int MAX_SPEED = 1000; // max. motor speed
    private DistanceSensor[] distancesensors; // Array with all distance sensors

    /**
     * Constructor
     */
    public BangBangBallFollowing() {

        // call super Constructor
        super();

        // initialize arrays for moving average
        _frontLeft = new double[MOV_AVERAGE_SIZE];
        _frontRight = new double[MOV_AVERAGE_SIZE];
        _sideFrontLeft = new double[MOV_AVERAGE_SIZE];
        _sideFrontRight = new double[MOV_AVERAGE_SIZE];
        _i = 0;

        distancesensors = new DistanceSensor[] {
                getDistanceSensor("ps7"), // S_FRONT_LEFT
                getDistanceSensor("ps0"), // S_FRONT_RIGHT
                getDistanceSensor("ps1"), // S_SIDEFRONT_RIGHT
                getDistanceSensor("ps6")  // S_SIDEFRONT_LEFT
        };
        for (int i = 0; i < 4; i++)
            distancesensors[i].enable(10);

    }

    public void run() {

        // balance ball in front of robot
        while (step(TIME_STEP) != -1) {
            // set sensor values in arrays to calculate moving average
            _frontLeft[_i] = distancesensors[S_FRONT_LEFT].getValue();
            _frontRight[_i] = distancesensors[S_FRONT_RIGHT].getValue();
            _sideFrontLeft[_i] = distancesensors[S_SIDEFRONT_LEFT].getValue();
            _sideFrontRight[_i] = distancesensors[S_SIDEFRONT_RIGHT].getValue();

            // calculate averages
            double avg_frontLeft = 0.0;
            double avg_frontRight = 0.0;
            double avg_sideFrontLeft = 0.0;
            double avg_sideFrontRight = 0.0;

            for (int j = 0; j < MOV_AVERAGE_SIZE; j++) {
                avg_frontLeft += _frontLeft[j];
                avg_frontRight += _frontRight[j];
                avg_sideFrontLeft += _sideFrontLeft[j];
                avg_sideFrontRight += _sideFrontRight[j];
            }

            avg_frontLeft = avg_frontLeft / MOV_AVERAGE_SIZE;
            avg_frontRight = avg_frontRight / MOV_AVERAGE_SIZE;
            avg_sideFrontLeft = avg_sideFrontLeft / MOV_AVERAGE_SIZE;
            avg_sideFrontRight = avg_sideFrontRight / MOV_AVERAGE_SIZE;


            // do stuff!
            if (distancesensors[S_FRONT_LEFT].getValue() > avg_frontLeft + VARIATION
                    || distancesensors[S_FRONT_RIGHT].getValue() > avg_frontRight + VARIATION
                    || distancesensors[S_SIDEFRONT_LEFT].getValue() > avg_sideFrontLeft + VARIATION
                    || distancesensors[S_SIDEFRONT_RIGHT].getValue() > avg_sideFrontRight + VARIATION) {
                // wait for next value

            } else {
                if ((distancesensors[S_FRONT_LEFT].getValue() > distancesensors[S_FRONT_RIGHT].getValue())
                        && (distancesensors[S_SIDEFRONT_LEFT].getValue() > distancesensors[S_SIDEFRONT_RIGHT].getValue())) {
                    driveLeft();

                } else if (distancesensors[S_FRONT_RIGHT].getValue() > distancesensors[S_FRONT_LEFT].getValue()
                        && distancesensors[S_SIDEFRONT_RIGHT].getValue() > distancesensors[S_SIDEFRONT_LEFT].getValue()) {
                    driveRight();
                } else {
                    driveForward();
                }
            }

            // increase counter for moving average arrays
            _i++;
            _i = _i % MOV_AVERAGE_SIZE;
        }

    }

    /**
     * Robot drives to the right
     */
    private void driveRight() {
        System.out.println("DRIVE RIGHT");
        setSpeed(MAX_SPEED, MIN_SPEED);
//		setSpeed(100, 0);
    }

    /**
     * Robot drives to the left
     */
    private void driveLeft() {
        System.out.println("DRIVE LEFT");
        setSpeed(MIN_SPEED, MAX_SPEED);
//		setSpeed(0, 100);
    }

    /**
     * Robot drives forward
     */
    private void driveForward() {
        System.out.println("DRIVE FORWARD");
        setSpeed(MAX_SPEED, MAX_SPEED);
        //setSpeed(250, 250);
    }

    public static void main(String[] args) {
        BangBangBallFollowing controller = new BangBangBallFollowing();
        controller.run();
    }
}