package com.esgScore.server.controller;

import com.esgScore.server.domain.dto.OrganizationDTO;
import com.esgScore.server.errors.ErrorResponse;
import com.esgScore.server.exceptions.InvalidRequestException;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.service.OrganizationService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
@Slf4j
public class OrganizationController {

  private final OrganizationService organizationService;

  @GetMapping
  public ResponseEntity<List<OrganizationDTO>> getAll() {
      List<OrganizationDTO> organizations = organizationService.getAll();

      return ResponseEntity.ok(organizations);
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrganizationDTO> getById(@PathVariable String id) {
    try {
      OrganizationDTO organization = organizationService.getById(id);
      return ResponseEntity.ok(organization);
    } catch (NotFoundException e) {
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping
  public ResponseEntity<OrganizationDTO> create(@RequestBody OrganizationDTO organization) {
    try {
      OrganizationDTO organizationCreated = organizationService.create(organization);
      return ResponseEntity.status(HttpStatus.CREATED).body(organizationCreated);
    } catch (InvalidRequestException e) {
      return ResponseEntity.badRequest().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PatchMapping("/{id}")
  public ResponseEntity<OrganizationDTO> update(@PathVariable String id, @RequestBody  OrganizationDTO organization) {
    try {
      OrganizationDTO organizationUpdated = organizationService.update(id, organization);
      return ResponseEntity.ok(organizationUpdated);
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
      organizationService.delete(id);
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
