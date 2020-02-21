package task2_4.customexceptions;

public class NoDeviceFoundException extends Exception {

	public NoDeviceFoundException() {
		super();
	}

	public NoDeviceFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoDeviceFoundException(String message) {
		super(message);
	}

	public NoDeviceFoundException(Throwable cause) {
		super(cause);
	}
}
