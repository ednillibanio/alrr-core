package br.leg.rr.al.core.dao;

public class BeanException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7275538078255916021L;

	public BeanException() {
		super();
	}

	public BeanException(final String message, Throwable cause) {
		super(message, cause);
	}

	public BeanException(final String message) {
		super(message);
	}

	public BeanException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
