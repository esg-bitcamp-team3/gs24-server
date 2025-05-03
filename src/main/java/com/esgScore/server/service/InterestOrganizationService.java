package com.esgScore.server.service;

import com.esgScore.server.annotation.Login;
import com.esgScore.server.domain.User;
import com.esgScore.server.domain.dto.UserOrganizationListDTO;
import com.esgScore.server.repository.InterestOrganizationRepository;
import com.esgScore.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InterestOrganizationService {
  private final InterestOrganizationRepository interestOrganizationRepository;
  private final UserRepository userRepository;

  public UserOrganizationListDTO getUserOrganizationListDTO(@Login User loginUser) {
    User user = userRepository.findById(loginUser.getId()).orElseThrow();

  }
}
