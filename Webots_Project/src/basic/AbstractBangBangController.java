package basic;

import com.cyberbotics.webots.controller.DifferentialWheels;

import strategies.Strategy;

public class AbstractBangBangController extends DifferentialWheels {
	
	public AbstractBangBangController(Strategy strat) {
		this.strategy = strat;
	}
	
	/* the strategy of the controller */
	public  final Strategy strategy;
	
}
