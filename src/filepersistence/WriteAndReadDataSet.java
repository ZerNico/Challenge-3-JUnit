
package filepersistence;

public class WriteAndReadDataSet {
    public static void main(String[] args) {
        // three example data sets
        String sensorName = "MyGoodOldSensor"; // does not change

        long[] timeStamps = new long[3];
        timeStamps[0] = System.currentTimeMillis();
        timeStamps[1] = timeStamps[0] + 1; // milli sec later
        timeStamps[2] = timeStamps[1] + 1000; // second later

        float[][] values = new float[3][];
        // 1st measure .. just one value
        float[] valueSet = new float[1];
        values[0] = valueSet;
        valueSet[0] = (float) 1.5; // example value 1.5 degrees

        // 2nd measure .. just three values
        valueSet = new float[3];
        values[1] = valueSet;
        valueSet[0] = (float) 0.7;
        valueSet[1] = (float) 1.2;
        valueSet[2] = (float) 2.1;

        // 3rd measure .. two values
        valueSet = new float[2];
        values[2] = valueSet;
        valueSet[0] = (float) 0.7;
        valueSet[1] = (float) 1.2;


        // create new storage object
        SensorDataStorage storage1 = new SensorDataStorageImpl(sensorName);

        // write three data set into a file
        for (int i = 0; i < timeStamps.length; i++) {
            try {
                storage1.saveData(timeStamps[i], values[i]);
            } catch (PersistenceException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }

        // read and print data
        SensorData[] data = null;

        try {
            data = storage1.readAllData();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i].sensorName);
            System.out.println(data[i].time);
            for (int j = 0; j < data[i].valueSet.length; j++) {
                System.out.println(data[i].valueSet[j]);
            }
            System.out.println();
        }
    }
}