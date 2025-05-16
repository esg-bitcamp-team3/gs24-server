package com.esgScore.server.controller;

import com.esgScore.server.annotation.Login;
import com.esgScore.server.domain.dto.*;
import com.esgScore.server.domain.dto.interest.InterestCompanyInfoDTO;
import com.esgScore.server.domain.dto.interest.OrganizationWithInterestDTOPage;
import com.esgScore.server.service.InterestOrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        .body(interestOrganizationService.addInterestOrganization(user, id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteInterestOrganization(@Login UserDTO user, @PathVariable String id) {
    return ResponseEntity.ok(interestOrganizationService.deleteInterestOrganization(user, id));
  }

  @GetMapping("/organizations")
  public ResponseEntity<OrganizationWithInterestDTOPage> getOrganizationsWithInterest(@Login UserDTO user,
                                                                                        @RequestParam(defaultValue = "0") int page,
                                                                                        @RequestParam(defaultValue = "20") int size) {
    OrganizationWithInterestDTOPage list = interestOrganizationService.getAllOrganizationsWithInterest(user.getId(), page, size);
    return ResponseEntity.ok(list);
  }

  @GetMapping("/interest")
  public ResponseEntity<List<InterestCompanyInfoDTO>> getIOInfo(@Login UserDTO user) {
    List<InterestCompanyInfoDTO> list = interestOrganizationService.getCompanyInfoInUser(user.getId());
    log.info("getIOInfo: {}", list);
    return ResponseEntity.ok(list);
  }
}
