package com.esgScore.server.controller;

import com.esgScore.server.domain.dto.IdListDTO;
import com.esgScore.server.domain.dto.InterestCorporationCategoryCreateDTO;
import com.esgScore.server.domain.dto.InterestCorporationCategoryDTO;
import com.esgScore.server.service.InterestCorporationCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interest-corporation-categories")
@RequiredArgsConstructor
@Slf4j
public class InterestCorporationCategoryController {

  private final InterestCorporationCategoryService interestCorporationCategoryService;

  @GetMapping("")
  public ResponseEntity<List<InterestCorporationCategoryDTO>> getAll() {
    List<InterestCorporationCategoryDTO> interestCorporationCategoryDTOS = interestCorporationCategoryService.getAll();

    return ResponseEntity.ok(interestCorporationCategoryDTOS);
  }

  @PostMapping("")
  public ResponseEntity<InterestCorporationCategoryDTO> create(@RequestBody InterestCorporationCategoryCreateDTO createDTO) {
    InterestCorporationCategoryDTO interestCorporationCategoryDTO = interestCorporationCategoryService.create(createDTO.getInterestCorporationId(), createDTO.getCategoryId());
    return ResponseEntity.status(HttpStatus.CREATED).body(interestCorporationCategoryDTO);
  }

  @GetMapping("/category/{categoryId}")
  public ResponseEntity<List<InterestCorporationCategoryDTO>> getAllByCategoryId(@PathVariable String categoryId) {
    List<InterestCorporationCategoryDTO> interestCorporationCategoryDTOS = interestCorporationCategoryService.getAllByCategoryId(categoryId);

    return ResponseEntity.ok(interestCorporationCategoryDTOS);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    interestCorporationCategoryService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/corporations/{corporationId}/categories")
  public ResponseEntity<List<InterestCorporationCategoryDTO>> addCorporationToCategories(@RequestBody IdListDTO idListDTO, @PathVariable String corporationId) {
    List<InterestCorporationCategoryDTO> interestCorporationCategoryDTOs = interestCorporationCategoryService.addCorporationToCategories(corporationId, idListDTO.getIdList());
    return ResponseEntity.status(HttpStatus.CREATED).body(interestCorporationCategoryDTOs);
  }

  @PostMapping("/categories/{categoryId}/corporations")
  public ResponseEntity<List<InterestCorporationCategoryDTO>> addCorporationsToCategory(@RequestBody IdListDTO idListDTO, @PathVariable String categoryId) {
    List<InterestCorporationCategoryDTO> interestCorporationCategoryDTOs = interestCorporationCategoryService.addCorporationsToCategory(idListDTO.getIdList(), categoryId);
    return ResponseEntity.status(HttpStatus.CREATED).body(interestCorporationCategoryDTOs);
  }



}
