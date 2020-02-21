package task2_4.devicetypes;

import task2_4.basedevice.BaseDevice;

public abstract class Portable extends BaseDevice {

	private boolean isCharged;
	private int batteryAmount;

	public Portable() {
		super();
	}

	public Portable(String manufacturer, String model, int batteryAmount, int generalPowerConsumption) {
		super(manufacturer, model, generalPowerConsumption);
		if (batteryAmount <= 100 && batteryAmount > 0) {
			if (batteryAmount == 0) {
				this.batteryAmount = batteryAmount;
			} else {
				this.batteryAmount = batteryAmount;
				this.isCharged = true;
			}
		} 
	}

	public boolean isCharged() {
		return isCharged;
	}

	public void setCharged(boolean isCharged) {
		this.isCharged = isCharged;
	}

	public int getBatteryAmount() {
		return batteryAmount;
	}

	public void setBatteryAmount(int batteryAmount) {
		this.batteryAmount = batteryAmount;
	}

	public void charge(int minutes) {
		if (minutes > 0) {
			for (int i = 1; i <= minutes; i++) {
				if (batteryAmount < 100) {
					batteryAmount++;
					if (batteryAmount == 100) {
						break;
					}
				}
			}
		} 
		if (batteryAmount > 0) {
			isCharged = true;
		}
	}

	@Override
	public void turnOn() {
		if (isCharged && !isOn()) {
			setOn(true);
			setCurrentPowerConsumption(getGeneralPowerConsumption());
		}
	}

	@Override
	public void turnOff() {
		if (isOn()) {
			setCurrentPowerConsumption(0);
			setOn(false);
		}
	}
}
