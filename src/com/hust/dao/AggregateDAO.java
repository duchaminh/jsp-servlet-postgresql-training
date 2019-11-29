package com.hust.dao;

import java.util.List;

import com.hust.dto.AggregateByAuthority;
import com.hust.util.ConditionForAggregate;

public interface AggregateDAO {
	List<ConditionForAggregate> initConditionForAggregate();
	List<AggregateByAuthority> aggregateFor(String foreignKey);
}
