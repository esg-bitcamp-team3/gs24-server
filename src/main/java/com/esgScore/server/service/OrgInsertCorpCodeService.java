package com.esgScore.server.service;

import com.esgScore.server.domain.Organization;
import com.esgScore.server.repository.main.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrgInsertCorpCodeService {
  private final OrganizationRepository organizationRepository;

  public void importCsvData (MultipartFile file) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
    reader.readLine();

    String line;
    while ((line = reader.readLine()) != null) {
      String[] data = line.split(",");

      String corp_code = "00" + data[0].trim();
      String corp_name = data[1].trim();
      String stock_code = data[3].trim();



      Optional<Organization> optionalOrg = organizationRepository.findByCompanyCode(stock_code);
      if(optionalOrg.isEmpty()) {
        continue;
      }
      Organization org = optionalOrg.get();
      org.setCorpCode(corp_code);
      organizationRepository.save(org);

    }
  }
}
