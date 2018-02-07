package com.cobo.dt.mapper;

import com.cobo.dt.model.impl.DecisionTable;

public interface MapToDecisionTable<T> {

	DecisionTable map(T dt2Map);
	
}
