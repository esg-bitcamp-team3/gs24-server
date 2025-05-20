package com.esgScore.server.mapper;

import com.esgScore.server.domain.Category;
import com.esgScore.server.domain.dto.CategoryDTO;

import java.util.List;

public class CategoryMapper {
    public static CategoryDTO toDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static Category fromDTO(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }

        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
