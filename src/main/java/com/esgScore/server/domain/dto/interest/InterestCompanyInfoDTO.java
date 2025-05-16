package com.esgScore.server.domain.dto.interest;

import com.esgScore.server.domain.dto.CompanyInfoDTO;
import com.esgScore.server.domain.dto.InterestOrganizationDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InterestCompanyInfoDTO {
  private InterestOrganizationDTO interestOrganization;
  private CompanyInfoDTO companyInfo;
}
