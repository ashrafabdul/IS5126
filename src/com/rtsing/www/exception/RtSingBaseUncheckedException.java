package com.rtsing.www.exception;

public class RtSingBaseUncheckedException extends RuntimeException {


	public RtSingBaseUncheckedException(String errorMsg)
	  {
	    super(errorMsg);
	  }

	  public RtSingBaseUncheckedException(String errorMsg, Exception e) {
	    super(errorMsg, e);
	  }
}
