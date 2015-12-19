// File:          A01dWallFollowing.java
// Date:
// Description:
// Author:        Martin Muench, Sonja Kopf
// Modifications:

// You may need to add other webots classes such as
//  import com.cyberbotics.webots.controller.DistanceSensor;
//  import com.cyberbotics.webots.controller.LED;
// or more simply:
//  import com.cyberbotics.webots.controller.*;
import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;
//import com.cyberbotics.webots.controller.LightSensor;


public class A02dWallFollowingBangBang extends DifferentialWheels {

    // You may need to define your own functions or variables, like
    // private LED led;


    private static int S_LEFT = 0; // Sensor left
    private static int S_FRONT_LEFT = 1; // Sensor front left
    private static int S_FRONT_RIGHT = 2; // Sensor front right
    private static int S_BACK_LEFT = 3; // Sensor left



    private static int MAX_SENSOR_VALUE = 70;
    private static int SENSOR_FIND_WALL = 120;
    // private static int MIN_SENSOR_VALUE = 1000;
    private static int TIME_STEP = 8;
    private static int MIN_SPEED = 0; // min. motor speed
    private static int MAX_SPEED = 700; // max. motor speed
    private DistanceSensor[] sensors; // Array with all distance sensors

    // A01dWallFollowing constructor
    public A02dWallFollowingBangBang() {

        // call the Robot constructor
        super();

        // get distance sensors and save them in array
        sensors = new DistanceSensor[] {
                getDistanceSensor("ps5"), // S_LEFT
                getDistanceSensor("ps7"), // S_FRONT_LEFT
                getDistanceSensor("ps0"), // S_FRONT_RIGHT
// 				getDistanceSensor("ps2"), // S_RIGHT
// 				getDistanceSensor("ps3"), // S_BACK_RIGHT
                getDistanceSensor("ps4")  // S_BACK_LEFT
        };
        for (int i = 0; i <4; i++)
            sensors[i].enable(10);

    }

    public void run() {

        boolean findWall = false;

        while (step(TIME_STEP) != -1) {
            if (sensors[S_FRONT_LEFT].getValue() > MAX_SENSOR_VALUE || sensors[S_FRONT_RIGHT].getValue() > MAX_SENSOR_VALUE) {
                driveRight();

                if (sensors[S_FRONT_LEFT].getValue() > SENSOR_FIND_WALL || sensors[S_FRONT_RIGHT].getValue() > SENSOR_FIND_WALL) {
                    findWall = true;
                }

            }

            else if (findWall && sensors[S_LEFT].getValue() < sensors[S_BACK_LEFT].getValue()){
                driveLeft();
            }


            else {
                // drive forward
                driveForward();

            }

            System.out.println("findWall: " + findWall + " --- FL: " + sensors[S_FRONT_LEFT].getValue() + " --- FR: " + sensors[S_FRONT_RIGHT].getValue() +
                    " --- L: " + sensors[S_LEFT].getValue() + " --- BL: " + sensors[S_BACK_LEFT].getValue());
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


//	private void driveStop() {
//		setSpeed(MIN_SPEED, MIN_SPEED);
//	}


    // This is the main program of your controller.
    // It creates an instance of your Robot subclass, launches its
    // function(s) and destroys it at the end of the execution.
    // Note that only one instance of Robot should be created in
    // a controller program.
    // The arguments of the main function can be specified by the
    // "controllerArgs" field of the Robot node
    public static void main(String[] args) {
        A02dWallFollowingBangBang controller = new A02dWallFollowingBangBang();
        controller.run();
    }
}