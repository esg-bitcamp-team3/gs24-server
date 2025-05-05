package com.esgScore.server.service;

import com.esgScore.server.domain.InterestOrganization;
import com.esgScore.server.domain.dto.OrganizationInfoDTO;
import com.esgScore.server.domain.dto.UserDTO;
import com.esgScore.server.domain.dto.UserOrganizationListDTO;
import com.esgScore.server.repository.InterestOrganizationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InterestOrganizationService {
  private final InterestOrganizationRepository interestOrganizationRepository;
  private final OrganizationService organizationService;
  private final UserService userService;

  public UserOrganizationListDTO getUserOrganizationListDTO(String userId) {
    UserDTO loginUser = userService.getUser(userId);
    List<InterestOrganization> interestOrganizations = interestOrganizationRepository.findByUserId(loginUser.getId());

    // 효율적?
    List<String> organizationIds = interestOrganizations.stream()
      .map(InterestOrganization::getOrganizationId)
      .distinct()
      .toList();
    Map<String, String> organizationNameMap = organizationService.getNamesByIds(organizationIds);
    // Map<orgId, orgName>

    List<OrganizationInfoDTO> organizationInfoDTOList = interestOrganizations.stream()
      .map(org -> OrganizationInfoDTO.builder()
        .organizationId(org.getOrganizationId())
        .name(organizationNameMap.getOrDefault(org.getOrganizationId(), ""))
        .checkTime(org.getCheckTime())
        .build())
      .collect(Collectors.toList());

    return UserOrganizationListDTO.builder()
      .userId(loginUser.getId())
      .username(loginUser.getName())
      .organizationList(organizationInfoDTOList)
      .build();
  }

  public String addInterestOrganization(String userId, String organizationId) {
    if(userService.getUser(userId) == null || organizationService.getById(organizationId) == null) {
      return "정보가 없습니다.";
    }
    InterestOrganization addInterestOrganization = InterestOrganization.builder()
      .userId(userId)
      .organizationId(organizationId)
      .checkTime(LocalDateTime.now())
      .build();

    interestOrganizationRepository.save(addInterestOrganization);
    return "추가 성공";
  }

  public String deleteInterestOrganization(String userId, String organizationId) {
    InterestOrganization interestOrganization = interestOrganizationRepository.findByUserIdAndOrganizationId(userId, organizationId).orElse(null);
    if(interestOrganization == null) {
      return "없는 회원입니다";
    }
    if(!interestOrganization.getOrganizationId().equals(organizationId)) {
      return "관심 기업이 아닙니다";
    }

    interestOrganizationRepository.delete(interestOrganization);
    return "삭제 성공";
  }
}
