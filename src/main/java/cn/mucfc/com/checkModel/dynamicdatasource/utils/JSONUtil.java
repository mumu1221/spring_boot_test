package cn.mucfc.com.checkModel.dynamicdatasource.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;




public class JSONUtil {

    private static final Logger logger = LoggerFactory.getLogger(JSONUtil.class);


    public static String toJSONString(Object object) {
        String result = null;
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        try {
            result = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("Generate JSON String error!" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
