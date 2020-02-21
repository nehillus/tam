package task2_4.devices;

import java.sql.ResultSet;
import java.sql.SQLException;

import task2_4.devicetypes.NotPortable;

public class TV extends NotPortable {

	private String connectivityTechnology;
	private String displayType;
	private int displaySize;
	private int refreshRate;
	private int resolution;

	public TV() {
		super();
	}
	
	public TV(String manufacturer, String model, int generalPowerConsumption, String connectivityTechnology, String displayType, int displaySize, int refreshRate, int resolution) {
		super(manufacturer, model, generalPowerConsumption);
		this.connectivityTechnology = connectivityTechnology;
		this.displayType = displayType;
		this.displaySize = displaySize;
		this.refreshRate = refreshRate;
		this.resolution = resolution;
	}

	public TV (ResultSet resultSet) throws SQLException {
		super(resultSet.getString("manufacturer"), resultSet.getString("model"), resultSet.getInt("power_consumption"));
		this.connectivityTechnology = resultSet.getString("connectivity_technology");
		this.displayType = resultSet.getString("display_type");
		this.displaySize = resultSet.getInt("display_size");
		this.refreshRate = resultSet.getInt("refresh_rate");
		this.resolution = resultSet.getInt("resolution");
	}
	
	public String getConnectivityTechnology() {
		return connectivityTechnology;
	}

	public void setConnectivityTechnology(String connectivityTechnology) {
		this.connectivityTechnology = connectivityTechnology;
	}

	public String getDisplayType() {
		return displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

	public int getDisplaySize() {
		return displaySize;
	}

	public void setDisplaySize(int displaySize) {
		this.displaySize = displaySize;
	}

	public int getRefreshRate() {
		return refreshRate;
	}

	public void setRefreshRate(int refreshRate) {
		this.refreshRate = refreshRate;
	}

	public int getResolution() {
		return resolution;
	}

	public void setResolution(int resolution) {
		this.resolution = resolution;
	}

	@Override
	public String toString() {
		return "TV [connectivityTechnology=" + connectivityTechnology + ", displayType=" + displayType
				+ ", displaySize=" + displaySize + ", refreshRate=" + refreshRate + ", resolution=" + resolution
				+ ", isPluggedIn=" + isPluggedIn() + ", manufacturer=" + getManufacturer() + ", model="
				+ getModel() + ", isOn=" + isOn() + ", currentPowerConsumption=" + getCurrentPowerConsumption()
				+ ", generalPowerConsumption=" + getGeneralPowerConsumption() + "]";
	}
}