package com.gatmane.Service.impl;

import com.gatmane.Service.ProductService;
import com.gatmane.mapper.ProductMapper;
import com.gatmane.model.Product;
import com.gatmane.model.Store;
import com.gatmane.model.User;
import com.gatmane.payload.dto.ProductDTO;
import com.gatmane.repository.ProductRepository;
import com.gatmane.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
  private final StoreRepository storeRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, User user) throws Exception{
        Store store=storeRepository.findById(
                productDTO.getStoreId()
                ).orElseThrow(
                        ()->new Exception("Store not found")
                );
        Product product= ProductMapper.toEntity(productDTO,store);
        Product savedProduct=productRepository.save(product);
        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) throws Exception {

        Product product=productRepository.findById(id).orElseThrow(
                ()->new Exception("product not found")
        );
        product.setName(product.getName());
        product.setDescription(product.getDescription());
        product.setSku(product.getSku());
        product.setMrp(productDTO.getMrp());
        product.setSellingPrice(product.getSellingPrice());
        product.setBrand(product.getBrand());
        product.setUpdatedAt(LocalDateTime.now());
        product.setImage(product.getImage());
        Product savedProduct=productRepository.save(product);
        return ProductMapper.toDTO(savedProduct);

    }

    @Override
    public void deleteProduct(Long id, User user) throws Exception {

        Product product=productRepository.findById(id).orElseThrow(
                ()->new Exception("product not found")
        );
        productRepository.delete(product);
    }

    @Override
    public List<ProductDTO> getProductByStoreId(Long storeId) {
        List<Product>products=productRepository.findByStoreId(storeId);
        return products.stream().map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> searchByKeyword(Long storeId, String keyword) {
        List<Product>products=productRepository.searchByKeyword(storeId,keyword);
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());

    }
}
