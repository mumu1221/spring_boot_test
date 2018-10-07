package cn.mucfc.com.checkModel.dynamicdatasource.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CountDao {
    @Select("SELECT count(1) as count FROM ${tableName} WHERE ${timeField} between ${beginTime} and ${endTime}")
    int select(@Param("dbName") String dbname, @Param("tableName")String tableName, @Param("timeField")String timeField, @Param("beginTime")String beginTime, @Param("endTime")String endTime);
    int selectCount(@Param("dbName") String dbname,@Param("tableName")String tableName,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
}
