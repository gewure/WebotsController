package proportional;

import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.LightSensor;

import basic.AbstractProportionalController;
import basic.IController;
import strategies.Aggression;
import strategies.*;

public class ProportionalReactive extends AbstractProportionalController implements IController {

	private static int TIME_STEP = 16; // do something all 16 miliseconds

	private static int MAX_SENSOR_VALUE = 200;

	private static int S_LEFT = 0; // Sensor left
	private static int S_FRONT_LEFT = 1; // Sensor front left
	private static int S_FRONT_RIGHT = 2; // Sensor front right
	private static int S_RIGHT = 3; // Sensor left
	
	private static int MIN_SPEED = 0; // min. motor speed
	private static int MAX_SPEED = 1000; // max. motor speed

	private DistanceSensor[] pSensors; // Array with all distance sensors
	private LightSensor[] lSensors; //Array with light sensors
	
	public ProportionalReactive(Strategy strat, float[][] matrix) {
		super(strat, matrix);
	}

	@Override
	public void run() {
		
		/* all 16 miliseconds.. */
		while (step(TIME_STEP) != -1) {
			
			/* TODO */ 
			
//			// drive right - reached a wall
//			if (sensors[S_FRONT_LEFT].getValue() > MAX_SENSOR_VALUE			   
//			    || sensors[S_LEFT].getValue() > MAX_SENSOR_VALUE) {
//				
//				driveRight();	
//			}
//			
//			// drive left - reached a wall
//			if (sensors[S_FRONT_RIGHT].getValue() > MAX_SENSOR_VALUE			   
//			    || sensors[S_RIGHT].getValue() > MAX_SENSOR_VALUE) {
//				
//				driveLeft();
//			} 
//			
//			// drive forward if nothing is in front of the robot 
//			else {
//				driveForward();
//			}
			
		}

	}

	@Override
	public void driveLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void driveRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void driveForward() {
		// TODO Auto-generated method stub

	}

	public void main(String[] args) {
		
		

		if (strategy instanceof Aggression) {

		}

		if (strategy instanceof Love) {

		}

		if (strategy instanceof FearLeft) {

		}
		
		if (strategy instanceof FearRight) {

		}
	}

}
