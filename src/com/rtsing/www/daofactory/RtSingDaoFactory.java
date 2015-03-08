package com.rtsing.www.daofactory;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.rtsing.www.constants.RtSingConstants;
import com.rtsing.www.dao.IFourSquareDao;
import com.rtsing.www.dao.impl.FourSquareDao;
import com.rtsing.www.exception.RtSingResourceNotFoundException;
import com.rtsing.www.resource.RtSingProperties;


public class RtSingDaoFactory {

	private static RtSingDaoFactory oDaoFactory = new RtSingDaoFactory();

	  private DataSource dataSource = null;
	  private InitialContext ic;

	  protected RtSingDaoFactory()
	  {
	    try
	    {
	      this.ic = new InitialContext();
	    } catch (NamingException ne) {
	      throw new RtSingResourceNotFoundException(
	        "RtSingResourceNotFoundException:JNDI name not found", ne);
	    } catch (RtSingResourceNotFoundException exp) {
	      throw exp;
	    }
	  }

	  public static RtSingDaoFactory getInstance() {
	    return oDaoFactory;
	  }

	  public DataSource getDataSource() {

	    return getJDBCDataSource();
	  }


	  private DataSource getJDBCDataSource()
	  {
		  RtSingProperties properties = RtSingProperties.getProperties();
	    try {
	      if (this.dataSource == null)
	        this.dataSource = ((DataSource)this.ic.lookup(properties.getProperty(RtSingConstants.RTSING_DS)));
	    }
	    catch (NamingException e)
	    {
	      throw new RtSingResourceNotFoundException(
	        "RtSingNotResourceFoundException:JNDI name not found", e);
	    }

	    return this.dataSource;
	  }


	
	public IFourSquareDao getFourSquareDao(){
		return new FourSquareDao();
	}
	

	
}
