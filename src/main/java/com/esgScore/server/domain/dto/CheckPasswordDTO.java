package com.esgScore.server.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckPasswordDTO {
    private String password;
}
