package task2_4.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;

import task2_4.basedevice.BaseDevice;
import task2_4.customexceptions.DisabledDeviceException;
import task2_4.customexceptions.EmptyDeviceListException;
import task2_4.customexceptions.NoDeviceFoundException;
import task2_4.devices.Headphones;
import task2_4.devices.Laptop;
import task2_4.devices.Refridgerator;
import task2_4.devices.Smartphone;
import task2_4.devices.TV;
import task2_4.devicetypes.NotPortable;

public abstract class Controller {

	/**
	 * Creating a list of devices using build-in constructors with parameter values
	 * from MySQL DataBase
	 * 
	 * @param connection - current connection to MySQL DataBase
	 * @return - a list of devices
	 * @throws SQLException
	 */
	public static ArrayList<BaseDevice> generateRandomDeviceListFromDatabase(Connection connection)
			throws SQLException {
		ArrayList<BaseDevice> devices = new ArrayList<BaseDevice>();
		try {
			devices.add(new Smartphone(generateResultSet(connection, Smartphone.class)));
			devices.add(new Refridgerator(generateResultSet(connection, Refridgerator.class)));
			devices.add(new Headphones(generateResultSet(connection, Headphones.class)));
			devices.add(new Laptop(generateResultSet(connection, Laptop.class)));
			devices.add(new TV(generateResultSet(connection, TV.class)));
		} catch (SQLException e) {
			throw e;
		}
		return devices;
	}

	/**
	 * Creating a list of devices using data provided by JSON Files
	 * 
	 * @param pathToDirectory - path to folder with JSONs
	 * @return - a list of devices
	 * @throws FileNotFoundException
	 */
	public static ArrayList<BaseDevice> generateDeviceListFromJSON(String pathToDirectory)
			throws FileNotFoundException {
		File folder = new File(pathToDirectory);
		List<File> devicesJSONinFolder;
		try {
			devicesJSONinFolder = Arrays.asList(folder.listFiles());
		} catch (NullPointerException e) {
			throw new NullPointerException("No JSON files in the provided directory: " + pathToDirectory);
		}
		ArrayList<BaseDevice> devices = new ArrayList<BaseDevice>();
		for (File json : devicesJSONinFolder) {
			if (json.isFile()) {
				String deviceType = json.getName().replace("_JSON.json", "");
				switch (deviceType) {
				case "Smartphone":
					devices.add(readDeviceDataFromJSON(pathToDirectory, json.getName(), Smartphone.class));
					break;
				case "TV":
					devices.add(readDeviceDataFromJSON(pathToDirectory, json.getName(), TV.class));
					break;
				case "Laptop":
					devices.add(readDeviceDataFromJSON(pathToDirectory, json.getName(), Laptop.class));
					break;
				case "Headphones":
					devices.add(readDeviceDataFromJSON(pathToDirectory, json.getName(), Headphones.class));
					break;
				case "Refridgerator":
					devices.add(readDeviceDataFromJSON(pathToDirectory, json.getName(), Refridgerator.class));
					break;
				default:
					throw new FileNotFoundException("Unknown device type provided!");
				}
			}
		}
		return devices;
	}

	/**
	 * Returns randomly 1 from 3 entries from each device table from database,
	 * depending on provided deviceClass; Table for each device type has 3 entries
	 * with id's from 1 to 3. sqlQuery returns randomly device with id from 1 to 3
	 * 
	 * @param connection - current connection to MySQL DataBase
	 * @param device     - device type
	 * @return - SQL result for entered sql query
	 * @throws SQLException
	 */
	public static ResultSet generateResultSet(Connection connection, Class<? extends BaseDevice> deviceClass)
			throws SQLException {
		Random rn = new Random();
		String sqlQuery = "select * from " + deviceClass.getSimpleName().toLowerCase() + " where id = "
				+ (rn.nextInt(3) + 1);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sqlQuery);
		resultSet.next();
		return resultSet;
	}

	/**
	 * Turning on all the devices
	 * 
	 * @param devices - provided list of devices
	 * @throws EmptyDeviceListException
	 * 
	 */
	public static void turnOnAllDevices(ArrayList<BaseDevice> devices) throws EmptyDeviceListException {
		if (devices.isEmpty()) {
			throw new EmptyDeviceListException("Empty devices list provided!");
		}
		for (BaseDevice device : devices) {
			if (device instanceof NotPortable) {
				((NotPortable) device).plugIn();
			}
			device.turnOn();
		}
	}

	/**
	 * Turning on all the devices. Counting total current power consumption.
	 * 
	 * @param devices - generated list of devices
	 * 
	 * @return - total power consumption of all working devices
	 * @throws DisabledDeviceException
	 * @throws EmptyDeviceListException
	 * 
	 */
	public static int countTotalPowerConsumption(ArrayList<BaseDevice> devices)
			throws DisabledDeviceException, EmptyDeviceListException {
		if (devices.isEmpty()) {
			throw new EmptyDeviceListException("Empty devices list provided!");
		}
		int totalPowerConsumption = 0;
		for (BaseDevice device : devices) {
			if (device.getCurrentPowerConsumption() == 0) {
				throw new DisabledDeviceException("Device is not enabled: " + device.getClass().getSimpleName());
			}
			totalPowerConsumption += device.getCurrentPowerConsumption();
		}

		return totalPowerConsumption;
	}

	/**
	 * Sorting all devices based on their currentPowerConsumption value, descending
	 * 
	 * @param devices - not sorted list of devices
	 * 
	 * @return - sorted list of devices by currentPowerConsumption parameter
	 * @throws EmptyDeviceListException
	 * 
	 */
	public static ArrayList<BaseDevice> sortDevicesListByPowerConsumption(ArrayList<BaseDevice> devices)
			throws EmptyDeviceListException {
		if (devices.isEmpty()) {
			throw new EmptyDeviceListException("Empty devices list provided!");
		}
		Comparator<BaseDevice> compareCurrentPowerConsumption = new Comparator<BaseDevice>() {
			public int compare(BaseDevice deviceOne, BaseDevice deviceTwo) {
				return deviceTwo.getCurrentPowerConsumption() - deviceOne.getCurrentPowerConsumption();
			}
		};
		devices.sort(compareCurrentPowerConsumption);

		return devices;
	}

	/**
	 * Return devices based on provided currentPowerConsumption value range
	 * 
	 * @param devices                  - a list of devices
	 * 
	 * @param minPowerConsumptionValue - minimum currentPowerConsuption value
	 * 
	 * @param maxPowerConsumptionValue - maximum currentPowerConsuption value
	 *
	 * @return - sorted list of devices
	 * @throws NoDeviceException
	 * @throws EmptyDeviceListException
	 * 
	 */
	public static ArrayList<BaseDevice> findDeviceByCurrentPowerConsumptionRange(ArrayList<BaseDevice> devices,
			int minPowerConsumptionValue, int maxPowerConsumptionValue) throws NoDeviceFoundException {
		if (minPowerConsumptionValue > maxPowerConsumptionValue) {
			throw new ArithmeticException("minPowerConsumptionValue is more than maxPowerConsumptionValue!");
		}

		ArrayList<BaseDevice> foundDevices = new ArrayList<BaseDevice>();
		for (BaseDevice device : devices) {
			if (device.getCurrentPowerConsumption() >= minPowerConsumptionValue
					&& device.getCurrentPowerConsumption() <= maxPowerConsumptionValue) {
				foundDevices.add(device);
			}
		}

		if (foundDevices.isEmpty()) {
			throw new NoDeviceFoundException("No devices found for the provided range between "
					+ minPowerConsumptionValue + " and " + maxPowerConsumptionValue);
		}

		return foundDevices;
	}

	/**
	 * Outputs list of devices to console
	 * 
	 * @param devices - a list of devices
	 * @throws EmptyDeviceListException
	 * 
	 */
	public static void outputListToConsole(ArrayList<BaseDevice> devices) throws EmptyDeviceListException {
		if (devices.isEmpty()) {
			throw new EmptyDeviceListException("Empty devices list provided!");
		}
		for (BaseDevice device : devices) {
			System.out.printf("Device: %s %s %s. Power consumption: %d\n", device.getClass().getSimpleName(),
					device.getManufacturer(), device.getModel(), device.getCurrentPowerConsumption());
		}
		System.out.println();
	}

	/**
	 * Outputs list of devices to a txt file. One file is created for each separate
	 * date
	 * 
	 * @param devices - a list of devices
	 * @throws IOException
	 * 
	 */
	public static void outputListToFile(ArrayList<BaseDevice> devices) throws IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
		SimpleDateFormat dateFormatInFile = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		File file = new File(".\\FileStorage\\FileOutputs\\" + dateFormat.format(new Date()) + " - DeviceList.txt");
		FileWriter writer = new FileWriter(file, true);
		writer.append("Devices sorted by power consumption for " + dateFormatInFile.format(new Date()) + "\n");
		for (BaseDevice device : devices) {
			writer.append(dateFormatInFile.format(new Date()) + " - Device: " + device.getClass().getSimpleName() + " "
					+ device.getManufacturer() + " " + device.getModel() + ". Power consumption: "
					+ device.getCurrentPowerConsumption() + "\n");
		}
		writer.close();
	}

	/**
	 * Establishes connection to local database
	 * 
	 * @param url      - local database url
	 * @param user     - mysql username
	 * @param password - mysql password
	 * @return - Connection instance
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection establishDBConnection(String url, String user, String password) throws SQLException {
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		return DriverManager.getConnection(url, user, password);
	}

	/**
	 * Creates a Java object based on fields and values from JSON fields
	 * 
	 * @param filename    - name of the JSON file
	 * @param deviceClass - class of the created device object
	 * @return - BaseDevice object
	 * @throws FileNotFoundException
	 */
	public static BaseDevice readDeviceDataFromJSON(final String pathToDirectory, String fileName,
			Class<? extends BaseDevice> deviceClass) throws FileNotFoundException {
		Gson gson = new Gson();
		BaseDevice device;
		try {
			Reader reader = new FileReader(pathToDirectory + fileName);
			device = gson.fromJson(reader, deviceClass);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("File " + fileName + " was not found in directory " + pathToDirectory);
		}

		return device;
	}
}
