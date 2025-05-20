package com.esgScore.server.controller;

import com.esgScore.server.annotation.Login;
import com.esgScore.server.domain.dto.UserDTO;

import com.esgScore.server.domain.dto.corporation.CorpWithInterestPage;
import com.esgScore.server.domain.dto.interest.InterestCorporationDTO;
import com.esgScore.server.service.InterestCorporationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interest-corporations")
@RequiredArgsConstructor
@Slf4j
public class InterestCorporationController {
  private final InterestCorporationService interestCorporationService;

  @GetMapping("/{id}")
  public ResponseEntity<Boolean> getInterestCorporation(@Login UserDTO user, @PathVariable String id){
    return ResponseEntity.ok(interestCorporationService.existsByUserAndCorporation(user.getId(), id));
  }

  @PostMapping("/{id}")
  public ResponseEntity<String> addInterestCorporation(@Login UserDTO user, @PathVariable String id){
    return ResponseEntity.ok(interestCorporationService.addInterestCorporation(user.getId(), id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteInterestCorporation(@Login UserDTO user, @PathVariable String id){
    return ResponseEntity.ok(interestCorporationService.deleteInterestCorporation(user.getId(), id));
  }
//  @GetMapping
//  public ResponseEntity<UserOrganizationListDTO> getInterestOrganization(@Login UserDTO user){
//    UserOrganizationListDTO userOrganizationListDTO = interestCorporationService.getUserOrganizationListDTO(user.getId());
//    return ResponseEntity.ok(userOrganizationListDTO);
//  }
//
//  @PostMapping("/{id}")
//  public ResponseEntity<String> addInterestOrganization(@Login UserDTO user, @PathVariable String id) {
//      return ResponseEntity.status(HttpStatus.CREATED)
//        .body(interestCorporationService.addInterestOrganization(user, id));
//  }
//
//  @DeleteMapping("/{id}")
//  public ResponseEntity<String> deleteInterestOrganization(@Login UserDTO user, @PathVariable String id) {
//    return ResponseEntity.ok(interestCorporationService.deleteInterestOrganization(user, id));
//  }

  @GetMapping("/corporations")
  public ResponseEntity<CorpWithInterestPage> getCorporationsWithInterest(@Login UserDTO user,
                                                                           @RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "20") int size) {
    CorpWithInterestPage list = interestCorporationService.getAllCorpsWithInterest(user.getId(), page, size);
    return ResponseEntity.ok(list);
  }

  @GetMapping("/my")
  public ResponseEntity<List<InterestCorporationDTO>> getCorporationsWithInterest(@Login UserDTO user) {
    List<InterestCorporationDTO> interestCorporationDTOS = interestCorporationService.getInterestcorporationDTOList(user.getId());
    return ResponseEntity.ok(interestCorporationDTOS);
  }

//  @GetMapping("/interest")
//  public ResponseEntity<List<InterestCompanyInfoDTO>> getIOInfo(@Login UserDTO user) {
//    List<InterestCompanyInfoDTO> list = interestCorporationService.getCompanyInfoInUser(user.getId());
//    log.info("getIOInfo: {}", list);
//    return ResponseEntity.ok(list);
//  }
}
