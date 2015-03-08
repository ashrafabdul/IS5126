package com.rtsing.www.cache;

import java.util.List;

import com.rtsing.www.exception.RtSingCacheException;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class RtSingCache implements IRtSingCache{
	
	private Cache cache;

	  public RtSingCache(Cache cache)
	  {
	    this.cache = cache;
	  }

	  public void addToCache(String key, Object value) throws RtSingCacheException {
	    if (isNull())
	      throw new RtSingCacheException("NOT A VALID CACHE");
	    Element element = new Element(key, value);
	    this.cache.put(element);
	  }

	  public void addToCache(List<Element> elements)
	    throws RtSingCacheException
	  {
	    if (isNull())
	      throw new RtSingCacheException("NOT A VALID CACHE");
	    for (int i = 0; i < elements.size(); i++)
	      this.cache.put((Element)elements.get(i));
	  }

	  public Object getFromCache(String key)
	    throws RtSingCacheException
	  {
	    if (isNull())
	      throw new RtSingCacheException("NOT A VALID CACHE");
	    if (!isExists(key)) {
	      return null;
	    }
	    return this.cache.get(key).getObjectValue();
	  }

	  public boolean isExists(String key) throws RtSingCacheException
	  {
	    if (this.cache == null) {
	      throw new RtSingCacheException("NOT A VALID CACHE");
	    }
	    return this.cache.get(key) != null;
	  }

	  public boolean isNull()
	  {
	    return this.cache == null;
	  }

	  public int size()
	  {
	    if (this.cache == null)
	      return 0;
	    return this.cache.getSize();
	  }

}
