package com.gatmane.mapper;

import com.gatmane.model.Category;
import com.gatmane.payload.dto.CategoryDTO;

public class CategoryMapper {

    public  static CategoryDTO toDTO(Category category){
        return CategoryDTO.builder()
                .Id(category.getId())
                .name(category.getName())
                .storeId(category.getStore()!=null?category.getStore().getId():null)

                .build();


    }
}
