package com.rtsing.www.cache;

import java.util.List;

import com.rtsing.www.exception.RtSingCacheException;

import net.sf.ehcache.Element;


public abstract interface IRtSingCache {
	
	 		public abstract Object getFromCache(String paramString)
			    throws RtSingCacheException;

	 		public abstract boolean isExists(String paramString)
			    throws RtSingCacheException;

			  public abstract void addToCache(String paramString, Object paramObject)
			    throws RtSingCacheException;

			  public abstract void addToCache(List<Element> paramList)
			    throws RtSingCacheException;

			  public abstract boolean isNull();

			  public abstract int size();

}
