package filepersistence;

/**
 * We assume: Each sensor gets its own storage engine. There wont be a parameter
 * sensor name.
 */
public interface SensorDataStorage {
    /**
     * This method can be called by a sensor to save a data set.
     * @param time UNIX time when measurement took place
     * @param values sensor data
     * @throws PersistenceException if something unexpected happened. Insufficient right, medium broken, offline..
     */
    void saveData(long time, float[] values) throws PersistenceException;

    /**
     * This method can be called by a sensor to read data at a specific position.
     * @param position specific position in saved data
     * @throws PersistenceException if something unexpected happened. Insufficient right, medium broken, offline..
     * @return SensorData object
     */
    SensorData readData(int position) throws PersistenceException;

    /**
     * This method can be called by a sensor to read data at a specific position.
     * @throws PersistenceException if something unexpected happened. Insufficient right, medium broken, offline..
     * @return SensorData[] array
     */
    SensorData[] readAllData() throws PersistenceException;
}