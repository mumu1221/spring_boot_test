package cn.mucfc.com.checkModel.dynamicdatasource;

import cn.mucfc.com.checkModel.dynamicdatasource.controller.ProductController;
import cn.mucfc.com.checkModel.dynamicdatasource.utils.ServiceException;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
public class DynamicDataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDataSourceApplication.class, args);
    }
}
