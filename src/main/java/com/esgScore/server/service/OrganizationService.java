package com.esgScore.server.service;

import com.esgScore.server.domain.Organization;
import com.esgScore.server.domain.dto.OrganizationDTO;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.mapper.OrganizationMapper;
import com.esgScore.server.repository.main.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrganizationService {
  private final OrganizationRepository organizationRepository;

  public List<OrganizationDTO> getAll() {
    List<Organization> organizations = organizationRepository.findAll();

      return organizations.stream()
              .map(OrganizationMapper::toDTO)
              .collect(Collectors.toList());
  }

  public OrganizationDTO getById(String id) {
    Organization organization = organizationRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Organization not found"));

    return OrganizationMapper.toDTO(organization);
  }

  @Transactional
  public OrganizationDTO create(OrganizationDTO organizationDTO) {
     Organization organization = OrganizationMapper.fromDTO(organizationDTO);
     Organization savedOrganization = organizationRepository.save(organization);

     return OrganizationMapper.toDTO(savedOrganization);
  }


  @Transactional
  public OrganizationDTO update(String id, OrganizationDTO organizationDTO) {
    Organization organization = organizationRepository.findById(id).orElseThrow(() -> new NotFoundException("Organization not found"));


    if (organizationDTO.getCompanyName() != null) {
      organization.setCompanyName(organizationDTO.getCompanyName());
    }

    Organization updatedOrganization = organizationRepository.save(organization);

    return OrganizationMapper.toDTO(updatedOrganization);
  }

  public void delete(String id) {
    Organization organization = organizationRepository.findById(id).orElseThrow(() -> new NotFoundException("Organization not found"));
    organizationRepository.delete(organization);
  }

  public Map<String, String> getNamesByIds(List<String> organizationIds) {
    List<Organization> organizations = organizationRepository.findByIdIn(organizationIds);

    return organizations.stream()
      .collect(Collectors.toMap(
        Organization::getId,
        Organization::getCompanyName
      ));
  }

}
