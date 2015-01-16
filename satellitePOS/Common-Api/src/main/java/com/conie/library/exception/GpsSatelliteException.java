package com.conie.library.exception;

import java.io.Serializable;

public class GpsSatelliteException extends Exception implements Serializable {
	private static final long	serialVersionUID		= -8408057549698109479L;
	public static final int		CONSTRAINT_EXCEPTION	= 0xff000;
	public static final int		NUMBER_FORMAT			= 0xaaaaa;
	private int					code;
	private String				message;

	public GpsSatelliteException(){
		
	}
	public GpsSatelliteException(int code, String message) {
		this.message = message;
		this.code = code;
	}
	
	public GpsSatelliteException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
