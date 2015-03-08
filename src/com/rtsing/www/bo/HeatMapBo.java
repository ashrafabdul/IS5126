package com.rtsing.www.bo;

import java.text.DecimalFormat;
import java.util.List;

import javax.sql.DataSource;

import com.rtsing.www.dao.IFourSquareDao;
import com.rtsing.www.daofactory.RtSingDaoFactory;
import com.rtsing.www.dto.HeatMapData;
import com.rtsing.www.dto.HeatMapDataPoint;

public class HeatMapBo {
	
	public HeatMapData getHeatMapData(){
		
		RtSingDaoFactory daoFac = RtSingDaoFactory.getInstance();
		IFourSquareDao fsDao = daoFac.getFourSquareDao();
		DataSource dataSource = daoFac.getDataSource();
		fsDao.setDataSource(dataSource);
		System.out.println("Calling DAO");
		HeatMapData hmd = fsDao.getPlacesRandomly();
		hmd = normalizeHMD(hmd);
		return hmd;
		
	}
	
	private HeatMapData normalizeHMD(HeatMapData hmd){
		DecimalFormat df = new DecimalFormat("###.####");
		
		HeatMapData tmp = new HeatMapData();
		
		int maxCheckIn = 0;
		int minCheckIn = 0;
		boolean maxminFlag = true;
		for(HeatMapDataPoint dp : hmd.getDataPoints()){
			
			int checkInCount = dp.getCount();
			if(maxminFlag){
				maxCheckIn = minCheckIn = checkInCount;
				maxminFlag = false;
			}
			dp.setLat(df.format(Double.parseDouble(dp.getLat())));
			dp.setLng(df.format(Double.parseDouble(dp.getLng())));
			if(checkInCount > maxCheckIn){
				maxCheckIn = checkInCount;
			}
			if(checkInCount < minCheckIn){
				minCheckIn = checkInCount;
			}
			
			tmp.addPoint(dp);
		}
		System.out.println("Min CheckIn is " + minCheckIn);
		System.out.println("Max CheckIn is " + maxCheckIn);
		List<HeatMapDataPoint> tmpList = tmp.getDataPoints();
		tmp = new HeatMapData();
		for(HeatMapDataPoint dp : tmpList){
			int checkInCount = dp.getCount();
			dp.setCount((checkInCount - minCheckIn) / (maxCheckIn - minCheckIn) * 9 + 1);
			tmp.addPoint(dp);
		}
	
		return tmp;
	}

}
