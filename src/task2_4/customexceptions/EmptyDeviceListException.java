package task2_4.customexceptions;

public class EmptyDeviceListException extends Exception{

	public EmptyDeviceListException() {
		super();
	}

	public EmptyDeviceListException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyDeviceListException(String message) {
		super(message);
	}

	public EmptyDeviceListException(Throwable cause) {
		super(cause);
	}
}
