package com.kandy.tissot.core.utils.validate;

import com.kandy.tissot.core.result.BaseResult;

/**
 * 校验调用服务结果
 */
public class BaseResultValidator {

	@SuppressWarnings("rawtypes")
	public static boolean checkBaseResult(BaseResult baseResult){
        if(null != baseResult && 200 == baseResult.getCode() && null != baseResult.getData()){
            return true;
        }
        return false;
    }
}