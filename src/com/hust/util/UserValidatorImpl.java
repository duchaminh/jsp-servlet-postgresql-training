package com.hust.util;

import com.hust.util.validators.genericvalidator.UserException;
import com.hust.util.validators.genericvalidator.ValidatorUtil;

import model.User;

public class UserValidatorImpl implements UserValidator {

	@Override
	public void validate(User user) throws UserException {
		StringBuilder errorFields = new StringBuilder();
		
		errorFields.append(ValidatorUtil.notNullString.and(ValidatorUtil.notEmptyString)
	        .and(ValidatorUtil.stringBetween(1, 30)).test(user.getUserId())
	        .getFieldNameIfInvalid(" Please specify valid user id  ").orElse(""));

	    errorFields.append(ValidatorUtil.notNullString.and(ValidatorUtil.notEmptyString)
	        .and(ValidatorUtil.stringBetween(1, 100)).test(user.getPassword())
	        .getFieldNameIfInvalid("Password too long").orElse(""));

	    errorFields.append(ValidatorUtil.notNullString.and(ValidatorUtil.notEmptyString)
	        .and(ValidatorUtil.stringBetween(1, 100)).test(user.getFirstName())
	        .getFieldNameIfInvalid(" Please specify valid firstname ").orElse(""));

	    errorFields.append(ValidatorUtil.notNullString.and(ValidatorUtil.notEmptyString)
	        .and(ValidatorUtil.stringBetween(1, 100)).test(user.getFamilyName())
	        .getFieldNameIfInvalid(" Please specify valid family name ").orElse(""));

		errorFields.append(ValidatorUtil.equalsWithZero.or(ValidatorUtil.integerBetween(18,
		60)).test(user.getAge())
		.getFieldNameIfInvalid(" Please specify valid age ").orElse(""));
		 

	    String errors = errorFields.toString();
	    if (!errors.isEmpty()) {
	      throw new UserException(errors);
	    }

	  }

	}

