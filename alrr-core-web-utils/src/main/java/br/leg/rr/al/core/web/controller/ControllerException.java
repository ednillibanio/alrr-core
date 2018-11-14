package br.leg.rr.al.core.web.controller;

/**
 * @author Ednil Libanio da Costa Junior
 * @date 18-04-2018
 */
public class ControllerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2790777594544917104L;

	public ControllerException() {
		super();
	}

	public ControllerException(final String message, Throwable cause) {
		super(message, cause);
	}

	public ControllerException(final String message) {
		super(message);
	}

	public ControllerException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
