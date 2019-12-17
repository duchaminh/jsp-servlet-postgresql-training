package com.hust.util;

import com.hust.util.validators.genericvalidator.UserException;
import com.hust.util.validators.genericvalidator.ValidatorUtil;

import model.User;

public class UserValidatorImpl implements UserValidator {

	@Override
	public void validate(User user) throws UserException {
		StringBuilder errorFields = new StringBuilder();
		
		errorFields.append(ValidatorUtil.notNullString.and(ValidatorUtil.notEmptyString)
	        .and(ValidatorUtil.stringBetween(0, 9)).test(user.getUserId())
	        .getFieldNameIfInvalid(" ユーザーIDが入力されていません。または長すぎ ").orElse(""));

	    errorFields.append(ValidatorUtil.notNullString.and(ValidatorUtil.notEmptyString)
	        .and(ValidatorUtil.stringBetween(0, 9)).test(user.getPassword())
	        .getFieldNameIfInvalid("パスワードが入力されていません。または長すぎ").orElse(""));

	    errorFields.append(ValidatorUtil.notNullString.and(ValidatorUtil.notEmptyString)
	        .and(ValidatorUtil.stringBetween(0, 11)).test(user.getFirstName())
	        .getFieldNameIfInvalid(" 名が入力されていません。または長すぎ ").orElse(""));

	    errorFields.append(ValidatorUtil.notNullString.and(ValidatorUtil.notEmptyString)
	        .and(ValidatorUtil.stringBetween(0, 11)).test(user.getFamilyName())
	        .getFieldNameIfInvalid(" 姓が入力されていません。または長すぎ ").orElse(""));

		errorFields.append(ValidatorUtil.equalsWithZero.or(ValidatorUtil.integerBetween(18,
		60)).test(user.getAge())
		.getFieldNameIfInvalid(" Please specify valid age ").orElse(""));
		 

	    String errors = errorFields.toString();
	    if (!errors.isEmpty()) {
	      throw new UserException(errors);
	    }

	  }

	}

