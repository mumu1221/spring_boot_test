package cn.mucfc.com.checkModel.dynamicdatasource.service;

import cn.mucfc.com.checkModel.dynamicdatasource.mapper.CountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CountService {
    @Autowired
    private CountDao countDao;

    public Map<String ,Object > collectCount(String dbName, String tableName,String timeField, String beginTime, String endTime){
        String mysqlDBName=dbName+"mysql";
        String verticaDBName=dbName+"vertica";
        String impalaDBName=dbName+"impala";

        Map<String ,Object> countMap=new HashMap<String ,Object >(6);

        int mysqlCount=countDao.select(mysqlDBName,tableName,timeField,beginTime,endTime);
        int verticaCount=countDao.select(verticaDBName,tableName,timeField,beginTime,endTime);
        int impalaCount=countDao.select(impalaDBName,tableName,timeField,beginTime,endTime);
        countMap.put(mysqlDBName,mysqlCount);
        countMap.put(verticaDBName,verticaCount);
        countMap.put(impalaDBName,impalaCount);
        countMap.put("tableName",tableName);
        countMap.put("beginTime",beginTime);
        countMap.put("endTime",endTime);
        return countMap;
    }


}
