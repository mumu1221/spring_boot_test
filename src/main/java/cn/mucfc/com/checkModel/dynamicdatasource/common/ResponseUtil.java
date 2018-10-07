package cn.mucfc.com.checkModel.dynamicdatasource.common;

import cn.mucfc.com.checkModel.dynamicdatasource.utils.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ResponseUtil {

    private static final Logger logger = LoggerFactory.getLogger(ResponseUtil.class);


    public static CommonResponse generateResponse(boolean resultStatus) {
        CommonResponse commonResponse = new CommonResponse();
        if (resultStatus) {
            commonResponse
                    .setCode(ResponseCode.SUCCESS)
                    .setMessage(CommonConstant.DEFAULT_SUCCESS_MESSAGE);
        } else {
            commonResponse
                    .setCode(ResponseCode.FAIL)
                    .setMessage(CommonConstant.DEFAULT_FAIL_MESSAGE);
        }
        return commonResponse;
    }


    public static CommonResponse generateResponse(String message, boolean resultStatus) {
        CommonResponse commonResponse = new CommonResponse();
        if (resultStatus) {
            commonResponse
                    .setCode(ResponseCode.SUCCESS)
                    .setMessage(message);
        } else {
            commonResponse
                    .setCode(ResponseCode.FAIL)
                    .setMessage(message);
        }
        return commonResponse;
    }


    public static CommonResponse generateResponse(Object data) {
        CommonResponse commonResponse = new CommonResponse();
        if (data != null) {
            commonResponse
                    .setCode(ResponseCode.SUCCESS)
                    .setMessage(CommonConstant.DEFAULT_SUCCESS_MESSAGE)
                    .setData(data);
        } else {
            commonResponse
                    .setCode(ResponseCode.SUCCESS)
                    .setMessage(CommonConstant.NO_RESULT_MESSAGE);

        }
        return commonResponse;
    }


    public static HttpServletResponse handlerResponse(HttpServletResponse response, Object object) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSONUtil.toJSONString(object));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return response;
    }
}
