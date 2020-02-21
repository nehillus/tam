package task2_4.devices;

import java.sql.ResultSet;
import java.sql.SQLException;

import task2_4.devicetypes.Portable;

public class Headphones extends Portable {

	private String earCoupling;
	private int frequency;
	private String connectionType;
	
	public Headphones() {
		super();
	}

	public Headphones(String manufacturer, String model,  int generalPowerConsumption, int batteryAmount, String earCoupling, int frequency,
			String connectionType) {
		super(manufacturer, model, batteryAmount, generalPowerConsumption);
		this.earCoupling = earCoupling;
		this.frequency = frequency;
		this.connectionType = connectionType;
	}

	public Headphones (ResultSet resultSet) throws SQLException {
		super(resultSet.getString("manufacturer"), resultSet.getString("model"), resultSet.getInt("battery_amount"), resultSet.getInt("power_consumption"));
		this.earCoupling = resultSet.getString("ear_coupling");
		this.frequency = resultSet.getInt("frequency");
		this.connectionType = resultSet.getString("connection_type");
	}
	
	public String getEarCoupling() {
		return earCoupling;
	}

	public void setEarCoupling(String earCoupling) {
		this.earCoupling = earCoupling;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	@Override
	public String toString() {
		return "Headphones [earCoupling=" + earCoupling + ", frequency=" + frequency + ", connectionType="
				+ connectionType + ", isCharged=" + isCharged() + ", batteryAmount=" + getBatteryAmount()
				+ ", manufacturer=" + getManufacturer() + ", model=" + getModel() + ", isOn=" + isOn()
				+ ", currentPowerConsumption=" + getCurrentPowerConsumption() + ", generalPowerConsumption="
				+ getGeneralPowerConsumption() + "]";
	}
}
