package com.rtsing.www.log;



import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.rtsing.www.constants.RtSingConstants;
import com.rtsing.www.resource.RtSingProperties;



public class Log {

	  public static final long DEFAULT_REFRESH_PERIOD = 5000L;
	  private static boolean configured;
	  private static long refreshPeriod;

	  protected static void configure()
	  {
	    if (!isConfigured())
	    {
	      String configFileName = RtSingProperties.getProperties().getProperty(
	        RtSingConstants.LOG_CONFIG_FILE_PATH);
	      long refreshPeriod = getRefreshPeriod();
	      if (refreshPeriod < 1000L)
	        refreshPeriod = 5000L;
	      DOMConfigurator.configureAndWatch(configFileName, refreshPeriod);

	      configured = true;
	      try
	      {
	        Logger logger = Logger.getLogger(
	          Class.forName(RtSingConstants.LOG_CLASS_NAME));
	        logger
	          .info("Logger Instatntiated succesfully logger=" + 
	          logger);
	        logger
	          .info("Instantiated logging from file: " + 
	          configFileName);
	      } catch (Exception e) {
	        e.printStackTrace();
	        error(Log.class, "Exception occurred in instatntiating the log file" + 
	          configFileName);
	      }
	    }
	  }

	  private static void log(Class<?> clazz, Object msg, int level, Throwable throwable)
	  {
	    configure();

	    Logger logger = Logger.getLogger(clazz);
	    if (logger != null) {
	      if (level == 40000) {
	        logger.error(msg, throwable);
	      }

	      if (level == 50000)
	        logger.fatal(msg, throwable);
	    }
	  }

	  private static void log(Class<?> clazz, Object msg, int level)
	  {
	    configure();

	    Logger logger = Logger.getLogger(clazz);

	    if (logger != null)
	    {
	      if (level == 10000) {
	        logger.debug(msg);
	      }

	      if (level == 20000) {
	        logger.info(msg);
	      }
	      if (level == 30000) {
	        logger.warn(msg);
	      }
	      if (level == 40000) {
	        logger.error(msg);
	      }

	      if (level == 50000)
	        logger.fatal(msg);
	    }
	  }

	  public static void debug(Class<?> clazz, Object msg) {
	    log(clazz, msg, 10000);
	  }

	  public static void info(Class<?> clazz, Object msg) {
	    log(clazz, msg, 20000);
	  }

	  public static void warn(Class<?> clazz, Object msg) {
	    log(clazz, msg, 30000);
	  }

	  public static void error(Class<?> clazz, Object msg) {
	    log(clazz, msg, 40000);
	  }

	  public static void fatal(Class<?> clazz, Object msg) {
	    log(clazz, msg, 50000);
	  }

	  public static void error(Class<?> clazz, Object msg, Throwable throwable) {
	    log(clazz, msg, 40000, throwable);
	  }

	  public static void fatal(Class<?> clazz, Object msg, Throwable throwable) {
	    log(clazz, msg, 50000, throwable);
	  }

	  public static void setRefreshPeriod(long refresh) {
	    refreshPeriod = refresh;
	  }

	  public static long getRefreshPeriod() {
	    return refreshPeriod;
	  }

	  public static boolean isConfigured() {
	    return configured;
	  }
}
