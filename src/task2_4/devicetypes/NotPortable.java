package task2_4.devicetypes;

import task2_4.basedevice.BaseDevice;

public abstract class NotPortable extends BaseDevice {
	
	private boolean isPluggedIn;
	
	public NotPortable() {
		super();
	}

	public NotPortable(String manufacturer, String model, int generalPowerConsumption) {
		super(manufacturer, model, generalPowerConsumption);
	}
	
	public boolean isPluggedIn() {
		return isPluggedIn;
	}

	public void setPluggedIn(boolean isPluggedIn) {
		this.isPluggedIn = isPluggedIn;
	}

	public void plugIn() {
		isPluggedIn = true;
	}
	
	public void unplug() {
		isPluggedIn = false;
		setOn(false);
	}
	
	@Override
	public void turnOn() {
		if (isPluggedIn && !isOn()) {
			setOn(true);
			setCurrentPowerConsumption(getGeneralPowerConsumption());
		}
	}

	@Override
	public void turnOff() {
		if (isOn() && isPluggedIn) {
			setCurrentPowerConsumption(0);
			setOn(false);
		}
	}
}
