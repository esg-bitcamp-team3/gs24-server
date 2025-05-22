package com.esgScore.server.service;

import com.esgScore.server.domain.Corporation;
import com.esgScore.server.domain.dto.corporation.CorporationDTO;
import com.esgScore.server.domain.dto.corporation.CorporationDetailDTO;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.mapper.CorporationMapper;
import com.esgScore.server.repository.main.CorporationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CorporationService {
  private final CorporationRepository corporationRepository;

  public List<CorporationDTO> getAll() {
    List<Corporation> corporations = corporationRepository.findAll();

      return corporations.stream()
              .map(CorporationMapper::toDTO)
              .collect(Collectors.toList());
  }

  public CorporationDTO getById(String id) {
    Corporation corporation = corporationRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Corporation not found"));

    return CorporationMapper.toDTO(corporation);
  }

  public CorporationDetailDTO getDetailsById(String id) {
    Corporation corporation = corporationRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Corporation not found"));

    return CorporationMapper.toDetailDTO(corporation);
  }

  public CorporationDTO getByCode(String code) {
    Corporation corporation = corporationRepository.findByCorpCode(code)
            .orElseThrow(() -> new NotFoundException("Corporation not found"));

    return CorporationMapper.toDTO(corporation);
  }

  public CorporationDTO getByStockCode(String stockCode) {
    Corporation corporation = corporationRepository.findByStockCode(stockCode)
            .orElseThrow(() -> new NotFoundException("Corporation not found"));

    return CorporationMapper.toDTO(corporation);
  }

  @Transactional
  public CorporationDetailDTO create(CorporationDetailDTO corporationDTO) {
     Corporation corporation = CorporationMapper.fromDTO(corporationDTO);
     Corporation savedCorporation = corporationRepository.save(corporation);

     return CorporationMapper.toDetailDTO(savedCorporation);
  }


  @Transactional
  public CorporationDetailDTO update(String id, CorporationDetailDTO corporationDTO) {
    Corporation corporation = corporationRepository.findById(id).orElseThrow(() -> new NotFoundException("Corporation not found"));

    Corporation updatedCorporation = corporationRepository.save(corporation);

    return CorporationMapper.toDetailDTO(updatedCorporation);
  }

  public void delete(String id) {
    Corporation corporation = corporationRepository.findById(id).orElseThrow(() -> new NotFoundException("Corporation not found"));
    corporationRepository.delete(corporation);
  }


  public Page<CorporationDTO> getCorporationDTOPage(Pageable pageable) {
    return corporationRepository.findAll(pageable).map(CorporationMapper::toDTO);
  }

}
