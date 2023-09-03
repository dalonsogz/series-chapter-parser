package com.eurobits.selenium.control.exception;

/**
 * Clase base de las excepciones de los tests de automatización.
 * 
 */
public class TestAutomatizacionException extends RuntimeException {
	private static final long serialVersionUID = 5817274345852859068L;

	/**
	 * Constructor a partir de un mensaje y una causa.
	 * @param msg Mensaje.
	 * @param cause Causa.
	 */
	public TestAutomatizacionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * Constructor a partir de un mensaje.
	 * @param msg Mensaje.
	 */
	public TestAutomatizacionException(String msg) {
		super(msg);
	}

	/**
	 * Constructor a partir de una causa base de la excepción.
	 * @param cause Causa de la excepción.
	 */
	public TestAutomatizacionException(Throwable cause) {
		super(cause);
	}
}
