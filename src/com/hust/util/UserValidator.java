package com.hust.util;

import com.hust.util.validators.genericvalidator.UserException;

import model.User;

public interface UserValidator {
	  public void validate(User user) throws UserException;
	}