package com.rtsing.www.cachefactory;


import java.io.PrintStream;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.rtsing.www.cache.IRtSingCache;
import com.rtsing.www.cache.RtSingCache;
import com.rtsing.www.constants.RtSingConstants;
import com.rtsing.www.exception.RtSingCacheException;
import com.rtsing.www.exception.RtSingResourceNotFoundException;
import com.rtsing.www.log.Log;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;

public class RtSingCacheFactory {
	
	 private CacheManager cacheManager = null;
	  private static RtSingCacheFactory cacheFactory = new RtSingCacheFactory();

	  private RtSingCacheFactory() {
	    try {
	      ResourceBundle rs = ResourceBundle.getBundle(RtSingConstants.RTSING_PROPERTIES);
	      URL url = getClass().getResource(rs.getString(RtSingConstants.CACHE_CONFIG));
	      this.cacheManager = new CacheManager(url);
	    }
	    catch (MissingResourceException me)
	    {
	      Log.error(RtSingCacheFactory.class, me.getMessage());
	      throw new RtSingResourceNotFoundException("Not able to load the resource bundle in Cache factory");
	    } catch (CacheException e) {
	      Log.error(RtSingCacheFactory.class, e.getMessage());
	      throw new RtSingResourceNotFoundException("Not able to load ehcache.xml from Path defined in RtSingResourceBundle");
	    }
	  }

	  public static RtSingCacheFactory getCacheManager() {
	    return cacheFactory;
	  }

	  private Cache getCache(String cacheName)
	  {
	    Cache cache = this.cacheManager.getCache(cacheName);
	    if (cache != null)
	      System.out.println("cache is" + cache);
	    else {
	      System.out.println("cache is null");
	    }
	    return cache;
	  }
	  
	  public IRtSingCache getRtSingPropertyCache() throws RtSingCacheException {
		    return new RtSingCache(getCache("rtsingProperty"));
		  }

}
