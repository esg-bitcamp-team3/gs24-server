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
import java.util.Optional;

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
  public List<InterestCorporationCategoryDTO> addCorporationToCategories(String corporationId, List<String> categoryIds, String userId) {
    CorporationDTO corporationDTO = corporationService.getById(corporationId);


    // find existing interest corporation
    InterestCorporationDTO interestCorporationDTO = interestCorporationService.getByUserAndCorporation(userId, corporationId);

    // if not exist, then create new
    if (interestCorporationDTO == null) {
      interestCorporationService.addInterestCorporation(userId, corporationId);
      interestCorporationDTO = interestCorporationService.getByUserAndCorporation(userId, corporationId);
    }

    // delete existing categories
    List<InterestCorporationCategory> existingCategories = interestCorporationCategoryRepository.findByInterestCorporationId(interestCorporationDTO.getId());
    interestCorporationCategoryRepository.deleteAll(existingCategories);

    //if id list is empty then delete interest
    if (categoryIds.isEmpty())
      interestCorporationService.deleteById(interestCorporationDTO.getId());


    // create
    List<InterestCorporationCategoryDTO> result = new ArrayList<>();

    for (String categoryId : categoryIds) {
      CategoryDTO categoryDTO = categoryService.getById(categoryId);

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

  @Transactional
  public List<InterestCorporationCategoryDTO> addCorporationsToCategory(List<String> corporationIds, String categoryId, String userId) {
    CategoryDTO categoryDTO = categoryService.getById(categoryId);

    // delete all existing
    List<InterestCorporationCategory> existingCategories = interestCorporationCategoryRepository.findByCategoryId(categoryId);
    interestCorporationCategoryRepository.deleteAll(existingCategories);

    List<InterestCorporationCategoryDTO> result = new ArrayList<>();

    for (String corporationId : corporationIds) {
      CorporationDTO corporationDTO = corporationService.getById(corporationId);


      InterestCorporationDTO interestCorporationDTO = interestCorporationService.getByUserAndCorporation(userId, corporationDTO.getId());

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

    // delete
    List<InterestCorporationDTO> userInterestCorporations = interestCorporationService.getAllByUserId(userId);
    for (InterestCorporationDTO existInterestCorporation : userInterestCorporations) {
      long count = interestCorporationCategoryRepository.countByInterestCorporationId(existInterestCorporation.getId());
      if (count == 0) {
        interestCorporationService.deleteById(existInterestCorporation.getId());
      }
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
