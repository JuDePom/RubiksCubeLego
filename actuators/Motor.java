package actuators;

import lejos.hardware.motor.NXTRegulatedMotor;

public class Motor {
	NXTRegulatedMotor motor;
	double wheelPerimeter = 1;
	double tachosPerCM = 0;
	
	boolean absolute = false;
	int currentPos = 0;
	
	public Motor(String port){
		switch(port){
		case "a": case "A":
			motor = lejos.hardware.motor.Motor.A;
			break;
		case "b": case "B":
			motor = lejos.hardware.motor.Motor.B;
			break;
		case "c": case "C":
			motor = lejos.hardware.motor.Motor.C;
			break;
		case "d": case "D":
			motor = lejos.hardware.motor.Motor.D;
			break;
		}
		motor.flt(false);
		reset();
	}
	
	public int getTachos(){
		return motor.getTachoCount();
	}
	
	public void reset(){
		motor.resetTachoCount();
		motor.stop();
	}
	
	public void stop(){
		motor.stop();
	}

	public void setSpeed(int speed) {		
		if (speed < 0){
			motor.backward();
			speed = -speed;
		} else {
			motor.forward();
		}
		motor.setSpeed(speed);
	}
	
	public void setAbsolute(){
		absolute = true;
	}
	
	public void setRelative(){
		absolute = false;
		currentPos = 0;
	}
	
	public void moveDegree(int position, int speed){
		motor.setSpeed(speed);
		motor.rotate(position - currentPos);
		motor.waitComplete();
		if (absolute){
			currentPos = position;
		}
	}
}
