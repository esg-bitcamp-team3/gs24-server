package com.esgScore.server.service;

import com.esgScore.server.domain.Category;
import com.esgScore.server.domain.dto.CategoryDTO;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.mapper.CategoryMapper;
import com.esgScore.server.repository.main.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
  private final CategoryRepository categoryRepository;

  public List<CategoryDTO> getAll() {
    List<Category> categories = categoryRepository.findAll();

      return categories.stream()
              .map(CategoryMapper::toDTO)
              .collect(Collectors.toList());
  }

  public CategoryDTO getById(String id) {
    Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Category not found"));

    return CategoryMapper.toDTO(category);
  }

  public List<CategoryDTO> getAllByUserId(String userId) {
    List<Category> categories = categoryRepository.findAllByUserId(userId);
    return categories.stream()
            .map(CategoryMapper::toDTO)
            .collect(Collectors.toList());
  }

  @Transactional
  public CategoryDTO create(String userId, CategoryDTO categoryDTO) {
     Category category = CategoryMapper.fromDTO(categoryDTO);
     category.setUserId(userId);
     Category savedCategory = categoryRepository.save(category);

     return CategoryMapper.toDTO(savedCategory);
  }


  @Transactional
  public CategoryDTO update(String id, CategoryDTO categoryDTO) {
    Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));


    if (categoryDTO.getName() != null) {
      category.setName(categoryDTO.getName());
    }

    Category updatedCategory = categoryRepository.save(category);

    return CategoryMapper.toDTO(updatedCategory);
  }

  public void delete(String id) {
    Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
    categoryRepository.delete(category);
  }
}
