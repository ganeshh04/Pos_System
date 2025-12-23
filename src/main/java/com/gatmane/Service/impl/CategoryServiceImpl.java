package com.gatmane.Service.impl;

import com.gatmane.Service.CategoryService;
import com.gatmane.Service.UserService;
import com.gatmane.domain.UserRole;
import com.gatmane.exceptions.UserException;
import com.gatmane.mapper.CategoryMapper;
import com.gatmane.model.Category;
import com.gatmane.model.Store;
import com.gatmane.model.User;
import com.gatmane.payload.dto.CategoryDTO;
import com.gatmane.repository.CategoryRepository;
import com.gatmane.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private  final StoreRepository storeRepository;
    @Override
    public CategoryDTO createCategory(CategoryDTO dto) throws Exception {
        User user=userService.getCurrentUser();

        Store store= storeRepository.findById(dto.getStoreId()).orElseThrow(
                ()->new Exception("Store not found")
        );

        Category category=Category.builder()
                .store(store)
                .name(dto.getName())
                .build();

        checkAuthority(user,category.getStore());


        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDTO> getCategoriesByStore(Long storeId) {
        List<Category>categories=categoryRepository.findAllByStoreId(storeId);
        return categories.stream()
                .map(
                        CategoryMapper::toDTO
                ).collect(Collectors.toList());

    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO dto) throws Exception {
        Category category=categoryRepository.findById(id).orElseThrow(
                ()->new Exception("category not found")
        );
        User user=userService.getCurrentUser();
        category.setName(dto.getName());
        checkAuthority(user,category.getStore());

        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        Category category=categoryRepository.findById(id).orElseThrow(
                ()->new Exception("category not found")
        );
        User user=userService.getCurrentUser();
        checkAuthority(user,category.getStore());
        categoryRepository.delete(category);

    }

    private void checkAuthority(User user,Store store) throws Exception {
        boolean isAdmin=user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
        boolean isManager=user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore=user.equals(store.getStoreAdmin());

        if(!(isAdmin && isSameStore) && !isManager){
            throw new Exception("you don't have permission to manage this category");
        }

    }

    //ganesh=>storeAdmin
    //store 1=>ganesh
}
