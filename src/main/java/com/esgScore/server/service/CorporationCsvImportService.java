package com.esgScore.server.service;

import com.esgScore.server.domain.Corporation;
import com.esgScore.server.domain.EsgRating;
import com.esgScore.server.domain.Organization;
import com.esgScore.server.domain.dto.EsgRatingDTO;
import com.esgScore.server.domain.dto.corporation.CorporationDetailDTO;
import com.esgScore.server.mapper.CorporationMapper;
import com.esgScore.server.mapper.EsgRatingMapper;
import com.esgScore.server.repository.main.CorporationRepository;
import com.esgScore.server.repository.main.EsgRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CorporationCsvImportService {

    private final CorporationRepository corporationRepository;
    private final EsgRatingRepository esgRatingRepository;

    public void saveCorporationData (MultipartFile file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                if (tokens.length < 5) continue;

                String corpCode = String.format("%08d", Integer.parseInt(tokens[0].trim()));
                String corpName = tokens[1].trim();
                String corpEngName = tokens[2].trim().replaceAll("^\"|\"$", "");
                String stockCode = String.format("%06d", Integer.parseInt(tokens[3].trim()));

                CorporationDetailDTO corpDTO = CorporationDetailDTO.builder()
                                                                .corpCode(corpCode)
                                                                .corpName(corpName)
                                                                .corpEngName(corpEngName)
                                                                .stockCode(stockCode)
                                                                .build();

                Corporation corp = CorporationMapper.fromDTO(corpDTO);

                corporationRepository.save(corp);
            }
        }
    }

    public void saveESGRating (MultipartFile file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            reader.readLine();


            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",", -1);

                int no = Integer.parseInt(tokens[0].trim());
                String name = tokens[1].trim();
                String code = tokens[2].trim();
                String esgGrade = tokens[3].trim();
                String environment = tokens[4].trim();
                String social = tokens[5].trim();
                String governance = tokens[6].trim();
                int year = Integer.parseInt(tokens[7].trim());

                Corporation corporation = corporationRepository
                        .findByStockCode(code)
                        .orElseGet(() -> {
                            CorporationDetailDTO corpDTO = CorporationDetailDTO.builder()
                                    .corpName(name)
                                    .stockCode(code)
                                    .build();

                            Corporation corp = CorporationMapper.fromDTO(corpDTO);

                            return corporationRepository.save(corp);
                        });

                EsgRatingDTO esgRatingDTO = EsgRatingDTO.builder()
                        .corporationId(corporation.getId())
                        .esgGrade(esgGrade)
                        .environment(environment)
                        .social(social)
                        .governance(governance)
                        .year(year)
                        .build();

                EsgRating esgRating = EsgRatingMapper.fromDTO(esgRatingDTO);
                esgRatingRepository.save(esgRating);
            }
        }
    }
}
