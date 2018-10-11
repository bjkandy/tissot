package com.kandy.tissot.core.validator;


import com.kandy.tissot.core.exception.ServiceException;

public interface ValidationService {

    ValidationResult validate(Object param, boolean fastMode);

    void validate(Object param) throws ServiceException;

}
