package task2_4.devices;

import java.sql.ResultSet;
import java.sql.SQLException;

import task2_4.devicetypes.Portable;

public class Laptop extends Portable{

	private double screenSize;
	private String operatingSystem;
	private String processor;
	private int gbRam;
	private int hardDriveSize;

	public Laptop(String manufacturer, String model, int generalPowerConsumption, int batteryAmount, double screenSize, String operatingSystem, String processor, int gbRam, int hardDriveSize) {
		super(manufacturer, model, batteryAmount, generalPowerConsumption);
		this.screenSize = screenSize;
		this.operatingSystem = operatingSystem;
		this.processor = processor;
		this.gbRam = gbRam;
		this.hardDriveSize = hardDriveSize;
	}
	
	public Laptop() {
		super();
	}

	public Laptop (ResultSet resultSet) throws SQLException {
		super(resultSet.getString("manufacturer"), resultSet.getString("model"), resultSet.getInt("battery_amount"), resultSet.getInt("power_consumption"));
		this.screenSize = resultSet.getDouble("screen_size");
		this.operatingSystem = resultSet.getString("operating_system");
		this.processor = resultSet.getString("processor");
		this.gbRam = resultSet.getInt("gb_ram");
		this.hardDriveSize = resultSet.getInt("harddrive_size");
	}
	
	public double getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(double screenSize) {
		this.screenSize = screenSize;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public int getGbRam() {
		return gbRam;
	}

	public void setGbRam(int gbRam) {
		this.gbRam = gbRam;
	}

	public int getHardDriveSize() {
		return hardDriveSize;
	}

	public void setHardDriveSize(int hardDriveSize) {
		this.hardDriveSize = hardDriveSize;
	}

	@Override
	public String toString() {
		return "Laptop [screenSize=" + screenSize + ", operatingSystem=" + operatingSystem + ", processor=" + processor
				+ ", gbRam=" + gbRam + ", hardDriveSize=" + hardDriveSize + ", isCharged=" + isCharged()
				+ ", batteryAmount=" + getBatteryAmount() + ", manufacturer=" + getManufacturer()
				+ ", model=" + getModel() + ", isOn=" + isOn() + ", currentPowerConsumption="
				+ getCurrentPowerConsumption() + ", generalPowerConsumption=" + getGeneralPowerConsumption() + "]";
	}
}
