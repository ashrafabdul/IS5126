package com.rtsing.www.dto;

import java.util.ArrayList;
import java.util.List;


public class HeatMapData {
	
	List<HeatMapDataPoint>dataPoints = new ArrayList();

	public List<HeatMapDataPoint> getDataPoints() {
		return dataPoints;
	}

	public void setDataPoints(List<HeatMapDataPoint> dataPoints) {
		this.dataPoints = dataPoints;
	}
	
	public void addPoint(HeatMapDataPoint dp){
		this.dataPoints.add(dp);
	}

}
