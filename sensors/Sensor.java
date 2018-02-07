package sensors;

public interface Sensor {
	void setPort(String port);
	float getValue();
	void reset();
	void close();
}
