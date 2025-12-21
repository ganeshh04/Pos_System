package com.gatmane.Service;

import com.gatmane.model.Product;
import com.gatmane.model.User;
import com.gatmane.payload.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO, User user)throws Exception;
    ProductDTO updateProduct(Long id,ProductDTO productDTO, User user) throws Exception;
    void deleteProduct(Long id,User user) throws Exception;
    List<ProductDTO>getProductByStoreId(Long storeId);
    List<ProductDTO>searchByKeyword(Long storeId,String keyword);

}
