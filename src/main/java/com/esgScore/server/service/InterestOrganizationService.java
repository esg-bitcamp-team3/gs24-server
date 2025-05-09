package com.esgScore.server.service;

import com.esgScore.server.domain.InterestOrganization;
import com.esgScore.server.domain.dto.OrganizationInfoDTO;
import com.esgScore.server.domain.dto.UserDTO;
import com.esgScore.server.domain.dto.UserOrganizationListDTO;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.repository.main.InterestOrganizationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
    List<InterestOrganization> interestOrganizations = interestOrganizationRepository.findListByUserId(loginUser.getId()
      .describeConstable().orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다.")));

    List<OrganizationInfoDTO> organizationInfoDTOList = interestOrganizations.stream()
      .map(io -> OrganizationInfoDTO.toDTO(io, organizationService.getById(io.getOrganizationId())))
      .collect(Collectors.toList());

    return UserOrganizationListDTO.toDTO(loginUser, organizationInfoDTOList);
  }

  public String addInterestOrganization(UserDTO user, String organizationId) {
    organizationService.getById(organizationId);

    InterestOrganization addInterestOrganization = InterestOrganization.builder()
      .userId(user.getId())
      .organizationId(organizationId)
      .checkTime(LocalDateTime.now())
      .build();

    interestOrganizationRepository.save(addInterestOrganization);
    return "추가 성공";
  }

  public String deleteInterestOrganization(String id) {
    InterestOrganization interestOrganization = interestOrganizationRepository.findById(id).orElseThrow(() -> new NotFoundException("없는 정보 입니다."));

    interestOrganizationRepository.delete(interestOrganization);
    return "삭제 성공";
  }
}
