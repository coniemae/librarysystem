package com.conie.library.exception;

public class GpsDataPersistenceException extends Exception {
	private static final long serialVersionUID = -4157041129342928840L;
	public enum ErrorCode{
		ACCOUNT_IS_ACTIVE		 (0xa0000),
		MAX_LIMIT 		 		 (0xabcde),
		CAST_EXCEPTION       	 (0xfffff),
		CONSTRAINT_EXCEPTION	 (0xff000),
		UNKNOWN_ERROR       	  	 (0x00000),
		USER_USERNAME_EXSIST	 (0xa0001),
		USER_DOES_NOT_EXSIST     (0xa0002),
		USER_TYPE_EXSIST  	     (0xa1001),
		USER_TYPE_NOT_EXIST 	 (0xa1002),
		USER_TYPE_IS_ACTIVE	 	 (0xa1003),
		VEHICLE_GROUP_EXIST	     (0xa2001), 
		DEVICE_EXSIST 	         (0xa2002),
		DRIVER_EXSIST       	 (0xa3001),
		DEVICE_NOT_EXIST	     (0xa4001),
		VEHICLE_EXIST	         (0xa5001),
		GEOFENCE_NOT_EXIST  	 (0xa6001),
		GEOFENCE_EXIST			 (0xa6002),
		POSITION_NOT_EXIST	 	 (0xa7001),
		POSITION_EXIST	 	 	 (0xa7002),
		PERSONNEL_GROUP_EXIST	 (0xa8001), 
		PERSONNEL_GROUP_NOT_EXIST(0xa8002),
		PERSONNEL_EXIST	 		 (0xa9001);
		
		private int errorCode;
			private ErrorCode(int errorCode) {
				this.errorCode=errorCode;
			}
			public int getErrorCode(){
				return errorCode;
			}
	}
	private ErrorCode code;
	private String message;
	
	public GpsDataPersistenceException(ErrorCode code ,String message) {
		this.message=message;
		this.code=code;
		
	}
	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorCode getCode() {
		return code;
	}

	public void setCode(ErrorCode code) {
		this.code = code;
	}
}
