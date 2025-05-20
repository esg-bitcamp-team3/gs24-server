package com.esgScore.server.controller;

import com.esgScore.server.annotation.Login;
import com.esgScore.server.domain.dto.CategoryDTO;
import com.esgScore.server.domain.dto.UserDTO;
import com.esgScore.server.exceptions.InvalidRequestException;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<CategoryDTO>> getAll() {
      List<CategoryDTO> categoryDTOList = categoryService.getAll();

      return ResponseEntity.ok(categoryDTOList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryDTO> getById(@PathVariable String id) {
    try {
      CategoryDTO category = categoryService.getById(id);
      return ResponseEntity.ok(category);
    } catch (NotFoundException e) {
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping("/my")
  public ResponseEntity<List<CategoryDTO>> getAllByUser(@Login UserDTO user) {
    try {
      List<CategoryDTO> categoryDTOList = categoryService.getAllByUserId(user.getId());
      return ResponseEntity.ok(categoryDTOList);
    } catch (NotFoundException e) {
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping
  public ResponseEntity<CategoryDTO> create(@Login UserDTO user, @RequestBody CategoryDTO category) {
    try {
      CategoryDTO categoryCreated = categoryService.create(user.getId(), category);
      return ResponseEntity.status(HttpStatus.CREATED).body(categoryCreated);
    } catch (InvalidRequestException e) {
      return ResponseEntity.badRequest().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PatchMapping("/{id}")
  public ResponseEntity<CategoryDTO> update(@PathVariable String id, @RequestBody CategoryDTO category) {
    try {
      CategoryDTO categoryUpdated = categoryService.update(id, category);
      return ResponseEntity.ok(categoryUpdated);
    }
    catch (NotFoundException e) {
      return ResponseEntity.notFound().build();
    }
    catch (InvalidRequestException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    try {
      categoryService.delete(id);
      return ResponseEntity.noContent().build();
    }
    catch (NotFoundException e) {
      return ResponseEntity.notFound().build();
    }
    catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }





}
