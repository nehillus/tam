package task2_4.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import task2_4.basedevice.BaseDevice;
import task2_4.controller.Controller;
import task2_4.customexceptions.DisabledDeviceException;
import task2_4.customexceptions.EmptyDeviceListException;
import task2_4.customexceptions.NoDeviceFoundException;

public class Main {
	
	private static final String URL = "jdbc:mysql://localhost:3306/devices";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static final String PATH_TO_JSON_FOLDER = ".\\FileStorage\\JSON\\";

	public static void main(String[] args) throws DisabledDeviceException, IOException, SQLException, ClassNotFoundException, EmptyDeviceListException, NoDeviceFoundException {
//		Creating a new Connection instance
		Connection newConnection = Controller.establishDBConnection(URL, USERNAME, PASSWORD);
		
//		Generating devices list from entries of SQL Database
		ArrayList<BaseDevice> devicesFromMySQL = Controller.generateRandomDeviceListFromDatabase(newConnection);

//		Generating devices list from JSON files
		ArrayList<BaseDevice> devicesFromJSON = Controller.generateDeviceListFromJSON(PATH_TO_JSON_FOLDER);
		
//		Generating a merged devices list from devicesFromJSON and devicesFromMySQL
		ArrayList<BaseDevice> mergedListOfDevices = new ArrayList<BaseDevice>(devicesFromMySQL);
		mergedListOfDevices.addAll(devicesFromJSON);

//	    Turning on all devices
		Controller.turnOnAllDevices(mergedListOfDevices);
		
//		Output device list
		System.out.println("Initial list:");
		Controller.outputListToConsole(mergedListOfDevices);

//		Output total power consumption
		System.out.printf("Total power consumption: %d\n" , Controller.countTotalPowerConsumption(mergedListOfDevices));
		System.out.println();
		
//		Sort the list by current power consumption
		Controller.sortDevicesListByPowerConsumption(mergedListOfDevices);
		
//		Output sorted device list in console
		System.out.println("Sorted list:");
		Controller.outputListToConsole(mergedListOfDevices);

//		Output sorted device list in a txt file
		Controller.outputListToFile(mergedListOfDevices);
		
//		Find device by provided power consumption range
		int minPowerConsumptionValue = 7;
		int maxPowerConsumptionValue = 8;
		ArrayList<BaseDevice> devicesByCurrentPowerConsumptionRange = Controller.findDeviceByCurrentPowerConsumptionRange(mergedListOfDevices, minPowerConsumptionValue, maxPowerConsumptionValue); 
		System.out.println("Devices with power consumption between " + minPowerConsumptionValue + " and " + maxPowerConsumptionValue +":");
		Controller.outputListToConsole(devicesByCurrentPowerConsumptionRange);
	}
}
