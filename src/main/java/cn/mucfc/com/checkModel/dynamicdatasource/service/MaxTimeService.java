package cn.mucfc.com.checkModel.dynamicdatasource.service;

import cn.mucfc.com.checkModel.dynamicdatasource.mapper.CountDao;
import cn.mucfc.com.checkModel.dynamicdatasource.mapper.MaxTimeDao;
import cn.mucfc.com.checkModel.dynamicdatasource.model.MaxTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MaxTimeService {
    @Autowired
    private MaxTimeDao maxTimeDao;


    public MaxTime selectMaxQueryTime(String dbName,String tableName){
        MaxTime maxTime=maxTimeDao.select(dbName,tableName);

        if (maxTime==null){
            maxTime.beginTime("0000-00-00 00:00:00");
        }
        return maxTime;
    }
}
