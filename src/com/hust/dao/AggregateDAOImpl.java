package com.hust.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hust.dto.AggregateByAuthority;
import com.hust.util.ConditionForAggregate;
import com.hust.util.DBConnectionUtil;

public class AggregateDAOImpl implements AggregateDAO {
	
	Connection connection = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	
	public static final String COLUMN_COUNT_MALE = "countMale";
	public static final String COLUMN_COUNT_FEMALE = "countFemale";
	public static final String COLUMN_COUNT_GENDER_NONE = "countGenderNone";
	public static final String COLUMN_COUNT_AGE_BEETWEEN_ZERO_TO_NINETEEN = "countAgeBeetweenZeroToNineTeen";
	public static final String COLUMN_COUNT_AGE_MORE_THAN_TWENTY = "countAgeMoreThanTwenty";
	public static final String COLUMN_COUNT_AGE_NONE = "countAgeNone";
	
	public static final String CONDITION_IS_MALE = "gender_id = 1";
	public static final String CONDITION_IS_FEMALE = "gender_id = 0";
	public static final String CONDITION_GENDER_IS_NONE = "gender_id = -1";
	public static final String CONDITION_AGE_BEETWEEN_ZERO_TO_NINETEEN = "age >0 and age <= 19";
	public static final String CONDITION_AGE_MORE_THAN_TWENTY = "age >= 20";
	public static final String CONDITION_AGE_NONE = "age = 0";
	
	public static final String TABLE_WITH_PRIMARY_KEY = "mst_role";
	public static final String PRIMARY_KEY = "authority_id";
	public static final String NAME_OF_PRIMARY_KEY = "authority_name";

	@Override
	public List<ConditionForAggregate> initConditionForAggregate() {
		List<ConditionForAggregate> listCondition = new ArrayList<ConditionForAggregate>();
		
		listCondition.add(new ConditionForAggregate("TABLE_MALE", COLUMN_COUNT_MALE, CONDITION_IS_MALE ));
		listCondition.add(new ConditionForAggregate("TABLE_FEMALE", COLUMN_COUNT_FEMALE, CONDITION_IS_FEMALE ));
		listCondition.add(new ConditionForAggregate("TABLE_GENDER_NONE", COLUMN_COUNT_GENDER_NONE, CONDITION_GENDER_IS_NONE ));
		listCondition.add(new ConditionForAggregate("TABLE_AGE_ZERO", COLUMN_COUNT_AGE_BEETWEEN_ZERO_TO_NINETEEN, CONDITION_AGE_BEETWEEN_ZERO_TO_NINETEEN ));
		listCondition.add(new ConditionForAggregate("TABLE_AGE_TWENTY", COLUMN_COUNT_AGE_MORE_THAN_TWENTY, CONDITION_AGE_MORE_THAN_TWENTY ));
		listCondition.add(new ConditionForAggregate("TABLE_AGE_NONE", COLUMN_COUNT_AGE_NONE, CONDITION_AGE_NONE));
		
		return listCondition;
	}

	@Override
	public List<AggregateByAuthority> aggregateFor(String foreignKey) {
		List<AggregateByAuthority> results = null;
		AggregateByAuthority user = null;
		List<ConditionForAggregate> conditions = initConditionForAggregate();
		try {
			results = new ArrayList<AggregateByAuthority>();
			
			String sql = "SELECT "+NAME_OF_PRIMARY_KEY+","+COLUMN_COUNT_MALE +","+COLUMN_COUNT_FEMALE+","+COLUMN_COUNT_GENDER_NONE+","+COLUMN_COUNT_AGE_BEETWEEN_ZERO_TO_NINETEEN+","+COLUMN_COUNT_AGE_MORE_THAN_TWENTY+","+COLUMN_COUNT_AGE_NONE+" FROM " +TABLE_WITH_PRIMARY_KEY;
			for(ConditionForAggregate index : conditions) {
				sql = sql.concat(" LEFT JOIN ( ");	
				sql = sql.concat(" SELECT "+foreignKey+", COUNT(*) as "+index.getColumnName()+" from mst_user where "+index.getCondition()+" GROUP BY "+foreignKey+ ") as "+index.getTableName());	
				sql = sql.concat(" on "+TABLE_WITH_PRIMARY_KEY+"."+PRIMARY_KEY+" = "+index.getTableName()+"."+foreignKey);
			}
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				user = new AggregateByAuthority();
				user.setAuthorityName(resultSet.getString(NAME_OF_PRIMARY_KEY));
				user.setCountAgeBeetweenZeroToNineTeen(resultSet.getInt(COLUMN_COUNT_AGE_BEETWEEN_ZERO_TO_NINETEEN));
				user.setCountAgeMoreThanTwenty(resultSet.getInt(COLUMN_COUNT_AGE_MORE_THAN_TWENTY));
				user.setCountAgeNone(resultSet.getInt(COLUMN_COUNT_AGE_NONE));
				user.setCountMale(resultSet.getInt(COLUMN_COUNT_MALE));
				user.setCountFemale(resultSet.getInt(COLUMN_COUNT_FEMALE));
				user.setCountGenderNone(resultSet.getInt(COLUMN_COUNT_GENDER_NONE));
				
				results.add(user);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
		return results;
	}

}
