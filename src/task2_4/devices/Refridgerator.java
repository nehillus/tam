package task2_4.devices;

import java.sql.ResultSet;
import java.sql.SQLException;

import task2_4.devicetypes.NotPortable;

public class Refridgerator extends NotPortable {
	
	private double capacity;
	private boolean hasFreezer;

	public Refridgerator() {
		super();
	}
	
	public Refridgerator(String manufacturer, String model, int generalPowerConsumption, double capacity, boolean hasFreezer) {
		super(manufacturer, model, generalPowerConsumption);
		this.capacity = capacity;
		this.hasFreezer = hasFreezer;
	}

	public Refridgerator (ResultSet resultSet) throws SQLException {
		super(resultSet.getString("manufacturer"), resultSet.getString("model"), resultSet.getInt("power_consumption"));
		this.capacity = resultSet.getDouble("capacity");
		this.hasFreezer = resultSet.getBoolean("has_freezer");
	}
	
	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public boolean isHasFreezer() {
		return hasFreezer;
	}

	public void setHasFreezer(boolean hasFreezer) {
		this.hasFreezer = hasFreezer;
	}

	@Override
	public String toString() {
		return "Refridgerator [capacity=" + capacity + ", hasFreezer=" + hasFreezer + ", isPluggedIn=" + isPluggedIn()
				+ ", manufacturer=" + getManufacturer() + ", model=" + getModel() + ", isOn=" + isOn()
				+ ", currentPowerConsumption=" + getCurrentPowerConsumption() + ", generalPowerConsumption="
				+ getGeneralPowerConsumption() + "]";
	}
}
