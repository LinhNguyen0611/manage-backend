package com.example.demo.controllers;

import com.example.demo.constants.Const;
import com.example.demo.constants.URL;
import com.example.demo.entities.Product;
import com.example.demo.exceptions.ApplicationException;
import com.example.demo.models.ProductModel;
import com.example.demo.responses.ProductResponse;
import com.example.demo.responses.ResponseModel;
import com.example.demo.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The class Product controller.
 */
@RestController
@RequestMapping(URL.PRODUCT_CONTROLLER)
public class ProductController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductService productService;

    @RequestMapping(value = URL.ADD_ACTION, method = RequestMethod.POST)
    public ResponseModel<ProductResponse> saveProduct(@RequestBody @Valid ProductModel productModel) {
        ResponseModel<ProductResponse> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " saveProduct ");
            //Save product
            Product product = productService.saveData(productModel.toEntity());
            response.setData(new ProductResponse(product));
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " saveProduct : {}", ae.getMessage());
            response.buildError(ae);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " saveProduct ");
        }
    }

    @RequestMapping(value = URL.GET_ACTION, method = RequestMethod.GET)
    public ResponseModel<ProductResponse> getProduct(@PathVariable(value = Const.PATH_ID) Integer id) {
        ResponseModel<ProductResponse> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " getProduct ");
            //Get product
            Product product = productService.getById(id);
            response.setData(new ProductResponse(product));
            return response;
        } catch (ApplicationException ae) {
            LOG.error(Const.LOGGING_ERROR + " getProduct : {}", ae.getMessage());
            response.buildError(ae);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " getProduct ");
        }
    }

    @RequestMapping(value = URL.LIST_PAGING, method = RequestMethod.GET)
    public ResponseModel<Page<ProductResponse>> listAll(
            @PathVariable(value = Const.PATH_SIZE) Integer size,
            @PathVariable(value = Const.PATH_PAGE) Integer page) {
        ResponseModel<Page<ProductResponse>> response = new ResponseModel<>();
        try {
            LOG.info(Const.LOGGING_CONTROLLER_BEGIN + " listAll [page={}],[size = {}] ", page, size);
            //List all
            response.setData(productService.listAll(page, size));
            return response;
        } catch (ApplicationException ex) {
            LOG.error(Const.LOGGING_ERROR + "listByName: {}", ex.getMessage());
            response.buildError(ex);
            return response;
        } finally {
            LOG.info(Const.LOGGING_CONTROLLER_END + " listByName ");
        }
    }
}
