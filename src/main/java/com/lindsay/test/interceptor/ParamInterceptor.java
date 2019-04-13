package com.lindsay.test.interceptor;

import com.lindsay.test.dto.ResultVO;
import com.lindsay.test.utils.JsonUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Auther: Lindsay
 * @Date: 2018/11/30 16:48
 * @Description:
 */
public class ParamInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");

        if (!"token".equalsIgnoreCase(token)) {
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(JsonUtils.obj2json(new ResultVO<>(-1, "token错误")));
            writer.flush();
            writer.close();
            return false;
        }

        return true;
    }
}
