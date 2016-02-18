package se.sml.sdj.service.exception;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = -1587405498053999703L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
