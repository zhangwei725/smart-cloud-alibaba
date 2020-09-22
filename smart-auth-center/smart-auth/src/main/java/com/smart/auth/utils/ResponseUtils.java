package com.smart.auth.utils;

import cn.hutool.http.server.HttpServerResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.common.dto.BaseResult;
import com.smart.common.dto.ResultCodeEnum;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhangwei
 */

public class ResponseUtils {
    public static final String JSON_CONTENT_TYPE = "application/json;charset=UTF-8";

    public static void responseToJson(HttpServletResponse response, String result) throws IOException {
        response.setContentType(JSON_CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.write(result);
        out.flush();
    }
}
