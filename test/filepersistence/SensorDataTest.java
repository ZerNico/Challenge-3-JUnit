package filepersistence;

import org.junit.Assert;
import org.junit.Test;

public class SensorDataTest {

    @Test
    public void gutTest1() {

        String sensorName = "testSensor";
        long time = System.currentTimeMillis();
        float[] valueSet = new float[2];
        valueSet[0] = 1.5f;
        valueSet[1] = 0.3f;

        SensorData data = new SensorData(sensorName, time, valueSet);

        Assert.assertEquals(data.time, time);
        Assert.assertEquals(data.sensorName, sensorName);
        Assert.assertArrayEquals(data.valueSet, valueSet, 0);
    }

    @Test
    public void randTest1() {
        String sensorName = "testSensor";
        long time = Long.MAX_VALUE;
        float[] valueSet = new float[2];
        valueSet[0] = Float.MAX_VALUE;
        valueSet[1] = Float.MAX_VALUE;

        SensorData data = new SensorData(sensorName, time, valueSet);

        Assert.assertEquals(data.time, time);
        Assert.assertEquals(data.sensorName, sensorName);
        Assert.assertArrayEquals(data.valueSet, valueSet, 0);
    }

    @Test
    public void randTest2() {
        String sensorName = "testSensor";
        long time = Long.MIN_VALUE;
        float[] valueSet = new float[2];
        valueSet[0] = Float.MIN_VALUE;
        valueSet[1] = Float.MIN_VALUE;

        SensorData data = new SensorData(sensorName, time, valueSet);

        Assert.assertEquals(data.time, time);
        Assert.assertEquals(data.sensorName, sensorName);
        Assert.assertArrayEquals(data.valueSet, valueSet, 0);
    }
}
