package filepersistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;

public class SensorDataStorageImpl implements SensorDataStorage {
    public String sensorName;

    public SensorDataStorageImpl(String sensorName) {
        this.sensorName = sensorName;
    }

    private int dataLength() throws PersistenceException {
        FileInputStream is = null;

        // open file
        try {
            is = new FileInputStream("SensorData.txt");
        } catch (IOException e) {
            throw new PersistenceException("Could not open file");
        }

        byte[] readBuffer = new byte[20000];

        // read file
        try {
            is.read(readBuffer);
        } catch (IOException e) {
            throw new PersistenceException("Could not read file");
        }

        String[] data = new String(readBuffer).split("\n");

        // close file
        try {
            is.close();
        } catch (IOException e) {
            throw new PersistenceException();
        }

        return data.length - 1;
    }

    @Override
    public void saveData(long time, float[] values) throws PersistenceException {
        FileOutputStream os = null;

        // don't allow writes with missing values
        if (values == null || values.length == 0) {
            throw new PersistenceException("No values given");
        }

        // open file
        try {
            // append to file
            os = new FileOutputStream("SensorData.txt", true);
        } catch (IOException e) {
            throw new PersistenceException("Could not open file");
        }

        // build up data string
        String dataToBeSaved = sensorName + "|" + time + "|";

        for (int i = 0; i < values.length; i++) {
            if (i == values.length - 1) {
                dataToBeSaved += values[i] + "\n";
            } else {
                dataToBeSaved += values[i] + "|";
            }
        }

        // write data to file
        try {
            os.write(dataToBeSaved.getBytes());
        } catch (IOException e) {
            throw new PersistenceException();
        }

        // close file
        try {
            os.close();
        } catch (IOException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public SensorData readData(int position) throws PersistenceException {
        FileInputStream is = null;

        // don't allow writes with missing values
        if (position > this.dataLength() - 1) {
            throw new PersistenceException("No data found at that position");
        }

        // open file
        try {
            is = new FileInputStream("SensorData.txt");
        } catch (IOException e) {
            throw new PersistenceException("Could not open file");
        }

        byte[] readBuffer = new byte[20000];

        // read from file
        try {
            is.read(readBuffer);
        } catch (IOException e) {
            throw new PersistenceException("Could not read file");
        }

        String sensorName = null;
        long time = 0;
        float[] valueSet = null;


        // seperate lines (for datasets)
        String[] data = new String(readBuffer).split("\n");

        // seperate data strings
        String[] sections = data[position].split(Pattern.quote("|"));
        valueSet = new float[sections.length - 2];
        for (int j = 0; j < sections.length; j++) {
            if (j == 0) {
                sensorName = sections[j];
            } else if (j == 1) {
                time = Long.parseLong(sections[j]);
            } else {
                valueSet[j - 2] = Float.parseFloat(sections[j]);
            }
        }

        // close file
        try {
            is.close();
        } catch (IOException e) {
            throw new PersistenceException();
        }

        return new SensorData(sensorName, time, valueSet);
    }

    @Override
    public SensorData[] readAllData() throws PersistenceException {

        // return all data
        SensorData[] data = new SensorData[this.dataLength()];
        for (int i = 0; i < this.dataLength(); i++) {
            data[i] = this.readData(i);
        }

        return data;
    }
}
