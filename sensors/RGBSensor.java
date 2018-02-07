package sensors;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.utility.Delay;

public class RGBSensor implements Sensor{

	private EV3ColorSensor colorSensor;
	private SensorMode colorSampler, rgbSampler;
	private float[] sample;

	public RGBSensor(String port) {
		setPort(port);
	}

	@Override
	public void setPort(String port){
		colorSensor = new EV3ColorSensor(LocalEV3.get().getPort(port));
		colorSampler = colorSensor.getColorIDMode();
		rgbSampler = colorSensor.getRGBMode();
		sample = new float[3];
	}
	
	@Override
	public float getValue(){
		colorSampler.fetchSample(sample, 0);
		return (int) (sample[0]*255);
	}
	
	public Color getColor(){
		rgbSampler = colorSensor.getRGBMode();
		rgbSampler.fetchSample(sample, 0);
		colorSensor.getRedMode().fetchSample(new float[3],0);
		return new Color(
				(int) (sample[0]*255),
				(int) (sample[1]*255),
				(int) (sample[2]*255));
	}
	
	@Override
	public void close(){
		colorSensor.close();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
