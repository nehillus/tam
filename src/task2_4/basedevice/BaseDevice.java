package task2_4.basedevice;

public abstract class BaseDevice{
	
	private int currentPowerConsumption; 
	private int generalPowerConsumption; 
	private String manufacturer;
	private String model;
	private boolean isOn;
	
	public BaseDevice() {
	}

	public BaseDevice(String manufacturer, String model, int generalPowerConsumption) {
		this.generalPowerConsumption = generalPowerConsumption;
		this.manufacturer = manufacturer;
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public boolean isOn() {
		return isOn;
	}

	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}
	
	public int getCurrentPowerConsumption() {
		return currentPowerConsumption;
	}

	protected void setCurrentPowerConsumption(int currentPowerConsumption) {
		this.currentPowerConsumption = currentPowerConsumption;
	}

	public int getGeneralPowerConsumption() {
		return generalPowerConsumption;
	}

	public void setGeneralPowerConsumption(int generalPowerConsumption) {
		this.generalPowerConsumption = generalPowerConsumption;
	}

	public abstract void turnOn();
	
	public abstract void turnOff();
}
