package filepersistence;

public class SensorData {
    public String sensorName;
    public long time;
    public float[] valueSet;

    SensorData(String sensorName, long time, float[] valueSet) {
        this.valueSet = valueSet;
        this.time = time;
        this.sensorName = sensorName;
    }
}
