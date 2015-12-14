package basic;

import com.cyberbotics.webots.controller.DifferentialWheels;

public interface IController {

	public void run();
	public void driveLeft();
	public void driveRight();
	public void driveForward();
	
}
