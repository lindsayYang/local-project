package com.lindsay.test.utils;

import com.lindsay.test.dto.ResultVO;
import com.lindsay.test.enums.IExceptionEnum;

/**
 * @Auther: Lindsay
 * @Date: 2018/11/26 18:55
 * @Description:
 */
public class ResultVoUtils {

    public ResultVoUtils() {
    }

    public static ResultVO success(Object object) {
        ResultVO result = new ResultVO();
        result.setCode(0);
        result.setData(object);
        return result;
    }

    public static ResultVO success(Object object, String msg) {
        ResultVO result = new ResultVO();
        result.setCode(0);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }

    public static ResultVO success() {
        return success((Object)null);
    }

    public static ResultVO error(Integer code, String message) {
        ResultVO result = new ResultVO();
        result.setCode(code);
        result.setMsg(message);
        return result;
    }

    public static ResultVO error(Integer code, String message, Object data) {
        ResultVO result = new ResultVO();
        result.setCode(code);
        result.setMsg(message);
        result.setData(data);
        return result;
    }

    public static ResultVO error(IExceptionEnum exceptionEnum) {
        ResultVO result = new ResultVO();
        result.setCode(exceptionEnum.getCode());
        result.setMsg(exceptionEnum.getMessage());
        return result;
    }

}
