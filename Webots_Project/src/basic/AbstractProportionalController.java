package basic;

import com.cyberbotics.webots.controller.DifferentialWheels;

import strategies.Strategy;

public class AbstractProportionalController extends DifferentialWheels{
	
	public AbstractProportionalController(Strategy strat, float[][] matrix) {
		this.strategy = strat;
		this.matrix = matrix;
	}
	
	/* the strategy of the controller */
	public final Strategy strategy;
	
	/* the value-matrix that adjusts the dynamic movements of the bot */
	public  final float[][] matrix;
	

}
