package com.rtsing.www.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.rtsing.www.dao.IFourSquareDao;
import com.rtsing.www.dto.HeatMapData;
import com.rtsing.www.dto.HeatMapDataPoint;

public class FourSquareDao extends RtSingBaseDao implements IFourSquareDao{

	public static final String SELECT_100_RANDOM_VENUES = "SELECT lat, lng, checkins_count  FROM fsPlaces WHERE checkins_count > 0 ORDER BY RAND() LIMIT 0,100";
	
	@Override
	public  HeatMapData getPlacesRandomly() {
		// TODO Auto-generated method stub

		HeatMapData hmd = new HeatMapData();
		int rowCount = 0;
		try{
			
			SqlRowSet srs = this.jdbcTemplate.queryForRowSet(SELECT_100_RANDOM_VENUES);
		    while (srs.next()) {
		    	HeatMapDataPoint dp = new HeatMapDataPoint();
		    	System.out.println(srs.getString("lat")+":"+srs.getString("lng")+":"+srs.getString("checkins_count"));
		    	dp.setLat(srs.getString("lat"));
		    	dp.setLng(srs.getString("lng"));
		    	dp.setCount(srs.getInt("checkins_count"));
		    	hmd.addPoint(dp);
		    	rowCount++;
		    }
		    System.out.println("Number of records : " + rowCount);

		}catch(DataAccessException dae){
			System.out.println("exception in  FourSquare dao " + dae.getMessage());
		}
		
		return hmd;
		
	
	}
}

