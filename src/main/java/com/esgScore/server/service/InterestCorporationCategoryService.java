package com.esgScore.server.service;

import com.esgScore.server.domain.InterestCorporationCategory;
import com.esgScore.server.domain.dto.CategoryDTO;
import com.esgScore.server.domain.dto.InterestCorporationCategoryCreateDTO;
import com.esgScore.server.domain.dto.InterestCorporationCategoryDTO;
import com.esgScore.server.domain.dto.corporation.CorporationDTO;
import com.esgScore.server.domain.dto.interest.InterestCorporationDTO;
import com.esgScore.server.domain.dto.interest.InterestCorporationDetailDTO;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.mapper.InterestCorporationCategoryMapper;
import com.esgScore.server.repository.main.InterestCorporationCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InterestCorporationCategoryService {
  private final InterestCorporationCategoryRepository interestCorporationCategoryRepository;
  private final InterestCorporationService interestCorporationService;
  private final CategoryService categoryService;
  private final CorporationService corporationService;

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

  @Transactional
  public InterestCorporationCategoryDTO create(String interestCorporationId, String categoryId) {
    InterestCorporationDetailDTO interestCorporationDTO = interestCorporationService.getById(interestCorporationId);
    CategoryDTO categoryDTO = categoryService.getById(categoryId);

    validateNotExists(interestCorporationId, categoryId);

    InterestCorporationCategoryCreateDTO interestCorporationCategoryCreateDTO = InterestCorporationCategoryCreateDTO.builder()
            .interestCorporationId(interestCorporationDTO.getId())
            .categoryId(categoryId)
            .build();

    InterestCorporationCategory interestCorporationCategory = InterestCorporationCategoryMapper.fromDTO(interestCorporationCategoryCreateDTO);

    InterestCorporationCategory savedInterestCorporationCategory = interestCorporationCategoryRepository.save(interestCorporationCategory);

    return InterestCorporationCategoryMapper.toDTO(savedInterestCorporationCategory, interestCorporationDTO, categoryDTO);
  }

  @Transactional
  public List<InterestCorporationCategoryDTO> addCorporationToCategories(String corporationId, List<String> categoryIds) {
    CorporationDTO corporationDTO = corporationService.getById(corporationId);
    List<InterestCorporationCategoryDTO> result = new ArrayList<>();

    for (String categoryId : categoryIds) {
      CategoryDTO categoryDTO = categoryService.getById(categoryId);
      String userId = categoryDTO.getUserId();

      InterestCorporationDTO interestCorporationDTO = interestCorporationService.getByUserAndCorporation(userId, corporationId);

      if (interestCorporationDTO == null) {
        interestCorporationService.addInterestCorporation(userId, corporationId);
        interestCorporationDTO = interestCorporationService.getByUserAndCorporation(userId, corporationId);
      }

      validateNotExists(interestCorporationDTO.getId(), categoryId);

      InterestCorporationCategoryCreateDTO createDTO = InterestCorporationCategoryCreateDTO.builder()
              .interestCorporationId(interestCorporationDTO.getId())
              .categoryId(categoryId)
              .build();

      InterestCorporationCategory entity = InterestCorporationCategoryMapper.fromDTO(createDTO);
      InterestCorporationCategory saved = interestCorporationCategoryRepository.save(entity);
      InterestCorporationDetailDTO detailDTO = interestCorporationService.getById(interestCorporationDTO.getId());

      result.add(InterestCorporationCategoryMapper.toDTO(saved, detailDTO, categoryDTO));
    }

    return result;
  }

  public void delete(String id) {
    InterestCorporationCategory interestCorporationCategory = interestCorporationCategoryRepository.findById(id).orElseThrow(() -> new NotFoundException("카테고리 내 관심 기업을 찾을 수 없습니다."));
    interestCorporationCategoryRepository.delete(interestCorporationCategory);
  }

  public void validateNotExists(String interestCorporationId, String categoryId) {
    boolean exists = interestCorporationCategoryRepository
            .findByCategoryIdAndInterestCorporationId(categoryId, interestCorporationId)
            .isPresent();

    if (exists) {
      throw new IllegalStateException("이미 해당 관심기업이 이 카테고리에 등록되어 있습니다.");
    }
  }



}
