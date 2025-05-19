package com.esgScore.server.service;

import com.esgScore.server.domain.InterestOrganization;
import com.esgScore.server.domain.dto.*;
import com.esgScore.server.domain.dto.corporation.CorpWithInterestDTO;
import com.esgScore.server.domain.dto.corporation.CorpWithInterestPage;
import com.esgScore.server.domain.dto.corporation.CorporationDTO;
import com.esgScore.server.domain.dto.interest.InterestCompanyInfoDTO;
import com.esgScore.server.domain.dto.interest.InterestCorporationDTO;
import com.esgScore.server.domain.dto.interest.OrganizationWithInterestDTOPage;
import com.esgScore.server.exceptions.DuplicateException;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.repository.main.InterestOrganizationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InterestOrganizationService {
  private final InterestOrganizationRepository interestOrganizationRepository;
  private final OrganizationService organizationService;
  private final UserService userService;
  private final CompanyInfoService companyInfoService;

  public UserOrganizationListDTO getUserOrganizationListDTO(String userId) {
    UserDTO loginUser = userService.getUser(userId);
    List<InterestOrganization> interestOrganizations = interestOrganizationRepository.findListByUserId(loginUser.getId()
      .describeConstable().orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다.")));

    List<OrganizationInfoDTO> organizationInfoDTOList = interestOrganizations.stream()
      .map(io -> OrganizationInfoDTO.toDTO(io, organizationService.getById(io.getOrganizationId())))
      .collect(Collectors.toList());

    return UserOrganizationListDTO.toDTO(loginUser, organizationInfoDTOList);
  }

  public List<InterestOrganizationDTO> getInterestOrganizationDTOList(String userId) {
    List<InterestOrganization> interestOrganizations = interestOrganizationRepository.findListByUserId(userId
      .describeConstable().orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다.")));
    return interestOrganizations.stream().map(InterestOrganizationDTO::toDTO).collect(Collectors.toList());
  }

  public String addInterestOrganization(UserDTO user, String organizationId) {

    Optional<InterestOrganization> check = interestOrganizationRepository.findByUserIdAndOrganizationId(user.getId(), organizationId);

    if(check.isPresent()) { throw new DuplicateException("이미 존재하는 기업입니다.");}

    InterestOrganization addInterestOrganization = InterestOrganization.builder()
      .userId(user.getId())
      .organizationId(organizationId)
      .checkTime(LocalDateTime.now())
      .build();

    interestOrganizationRepository.save(addInterestOrganization);

    return "추가 성공";
  }

  public String deleteInterestOrganization(UserDTO user, String id) {
    InterestOrganization interestOrganization = interestOrganizationRepository.findByUserIdAndOrganizationId(user.getId(), id)
      .orElseThrow(() -> new NotFoundException("없는 정보 입니다."));

    interestOrganizationRepository.delete(interestOrganization);
    return "삭제 성공";
  }

  public OrganizationWithInterestDTOPage getAllOrganizationsWithInterest(String userId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<OrganizationDTO> organizationDTOPage = organizationService.getOrganizationDTOPage(pageable);

    // user 의 관심 기업 list
    List<InterestOrganizationDTO> interestList = getInterestOrganizationDTOList(userId);
    Set<String> interestedOrganizationIds = interestList.stream()
      .map(InterestOrganizationDTO::getOrganizationId)
      .collect(Collectors.toSet());

    List<OrganizationWithInterestDTO> organizationWithInterestDTOList = organizationDTOPage.getContent().stream()
      .map(org -> OrganizationWithInterestDTO.builder()
        .organization(org)
        .isInterested(interestedOrganizationIds.contains(org.getId()))
        .build())
      .collect(Collectors.toList());

    return OrganizationWithInterestDTOPage.builder()
      .organizationWithInterestDTOList(organizationWithInterestDTOList)
      .hasMore(organizationDTOPage.hasNext())
      .build();
  }

  public List<InterestCompanyInfoDTO> getCompanyInfoInUser(String loginUser){
    List<InterestOrganizationDTO> interestOrganizations = interestOrganizationRepository.findListByUserId(loginUser
      .describeConstable().orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."))).stream().map(InterestOrganizationDTO::toDTO).toList();

    return interestOrganizations.stream()
      .map(io -> InterestCompanyInfoDTO.builder()
        .interestOrganization(io)
        .companyInfo(companyInfoService.getCompanyInfoById(io.getOrganizationId()))
        .build())
      .toList();
  }

}
