package com.esgScore.server.controller;

import com.esgScore.server.annotation.Login;
import com.esgScore.server.domain.dto.UserDTO;
import com.esgScore.server.domain.dto.corporation.CorporationDTO;
import com.esgScore.server.domain.dto.corporation.CorporationDetailDTO;
import com.esgScore.server.exceptions.InvalidRequestException;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.service.CorporationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/corporations")
@RequiredArgsConstructor
@Slf4j
public class CorporationController {

  private final CorporationService corporationService;

  @GetMapping
  public ResponseEntity<List<CorporationDTO>> getAll() {
      List<CorporationDTO> corporations = corporationService.getAll();

      return ResponseEntity.ok(corporations);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CorporationDetailDTO> getById(@PathVariable String id) {
    try {
      CorporationDetailDTO corporation = corporationService.getById(id);
      return ResponseEntity.ok(corporation);
    } catch (NotFoundException e) {
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping
  public ResponseEntity<CorporationDetailDTO> create(@RequestBody CorporationDetailDTO corporation) {
    try {
      CorporationDetailDTO corporationCreated = corporationService.create(corporation);
      return ResponseEntity.status(HttpStatus.CREATED).body(corporationCreated);
    } catch (InvalidRequestException e) {
      return ResponseEntity.badRequest().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PatchMapping("/{id}")
  public ResponseEntity<CorporationDetailDTO> update(@PathVariable String id, @RequestBody CorporationDetailDTO corporation) {
    try {
      CorporationDetailDTO corporationUpdated = corporationService.update(id, corporation);
      return ResponseEntity.ok(corporationUpdated);
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
      corporationService.delete(id);
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
