package com.rtsing.www.dao.impl;



import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.rtsing.www.dao.IRtSingBaseDao;




public abstract class RtSingBaseDao implements IRtSingBaseDao{

	 protected JdbcTemplate jdbcTemplate;
	  protected NamedParameterJdbcTemplate namedJdbcTemplate;
	  

	  public void setDataSource(DataSource dataSource)
	  {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	    this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	    
	  }
}
