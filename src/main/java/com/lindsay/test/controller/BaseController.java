package com.lindsay.test.controller;

import com.lindsay.test.dto.ResultVO;
import com.lindsay.test.enums.IExceptionEnum;
import com.lindsay.test.utils.ResultVoUtils;

/**
 * @Auther: Lindsay
 * @Date: 2018/11/26 18:52
 * @Description:
 */
public class BaseController {

    public BaseController() {
    }

    public ResultVO success(Object obj) {
        return ResultVoUtils.success(obj);
    }

    public ResultVO success() {
        return ResultVoUtils.success();
    }

    public static ResultVO error(Integer code, String message) {
        return ResultVoUtils.error(code, message);
    }

    public static ResultVO error(IExceptionEnum exceptionEnum) {
        return ResultVoUtils.error(exceptionEnum);
    }

}
