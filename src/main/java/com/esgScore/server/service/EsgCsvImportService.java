package com.esgScore.server.service;

import com.esgScore.server.domain.EsgRating;
import com.esgScore.server.domain.Organization;
import com.esgScore.server.repository.main.EsgRatingRepository;
import com.esgScore.server.repository.main.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EsgCsvImportService {

    private final OrganizationRepository organizationRepository;
    private final EsgRatingRepository esgRatingRepository;

    public void importCsvData (MultipartFile file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
//        esgRatingRepository.deleteAll();
//        reader.readLine();
//
//        String line;
//        while ((line = reader.readLine()) != null) {
//            String[] data = line.split(",");
//
//            int no = Integer.parseInt(data[0].trim());
//            String name = data[1].trim();
//            String code = data[2].trim();
//            String esgGrade = data[3].trim();
//            String environment = data[4].trim();
//            String social = data[5].trim();
//            String governance = data[6].trim();
//            int year = Integer.parseInt(data[7].trim());
//
//            Organization organization = organizationRepository
//                    .findByCompanyCode(code)
//                    .orElseGet(() -> {
//                        Organization org = new Organization();
//                        org.setCompanyName(name);
//                        org.setCompanyCode(code);
//                        return organizationRepository.save(org);
//                    });
//
//            EsgRating rating  = new EsgRating();
//            rating.setOrganizationId(organization.getId());
//            rating.setNo(no);
//            rating.setYear(year);
//            rating.setEnvironment(environment);
//            rating.setSocial(social);
//            rating.setGovernance(governance);
//            rating.setEsgGrade(esgGrade);

//            esgRatingRepository.save(rating);
//            organization.getEsgRatings().add(rating);

//            organizationRepository.save(organization);
//            esgRatingRepository.save(rating);
//        }
    }
}
