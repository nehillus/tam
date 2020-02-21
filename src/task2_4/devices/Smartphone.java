package task2_4.devices;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import task2_4.basedevice.BaseDevice;
import task2_4.devicetypes.Portable;

public class Smartphone extends Portable{
	
	private double displaySize;
	private String operatingSystem;
	private int cameraResolution;
	private String processor;
	private int gbRam;
	
	public Smartphone(String manufacturer, String model, int generalPowerConsumption, int batteryAmount, double displaySize, String operatingSystem, int cameraResolution, String processor, int gbRam) {
		super(manufacturer, model, batteryAmount, generalPowerConsumption);
		this.displaySize = displaySize;
		this.operatingSystem = operatingSystem;
		this.cameraResolution = cameraResolution;
		this.processor = processor;
		this.gbRam = gbRam;
	}
	
	public Smartphone() {
		super();
	}

	public Smartphone (ResultSet resultSet) throws SQLException {
		super(resultSet.getString("manufacturer"), resultSet.getString("model"), resultSet.getInt("battery_amount"), resultSet.getInt("power_consumption"));
		this.displaySize = resultSet.getDouble("display_size");
		this.operatingSystem = resultSet.getString("operating_system");
		this.cameraResolution = resultSet.getInt("camera_resolution");
		this.processor = resultSet.getString("processor");
		this.gbRam = resultSet.getInt("gb_ram");
	}
	
	public Smartphone (String filename, BaseDevice deviceType) throws SQLException, FileNotFoundException {
		Gson gson = new Gson();
		Reader reader = new FileReader(".\\FileOutputs\\JSON\\" + filename);
		gson.fromJson(reader, deviceType.getClass());
	}
	
	public double getDisplaySize() {
		return displaySize;
	}

	public void setDisplaySize(double displaySize) {
		this.displaySize = displaySize;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public int getCameraResolution() {
		return cameraResolution;
	}

	public void setCameraResolution(int cameraResolution) {
		this.cameraResolution = cameraResolution;
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

	public String toString() {
		return "Smartphone [displaySize=" + displaySize + ", operatingSystem=" + operatingSystem + ", cameraResolution="
				+ cameraResolution + ", processor=" + processor + ", gbRam=" + gbRam + ", isCharged()=" + isCharged()
				+ ", batteryAmount=" + getBatteryAmount() + ", manufacturer=" + getManufacturer()
				+ ", model()=" + getModel() + ", isOn=" + isOn() + ", currentPowerConsumption="
				+ getCurrentPowerConsumption() + ", generalPowerConsumption=" + getGeneralPowerConsumption() + "]";
	}
}
