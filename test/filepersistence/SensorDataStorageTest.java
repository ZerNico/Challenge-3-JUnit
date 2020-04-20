package filepersistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class SensorDataStorageTest {

    @Before
    @After
    public void clear() {
        SensorDataStorage storage = new SensorDataStorageImpl("testSensor");
        File f = new File("SensorData.txt");
        f.delete();
    }

    @Test
    public void gutTest1() throws PersistenceException {
        SensorDataStorage storage = new SensorDataStorageImpl("testSensor");
        long time = System.currentTimeMillis();
        float[] valueSet = new float[2];
        valueSet[0] = 1.5f;
        valueSet[1] = 0.3f;
        storage.saveData(time, valueSet);

        File f = new File("SensorData.txt");
        Assert.assertTrue(f.exists());
    }

    @Test
    public void gutTest2() throws PersistenceException {
        SensorDataStorage storage = new SensorDataStorageImpl("testSensor");
        long time = System.currentTimeMillis();
        float[] valueSet = new float[2];
        valueSet[0] = 1.5f;
        valueSet[1] = 0.3f;
        storage.saveData(time, valueSet);

        SensorData data = storage.readData(0);
        Assert.assertEquals(data.time, time);
        Assert.assertArrayEquals(data.valueSet, valueSet, 0);
    }

    @Test
    public void gutTest3() throws PersistenceException {
        SensorDataStorage storage = new SensorDataStorageImpl("testSensor");
        long time1 = System.currentTimeMillis();
        float[] valueSet1 = new float[2];
        valueSet1[0] = 1.5f;
        valueSet1[1] = 0.3f;
        storage.saveData(time1, valueSet1);

        long time2 = System.currentTimeMillis();
        float[] valueSet2 = new float[2];
        valueSet2[0] = 5.5f;
        valueSet2[1] = 0f;
        storage.saveData(time2, valueSet2);

        SensorData[] data = storage.readAllData();
        Assert.assertEquals(data[0].time, time1);
        Assert.assertEquals(data[1].time, time2);
        Assert.assertArrayEquals(data[0].valueSet, valueSet1, 0);
        Assert.assertArrayEquals(data[1].valueSet, valueSet2, 0);
    }

    @Test
    public void randTest1() throws PersistenceException {
        SensorDataStorage storage = new SensorDataStorageImpl("testSensor");
        long time = Long.MAX_VALUE;
        float[] valueSet = new float[2];
        valueSet[0] = Float.MAX_VALUE;
        valueSet[1] = Float.MAX_VALUE;
        storage.saveData(time, valueSet);

        File f = new File("SensorData.txt");
        Assert.assertTrue(f.exists());
    }

    @Test
    public void randTest2() throws PersistenceException {
        SensorDataStorage storage = new SensorDataStorageImpl("testSensor");
        long time = Long.MIN_VALUE;
        float[] valueSet = new float[2];
        valueSet[0] = Float.MIN_VALUE;
        valueSet[1] = Float.MIN_VALUE;
        storage.saveData(time, valueSet);

        File f = new File("SensorData.txt");
        Assert.assertTrue(f.exists());
    }

    @Test
    public void randTest3() throws PersistenceException {
        SensorDataStorage storage = new SensorDataStorageImpl("testSensor");
        long time = Long.MAX_VALUE;
        float[] valueSet = new float[2];
        valueSet[0] = Float.MAX_VALUE;
        valueSet[1] = Float.MAX_VALUE;
        storage.saveData(time, valueSet);

        SensorData data = storage.readData(0);
        Assert.assertEquals(data.time, time);
        Assert.assertArrayEquals(data.valueSet, valueSet, 0);
    }

    @Test
    public void randTest4() throws PersistenceException {
        SensorDataStorage storage = new SensorDataStorageImpl("testSensor");
        long time = Long.MIN_VALUE;
        float[] valueSet = new float[2];
        valueSet[0] = Float.MIN_VALUE;
        valueSet[1] = Float.MIN_VALUE;
        storage.saveData(time, valueSet);

        SensorData data = storage.readData(0);
        Assert.assertEquals(data.time, time);
        Assert.assertArrayEquals(data.valueSet, valueSet, 0);
    }

    @Test
    public void randTest5() throws PersistenceException {
        SensorDataStorage storage = new SensorDataStorageImpl("testSensor");
        long time1 = Long.MAX_VALUE;
        float[] valueSet1 = new float[2];
        valueSet1[0] = Float.MAX_VALUE;
        valueSet1[1] = Float.MAX_VALUE;
        storage.saveData(time1, valueSet1);

        long time2 = System.currentTimeMillis();
        float[] valueSet2 = new float[2];
        valueSet2[0] = 5.5f;
        valueSet2[1] = 0f;
        storage.saveData(time2, valueSet2);

        SensorData[] data = storage.readAllData();
        Assert.assertEquals(data[0].time, time1);
        Assert.assertEquals(data[1].time, time2);
        Assert.assertArrayEquals(data[0].valueSet, valueSet1, 0);
        Assert.assertArrayEquals(data[1].valueSet, valueSet2, 0);
    }

    @Test
    public void randTest6() throws PersistenceException {
        SensorDataStorage storage = new SensorDataStorageImpl("testSensor");
        long time1 = Long.MIN_VALUE;
        float[] valueSet1 = new float[2];
        valueSet1[0] = Float.MIN_VALUE;
        valueSet1[1] = Float.MIN_VALUE;
        storage.saveData(time1, valueSet1);

        long time2 = System.currentTimeMillis();
        float[] valueSet2 = new float[2];
        valueSet2[0] = 5.5f;
        valueSet2[1] = 0f;
        storage.saveData(time2, valueSet2);

        SensorData[] data = storage.readAllData();
        Assert.assertEquals(data[0].time, time1);
        Assert.assertEquals(data[1].time, time2);
        Assert.assertArrayEquals(data[0].valueSet, valueSet1, 0);
        Assert.assertArrayEquals(data[1].valueSet, valueSet2, 0);
    }

    @Test(expected=PersistenceException.class)
    public void schlechtTest1() throws PersistenceException {
        SensorDataStorage storage = new SensorDataStorageImpl("testSensor");
        long time = System.currentTimeMillis();
        float[] valueSet = new float[2];
        valueSet[0] = 1f;
        try {
            storage.saveData(time, valueSet);
        } catch (PersistenceException e) {

        }

        SensorData data = null;

        data = storage.readData(1);
        System.out.println(data.time);
    }

    @Test(expected=PersistenceException.class)
    public void schlechtTest2() throws PersistenceException {
        SensorDataStorage storage = new SensorDataStorageImpl("testSensor");
        long time = System.currentTimeMillis();
        float[] valueSet = new float[2];
        valueSet = null;

        storage.saveData(time, valueSet);
    }
}
