package com.esgScore.server.service;

import com.esgScore.server.domain.InterestCorporation;
import com.esgScore.server.domain.dto.corporation.CorpWithInterestDTO;
import com.esgScore.server.domain.dto.corporation.CorpWithInterestPage;
import com.esgScore.server.domain.dto.corporation.CorporationDTO;

import com.esgScore.server.domain.dto.interest.InterestCorporationDTO;
import com.esgScore.server.domain.dto.interest.InterestCorporationDetailDTO;
import com.esgScore.server.exceptions.DuplicateException;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.mapper.InterestCorporationMapper;
import com.esgScore.server.repository.main.InterestCorporationRepository;
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
public class InterestCorporationService {
  private final InterestCorporationRepository interestcorporationRepository;
  private final UserService userService;
  private final CompanyInfoService companyInfoService;
  private final CorporationService corporationService;

//  public UserCorporationListDTO getUsercorporationListDTO(String userId) {
//    UserDTO loginUser = userService.getUser(userId);
//    List<Interestcorporation> interestcorporations = interestcorporationRepository.findListByUserId(loginUser.getId()
//      .describeConstable().orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다.")));
//
//    List<corporationInfoDTO> corporationInfoDTOList = interestcorporations.stream()
//      .map(io -> corporationInfoDTO.toDTO(io, corporationService.getById(io.getcorporationId())))
//      .collect(Collectors.toList());
//
//    return UsercorporationListDTO.toDTO(loginUser, corporationInfoDTOList);
//  }
  public boolean existsByUserAndCorporation(String userId, String corpId) {
    Optional<InterestCorporation> interestCorporation = interestcorporationRepository.findByUserIdAndCorporationId(userId, corpId);
    return interestCorporation.isPresent();
  }

  public InterestCorporationDTO getByUserAndCorporation(String userId, String corpId) {
    Optional<InterestCorporation> interestCorporation = interestcorporationRepository.findByUserIdAndCorporationId(userId, corpId);
    return interestCorporation.map(InterestCorporationMapper::toDTO).orElse(null);
  }

  public InterestCorporationDetailDTO getById(String id) {
    InterestCorporation interestCorporation = interestcorporationRepository.findById(id).orElseThrow(() -> new NotFoundException("관심 기업을 찾을 수 없습니다."));
    CorporationDTO corporationDTO = corporationService.getById(interestCorporation.getCorporationId());

    return InterestCorporationMapper.toDetailDTO(interestCorporation, corporationDTO);
  }

  public String addInterestCorporation(String userId, String corporationId) {
    Optional<InterestCorporation> optionalInterestCorporation = interestcorporationRepository.findByUserIdAndCorporationId(userId, corporationId);
    if(optionalInterestCorporation.isPresent()){
      throw new DuplicateException("이미 관심 기업 입니다.");
    }
    InterestCorporation interestCorporation = InterestCorporation.builder()
      .userId(userId)
      .corporationId(corporationId)
      .checkTime(LocalDateTime.now())
      .build();
    interestcorporationRepository.save(interestCorporation);
    return "관심 기업 추가 성공";
  }

  public String deleteInterestCorporation(String userId, String corporationId) {
    InterestCorporation interestCorporation = interestcorporationRepository.findByUserIdAndCorporationId(userId, corporationId)
      .orElseThrow(() -> new NotFoundException("관심이 없는 기업 입니다."));
    interestcorporationRepository.delete(interestCorporation);
    return "관심이 없어 졌어요.";
  }

  public List<InterestCorporationDTO> getInterestcorporationDTOList(String userId) {
    List<InterestCorporation> interestCorporations = interestcorporationRepository.findListByUserId(userId
      .describeConstable().orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다.")));
    return interestCorporations.stream().map(InterestCorporationDTO::toDTO).collect(Collectors.toList());
  }

//
//  public List<InterestCompanyInfoDTO> getCompanyInfoInUser(String loginUser){
//    List<InterestcorporationDTO> interestcorporations = interestcorporationRepository.findListByUserId(loginUser
//      .describeConstable().orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."))).stream().map(InterestcorporationDTO::toDTO).toList();
//
//    return interestcorporations.stream()
//      .map(io -> InterestCompanyInfoDTO.builder()
//        .interestcorporation(io)
//        .companyInfo(companyInfoService.getCompanyInfoById(io.getcorporationId()))
//        .build())
//      .toList();
//  }

  public CorpWithInterestPage getAllCorpsWithInterest(String userId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<CorporationDTO> corporationDTOS = corporationService.getCorporationDTOPage(pageable);

    // user 의 관심 기업 list
    List<InterestCorporationDTO> interestList = getInterestcorporationDTOList(userId);
    Set<String> interestedCorporationIds = interestList.stream()
      .map(InterestCorporationDTO::getCorporationId)
      .collect(Collectors.toSet());

    List<CorpWithInterestDTO> corporationWithInterestDTOList = corporationDTOS.getContent().stream()
      .map(org -> CorpWithInterestDTO.builder()
        .corporation(org)
        .interested(interestedCorporationIds.contains(org.getId()))
        .build())
      .collect(Collectors.toList());

    return CorpWithInterestPage.builder()
      .corpWithInterestDTOList(corporationWithInterestDTOList)
      .hasMore(corporationDTOS.hasNext())
      .build();
  }

}
