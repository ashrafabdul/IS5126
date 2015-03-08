package com.rtsing.www.resource;


import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.rtsing.www.cache.IRtSingCache;
import com.rtsing.www.exception.RtSingCacheException;
import com.rtsing.www.exception.RtSingResourceNotFoundException;


public class RtSingProperties {
	

	 private ResourceBundle rb = null;
	 private IRtSingCache propertyCache = null;
	 private static RtSingProperties properties = new RtSingProperties();

	  private RtSingProperties() {
	    try {
	      this.rb = ResourceBundle.getBundle("com.rtsing.www.resource.RtSingProperties");
	      //this.propertyCache = LoeGenCacheFactory.getCacheManager().getTempusPropertyCache();
	    } catch (MissingResourceException eir) {
	     // Log.error(LoeGenProperties.class, eir.getMessage());
	      throw new RtSingResourceNotFoundException("Not Able to Load Resource Bundle");
	    }
	  }

	  public static RtSingProperties getProperties() {
	    return properties;
	  }

	  public String getProperty(String key) throws RtSingCacheException, RtSingResourceNotFoundException {
	//   if (!this.propertyCache.isExists(key)) {
	  //   addToproperty(key);
	    //}
	    //return (String)this.propertyCache.getFromCache(key);
		  
		  String value = null;
		  try
		    {
		      value = this.rb.getString(key);
		      //this.propertyCache.addToCache(key, value);
		    } catch (MissingResourceException mre) {
		      //Log.error(LoeGenProperties.class, mre.getMessage());
		      throw new RtSingResourceNotFoundException("RtSingResourceNotFoundException: No resource named :" + key + ": found in RtSingProperties.properties");
		    }
		  return value;
	  }

	  private void addToproperty(String key)
	    throws RtSingCacheException, RtSingResourceNotFoundException
	  {
	    try
	    {
	      String value = this.rb.getString(key);
	      this.propertyCache.addToCache(key, value);
	    } catch (MissingResourceException mre) {
	      //Log.error(LoeGenProperties.class, mre.getMessage());
	      throw new RtSingResourceNotFoundException("RtSingResourceNotFoundException: No resource named :" + key + ": found in RtSingProperties.properties");
	    }
	  }

}
