package cn.mucfc.com.checkModel.dynamicdatasource.controller;


import cn.mucfc.com.checkModel.dynamicdatasource.service.CountService;
import cn.mucfc.com.checkModel.dynamicdatasource.utils.ApplicationContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/count")
public class CountController {
    @Autowired
    private CountService countService;

    @GetMapping
    public Map<String ,Object> getCountMap(String dbName,String tableName,String timeField,String beginTime,String endTime){
        Map<String ,Object> countMap=countService.collectCount(dbName,tableName,timeField,beginTime,endTime);
        return countMap;
    }


}
