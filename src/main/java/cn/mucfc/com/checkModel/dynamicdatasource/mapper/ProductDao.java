package cn.mucfc.com.checkModel.dynamicdatasource.mapper;

import cn.mucfc.com.checkModel.dynamicdatasource.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductDao {
    Product select(@Param("dbName") String dbname,@Param("tableName") String  tableName,@Param("id") long id);

    Integer update(Product product);

    Integer insert(Product product);

    Integer delete(long productId);

    List<Product> getAllProduct();
}
