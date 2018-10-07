package cn.mucfc.com.checkModel.dynamicdatasource.mapper;

import cn.mucfc.com.checkModel.dynamicdatasource.model.MaxTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MaxTimeDao {
    @Select("SELECT max(endTime) as beginTime as count FROM dqclog WHERE tableName='#{tableName}' and chkFlg='Y'")
    MaxTime select(@Param("dbName") String dbname, @Param("tableName") String tableName);
}
