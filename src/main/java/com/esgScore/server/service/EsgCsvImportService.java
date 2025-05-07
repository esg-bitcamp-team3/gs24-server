package com.esgScore.server.service;

import com.esgScore.server.domain.EsgRating;
import com.esgScore.server.domain.Organization;
import com.esgScore.server.repository.EsgCsvImportRepository;
import com.esgScore.server.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EsgCsvImportService {

//    private final EsgCsvImportRepository esgCsvImportRepository;
    private final OrganizationRepository organizationRepository;

    public void importCsvData (MultipartFile file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
        reader.readLine();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");

            int no = Integer.parseInt(data[0]);
            String name = data[1];
            String code = data[2];
            String esgGrade = data[3];
            String environment = data[4];
            String social = data[5];
            String governance = data[6];
            int year = Integer.parseInt(data[7].trim());

            Organization organization = organizationRepository
                    .findById(String.valueOf(no))
                    .orElseGet(() -> organizationRepository.save(new Organization(null, name, code)));

            Organization org = new Organization();
            org.setCompanyCode(code);
            org.setCompanyName(name);

            EsgRating rating  = new EsgRating();
            rating.setNo(no);
            rating.setYear(year);
            rating.setEnvironment(environment);
            rating.setSocial(social);
            rating.setGovernance(governance);
            rating.setEsgGrade(esgGrade);

            EsgCsvImportRepository repository = new EsgCsvImportRepository();

            repository.save(rating);
        }
    }
}
