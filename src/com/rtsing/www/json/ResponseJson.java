package com.rtsing.www.json;

import java.util.List;

import com.rtsing.www.dto.HeatMapDataPoint;

public class ResponseJson {
	int max;
	List<HeatMapDataPoint> data;
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public List<HeatMapDataPoint> getData() {
		return data;
	}
	public void setData(List<HeatMapDataPoint> data) {
		this.data = data;
	}
	
	
}
