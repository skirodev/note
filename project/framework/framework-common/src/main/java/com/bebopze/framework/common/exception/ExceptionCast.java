package com.bebopze.framework.common.exception;

import com.bebopze.framework.common.model.response.code.ResultCode;


/**
 * 异常捕获
 *
 * @author bebopze
 * @date 2018/11/14
 */
public class ExceptionCast {

    public static void cast(ResultCode resultCode) {
        throw new CustomException(resultCode);
    }
}
