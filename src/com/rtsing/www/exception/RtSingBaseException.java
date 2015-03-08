package com.rtsing.www.exception;
public class RtSingBaseException extends Exception {
	
	public RtSingBaseException(String errorMsg)
	  {
	    super(errorMsg);
	  }

	  public RtSingBaseException(String errorMsg, Exception e) {
	    super(errorMsg, e);
	  }

}
