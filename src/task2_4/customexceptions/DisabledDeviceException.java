package task2_4.customexceptions;

public class DisabledDeviceException extends Exception {

	public DisabledDeviceException() {
		super();
	}

	public DisabledDeviceException(String message, Throwable cause) {
		super(message, cause);
	}

	public DisabledDeviceException(String message) {
		super(message);
	}

	public DisabledDeviceException(Throwable cause) {
		super(cause);
	}
}
