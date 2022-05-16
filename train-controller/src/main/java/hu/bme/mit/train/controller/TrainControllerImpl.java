package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	

	@Override
	public void followSpeed() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask()){
			@Override
			public void run(){
				if (referenceSpeed < 0) {
					referenceSpeed = 0;
				} else {
					if(referenceSpeed+step > 0) {
						referenceSpeed += step;
					} else {
						referenceSpeed = 0;
					}
				}
			enforceSpeedLimit();
			}	
		}, 0, 3000);	
		
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}

}
