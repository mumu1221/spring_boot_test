package cn.mucfc.com.checkModel.dynamicdatasource.controller;

import cn.mucfc.com.checkModel.dynamicdatasource.common.CommonResponse;
import cn.mucfc.com.checkModel.dynamicdatasource.common.ResponseUtil;
import cn.mucfc.com.checkModel.dynamicdatasource.model.Product;
import cn.mucfc.com.checkModel.dynamicdatasource.service.ProductService;
import cn.mucfc.com.checkModel.dynamicdatasource.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Get product by id
     *
     * @param productId
     * @return
     * @throws ServiceException
     */
    @GetMapping("/{dbName}/{tableName}/{id}")
    public CommonResponse getProduct(@PathVariable("dbName") String  dbName,@PathVariable("tableName") String tableName,@PathVariable("id") Long productId) throws ServiceException {
        return ResponseUtil.generateResponse(productService.select(dbName,tableName,productId));
    }

    /**
     * Get all product
     *
     * @return
     * @throws ServiceException
     */
    @GetMapping
    public CommonResponse getAllProduct() {
        return ResponseUtil.generateResponse(productService.getAllProduct());
    }

    /**
     * Update product by id
     *
     * @param productId
     * @param newProduct
     * @return
     * @throws ServiceException
     */
    @PutMapping("/{id}")
    public CommonResponse updateProduct(@PathVariable("id") Long productId, @RequestBody Product newProduct) throws ServiceException {
        return ResponseUtil.generateResponse(productService.update(productId, newProduct));
    }

    /**
     * Delete product by id
     *
     * @param productId
     * @return
     * @throws ServiceException
     */
    @DeleteMapping("/{id}")
    public CommonResponse deleteProduct(@PathVariable("id") long productId) throws ServiceException {
        return ResponseUtil.generateResponse(productService.delete(productId));
    }

    /**
     * Save product
     *
     * @param newProduct
     * @return
     * @throws ServiceException
     */
    @PostMapping
    public CommonResponse addProduct(@RequestBody Product newProduct) throws ServiceException {
        return ResponseUtil.generateResponse(productService.add(newProduct));
    }
}
