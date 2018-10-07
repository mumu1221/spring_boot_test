package cn.mucfc.com.checkModel.dynamicdatasource.service;

import cn.mucfc.com.checkModel.dynamicdatasource.mapper.ProductDao;
import cn.mucfc.com.checkModel.dynamicdatasource.model.Product;
import cn.mucfc.com.checkModel.dynamicdatasource.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;


    public Product select(String dbName,String tableName,long productId) throws ServiceException {
        Product product = productDao.select(dbName,tableName,productId);
        if (product == null) {
            throw new ServiceException("Product:" + productId + " not found");
        }
        return product;
    }



    @Transactional(rollbackFor = DataAccessException.class)
    public Product update(long productId, Product newProduct) throws ServiceException {

        if (productDao.update(newProduct) <= 0) {
            throw new ServiceException("Update product:" + productId + "failed");
        }
        return newProduct;
    }


    @Transactional(rollbackFor = DataAccessException.class)
    public boolean add(Product newProduct) throws ServiceException {
        Integer num = productDao.insert(newProduct);
        if (num <= 0) {
            throw new ServiceException("Add product failed");
        }
        return true;
    }


    @Transactional(rollbackFor = DataAccessException.class)
    public boolean delete(long productId) throws ServiceException {
        Integer num = productDao.delete(productId);
        if (num <= 0) {
            throw new ServiceException("Delete product:" + productId + "failed");
        }
        return true;
    }


    public List<Product> getAllProduct() {
        return productDao.getAllProduct();
    }
}
