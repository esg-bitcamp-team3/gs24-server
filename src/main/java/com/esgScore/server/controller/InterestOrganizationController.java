package com.esgScore.server.controller;

import com.esgScore.server.annotation.Login;
import com.esgScore.server.domain.Organization;
import com.esgScore.server.domain.User;
import com.esgScore.server.domain.dto.UserDTO;
import com.esgScore.server.repository.OrganizationRepository;
import com.esgScore.server.repository.UserRepository;
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
  private final UserRepository userRepository;
  private final OrganizationRepository organizationRepository;

  @GetMapping
  public ResponseEntity<List<Organization>> getInterestOrganization(@Login UserDTO user){
    List<Organization> organizationList = user.getInterestOrganization();

    return ResponseEntity.ok(organizationList);
  }

  @PostMapping("/{id}")
  public ResponseEntity<String> addInterestOrganization(@Login UserDTO user, @PathVariable String id) {
    Optional<Organization> organizationOptional = organizationRepository.findById(id);

    if (organizationOptional.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("없는 기업입니다");
    }

    Organization organization = organizationOptional.get();
    List<Organization> organizationList = user.getInterestOrganization();


    // 중복 추가 방지 로직 (선택)
    if (organizationList.contains(organization)) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 관심 목록에 있습니다.");
    }

    organizationList.add(organization);
    Optional<User> loginUser = userRepository.findById(user.getId());
    if (loginUser.isPresent()) {
      loginUser.get().setInterestOrganization(organizationList);
      userRepository.save(loginUser.get());
    }

    return ResponseEntity.status(HttpStatus.CREATED).body("추가 성공");
  }

  @DeleteMapping
  public ResponseEntity<String> deleteInterestOrganization(@Login User user, @RequestBody String organizationName) {
    Optional<Organization> organizationOptional = organizationRepository.findByName(organizationName);

    if (organizationOptional.isEmpty()) {
      return ResponseEntity.ok("없는 기업입니다.");
    }

    Organization organization = organizationOptional.get();
    List<Organization> organizationList = user.getInterestOrganization();

    if (organizationList == null || !organizationList.contains(organization)) {
      return ResponseEntity.ok("관심 목록에 없는 기업입니다.");
    }

    organizationList.remove(organization);
    user.setInterestOrganization(organizationList);
    userRepository.save(user);

    return ResponseEntity.ok("삭제 성공");
  }
}
