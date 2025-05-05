package com.esgScore.server.controller;

import com.esgScore.server.annotation.Login;
import com.esgScore.server.domain.Organization;
import com.esgScore.server.domain.User;
import com.esgScore.server.domain.dto.UserDTO;
import com.esgScore.server.domain.dto.UserOrganizationListDTO;
import com.esgScore.server.repository.OrganizationRepository;
import com.esgScore.server.repository.UserRepository;
import com.esgScore.server.service.InterestOrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/interestOrganization")
@RequiredArgsConstructor
@Slf4j
public class InterestOrganizationController {
  private final InterestOrganizationService interestOrganizationService;

  @GetMapping
  public ResponseEntity<UserOrganizationListDTO> getInterestOrganization(@Login UserDTO user){
    UserOrganizationListDTO userOrganizationListDTO = interestOrganizationService.getUserOrganizationListDTO(user.getId());

    return ResponseEntity.ok(userOrganizationListDTO);
  }

  @PostMapping("/{id}")
  public ResponseEntity<String> addInterestOrganization(@Login UserDTO user, @PathVariable String id) {

    return ResponseEntity.status(HttpStatus.CREATED)
      .body(interestOrganizationService.addInterestOrganization(user.getId(), id));
  }

  @DeleteMapping
  public ResponseEntity<String> deleteInterestOrganization(@Login UserDTO user, @RequestBody String id) {

    return ResponseEntity.ok(interestOrganizationService.deleteInterestOrganization(user.getId(), id));
  }
}
