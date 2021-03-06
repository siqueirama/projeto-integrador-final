package com.meli.projetointegradorgroup1.controller;
import com.meli.projetointegradorgroup1.dto.request.ProductRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.ProductResponseDTO;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.entity.StockType;
import com.meli.projetointegradorgroup1.repository.ProductRepository;
import com.meli.projetointegradorgroup1.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Marco Siqueiraa
 */

public class ProductControllerTest {

    Product product = new Product(1l, "teste","cafe", StockType.FRESH);
    ProductResponseDTO productDtoRes = new ProductResponseDTO("teste","cafe", StockType.FRESH);
    ProductRequestDTO productDtoReq = new ProductRequestDTO("teste","cafe", StockType.FRESH);

    List<ProductResponseDTO> listProductResp = new ArrayList();
    ProductService productService;


    @Test
    public void createProductDto(){
        productService = Mockito.mock(ProductService.class);

        Mockito.when(productService.save(Mockito.any(),Mockito.any())).thenReturn(ResponseEntity.accepted().body(product));
        Mockito.when(productService.convert(Mockito.any())).thenReturn(product);

        ProductController productController = new ProductController (productService);
        productController.createProduct(productDtoReq, null);

        assert(product.getId() != null);
    }

    @Test
    public void listProductOk(){
        listProductResp.add(productDtoRes);

        productService = Mockito.mock(ProductService.class);
        Mockito.when(productService.listProductAll()).thenReturn(listProductResp);

        ProductController productController = new ProductController (productService);
        productController.listProduct();

        assert (productService.listProductAll().size() == 1);
    }

    @Test
    public void getByIdOK(){
        productService = Mockito.mock(ProductService.class);

        Mockito.when(productService.convertToDto(Mockito.any())).thenReturn(productDtoRes);
        Mockito.when(productService.obtem(Mockito.any())).thenReturn(product);

        ProductController productController = new ProductController(productService);
        productController.getById(1l);

        assert (productDtoRes.getName() != null);
    }

    @Test
    public void getByNameOK(){
        listProductResp.add(productDtoRes);

        productService = Mockito.mock(ProductService.class);

        Mockito.when(productService.listProduct(Mockito.anyString())).thenReturn(listProductResp);

        ProductController productController = new ProductController (productService);
        productController.getByName("teste");

        assert (productService.listProduct("").size() == 1);
    }

    @Test
    public void updateProductOK(){
         productService = Mockito.mock(ProductService.class);

        Mockito.when(productService.obtem(Mockito.anyLong())).thenReturn(product);
        Mockito.when(productService.validaUpdate(Mockito.any(), Mockito.any())).thenReturn(product);
        Mockito.when(productService.save(Mockito.any(), Mockito.any())).thenReturn(ResponseEntity.accepted().body(product));
        Mockito.when(productService.convertToDto(Mockito.any())).thenReturn(productDtoRes);

        ProductController productController = new ProductController (productService);
        productController.updateProduct(1l, productDtoReq, null);

        assert (productDtoReq.getName().equals(product.getName()));
    }

    @Test
    public void deleteProductOK(){
        productService= Mockito.mock(ProductService.class);

        Mockito.when(productService.obtem(Mockito.any())).thenReturn(product);
        Mockito.doNothing().when(productService).deletaProduct(Mockito.anyLong());
        Mockito.when(productService.convertToDto(Mockito.any())).thenReturn(productDtoRes);

        ProductController productController = new ProductController (productService);
        productController.deleteProduct(1l);

        assert (product.getId() == 1);
    }
}
