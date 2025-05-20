package com.esgScore.server.service;

import com.esgScore.server.domain.InterestCorporationCategory;
import com.esgScore.server.domain.dto.CategoryDTO;
import com.esgScore.server.domain.dto.InterestCorporationCategoryCreateDTO;
import com.esgScore.server.domain.dto.InterestCorporationCategoryDTO;
import com.esgScore.server.domain.dto.interest.InterestCorporationDTO;
import com.esgScore.server.domain.dto.interest.InterestCorporationDetailDTO;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.mapper.InterestCorporationCategoryMapper;
import com.esgScore.server.repository.main.InterestCorporationCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InterestCorporationCategoryService {
  private final InterestCorporationCategoryRepository interestCorporationCategoryRepository;
  private final InterestCorporationService interestCorporationService;
  private final CategoryService categoryService;

  public List<InterestCorporationCategoryDTO> getAll(){
    List<InterestCorporationCategory> interestCorporationCategories = interestCorporationCategoryRepository.findAll();

    List<InterestCorporationCategoryDTO> result = new ArrayList<>();

    for (InterestCorporationCategory interestCorporationCategory : interestCorporationCategories) {
      InterestCorporationDetailDTO detailDTO = interestCorporationService.getById(interestCorporationCategory.getInterestCorporationId());
      CategoryDTO categoryDTO = categoryService.getById(interestCorporationCategory.getCategoryId());

      InterestCorporationCategoryDTO dto = InterestCorporationCategoryMapper.toDTO(interestCorporationCategory, detailDTO, categoryDTO);

      result.add(dto);
    }

    return result;
  }

  public List<InterestCorporationCategoryDTO> getAllByCategoryId(String categoryId) {
    List<InterestCorporationCategory> interestCorporationCategories = interestCorporationCategoryRepository.findByCategoryId(categoryId);

    List<InterestCorporationCategoryDTO> result = new ArrayList<>();

    for (InterestCorporationCategory interestCorporationCategory : interestCorporationCategories) {
      InterestCorporationDetailDTO detailDTO = interestCorporationService.getById(interestCorporationCategory.getInterestCorporationId());
      CategoryDTO categoryDTO = categoryService.getById(interestCorporationCategory.getCategoryId());

      InterestCorporationCategoryDTO dto = InterestCorporationCategoryMapper.toDTO(interestCorporationCategory, detailDTO, categoryDTO);

      result.add(dto);
    }

    return result;
  }


  public InterestCorporationCategoryDTO create(String interestCorporationId, String categoryId) {
    InterestCorporationDetailDTO interestCorporationDTO = interestCorporationService.getById(interestCorporationId);
    CategoryDTO categoryDTO = categoryService.getById(categoryId);

    InterestCorporationCategoryCreateDTO interestCorporationCategoryCreateDTO = InterestCorporationCategoryCreateDTO.builder()
            .interestCorporationId(interestCorporationDTO.getId())
            .categoryId(categoryId)
            .build();

    InterestCorporationCategory interestCorporationCategory = InterestCorporationCategoryMapper.fromDTO(interestCorporationCategoryCreateDTO);

    InterestCorporationCategory savedInterestCorporationCategory = interestCorporationCategoryRepository.save(interestCorporationCategory);

    return InterestCorporationCategoryMapper.toDTO(savedInterestCorporationCategory, interestCorporationDTO, categoryDTO);
  }

  public void delete(String id) {
    InterestCorporationCategory interestCorporationCategory = interestCorporationCategoryRepository.findById(id).orElseThrow(() -> new NotFoundException("카테고리 내 관심 기업을 찾을 수 없습니다."));
    interestCorporationCategoryRepository.delete(interestCorporationCategory);
  }




}
