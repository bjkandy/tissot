package com.kandy.tissot.core.validator;

import com.kandy.tissot.core.constant.ErrorCodeEnum;
import com.kandy.tissot.core.exception.ServiceException;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValidationServiceImpl implements ValidationService {
    private static final String NULL_PARAM    = "参数为空";
    private static final String INVALID_PARAM = "输入参数不正确";

    private Validator validator;

    public ValidationResult validate(Object param, boolean fastMode) {
        ValidationResult result = new ValidationResult();

        Set<ConstraintViolation<Object>> violations = validator.validate(param);
        boolean isEmpty = violations.isEmpty();

        result.setPassed(isEmpty);
        if (!isEmpty) {
            List<String> failedReasonList = new ArrayList<String>(fastMode ? 1 : violations.size());
            for (ConstraintViolation<Object> violation : violations) {
                failedReasonList.add(violation.getMessage());
                if (fastMode) {
                    break;
                }
            }
            result.setFailedReasonList(failedReasonList);
        }
        return result;
    }

    public void validate(Object param) throws ServiceException {
        if (param == null) {
            throw createServiceException(NULL_PARAM);
        }

        ValidationResult vr = validate(param, true);
        if (!vr.isPassed()) {
            throw createServiceException(INVALID_PARAM, vr.getFirstFailedReason());
        }
    }

    private ServiceException createServiceException(String invalidParam, String firstFailedReason) {
        return new ServiceException(ErrorCodeEnum.参数异常.getCode(),StringUtils.isEmpty(firstFailedReason) ? invalidParam:firstFailedReason);
    }

    private ServiceException createServiceException(String nullParam) {
        return new ServiceException(ErrorCodeEnum.参数不为空.getCode(),nullParam);
    }

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}
