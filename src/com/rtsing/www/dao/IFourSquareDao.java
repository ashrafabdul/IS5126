package com.rtsing.www.dao;

import com.rtsing.www.dto.HeatMapData;

public interface IFourSquareDao extends IRtSingBaseDao{

	HeatMapData getPlacesRandomly();

}
