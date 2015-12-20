package bangbang; /**
 * @author Martin Muench, Sonja Kopf
 * @date   09-12-2013
 *
 */
import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.LightSensor;

public class BangBangLightLove extends DifferentialWheels {

    private static int TIME_STEP = 16;
    private static int MAX_SENSOR_VALUE = 200;
    private static int S_LEFT = 0; // Sensor left
    private static int S_FRONT_LEFT = 1; // Sensor front left
    private static int S_FRONT_RIGHT = 2; // Sensor front right
    private static int S_RIGHT = 3; // Sensor left
    // private static int S_BACK_RIGHT = 4; // Sensor back right
    // private static int S_BACK_LEFT = 5; // Sensor back left
    private static int MIN_SPEED = 0; // min. motor speed
    private static int MAX_SPEED = 1000; // max. motor speed
    private LightSensor[] lightsensors; // Array with all light sensors
    private DistanceSensor[] distancesensors; // Array with all light sensors

    /**
     * Constructor
     */
    public BangBangLightLove() {

        //call superconstructor
        super();

        lightsensors = new LightSensor[] {
                getLightSensor("ls5"), // S_LEFT
                getLightSensor("ls7"), // S_FRONT_LEFT
                getLightSensor("ls0"), // S_FRONT_RIGHT
                getLightSensor("ls2"), // S_RIGHT
                getLightSensor("ls3"), // S_BACK_RIGHT
                getLightSensor("ls4")};// S_BACK_LEFT
        for (int i = 0; i < 6; i++)
            lightsensors[i].enable(10);

        // get distanceSensors and save them in array
        distancesensors = new DistanceSensor[] {
                getDistanceSensor("ps5"), // S_LEFT
                getDistanceSensor("ps7"), // S_FRONT_LEFT
                getDistanceSensor("ps0"), // S_FRONT_RIGHT
                getDistanceSensor("ps2")  // S_RIGHT
        };
        for (int i = 0; i < 4; i++)
            distancesensors[i].enable(10);

    }

    public void run() {

        while (step(TIME_STEP) != -1) {

            // stop if wall/light is too close
            if(distancesensors[S_FRONT_LEFT].getValue() >= MAX_SENSOR_VALUE || distancesensors[S_FRONT_RIGHT].getValue() >= MAX_SENSOR_VALUE) {
                stop();
            } else {
                if (lightsensors[S_FRONT_LEFT].getValue() < lightsensors[S_FRONT_RIGHT].getValue()
                        || lightsensors[S_LEFT].getValue() < lightsensors[S_RIGHT].getValue()) {
                    // drive to the left (where the light is)
                    driveLeft();
                } else if (lightsensors[S_FRONT_RIGHT].getValue() < lightsensors[S_FRONT_LEFT].getValue()
                        || lightsensors[S_RIGHT].getValue() < lightsensors[S_LEFT].getValue()) {
                    // drive to the right (where the light is)
                    driveRight();
                } else {
                    // drive forward
                    driveForward();
                }
            }
        }
    }

    /**
     * Robot drives to the right
     */
    private void driveRight() {
        setSpeed(MAX_SPEED, MIN_SPEED);
    }

    /**
     * Robot drives to the left
     */
    private void driveLeft() {
        setSpeed(MIN_SPEED, MAX_SPEED);
    }

    /**
     * Robot drives forward
     */
    private void driveForward() {
        setSpeed(MAX_SPEED, MAX_SPEED);
    }

    private void stop() {
        setSpeed(0, 0);
    }

    // This is the main program of your controller.
    // It creates an instance of your Robot subclass, launches its
    // function(s) and destroys it at the end of the execution.
    // Note that only one instance of Robot should be created in
    // a controller program.
    // The arguments of the main function can be specified by the
    // "controllerArgs" field of the Robot node

    public static void main(String[] args) {
        BangBangLightLove controller = new BangBangLightLove();
        controller.run();
    }
}