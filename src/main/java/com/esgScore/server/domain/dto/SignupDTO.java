package com.esgScore.server.domain.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;

@Data
public class SignupDTO {
  @NotEmpty(message = "로그인 아이디를 입력해주세요.")
  private String loginId;

  @NotEmpty(message = "비밀번호를 입력해주세요.")
  private String password;

  @NotEmpty(message = "이름을 입력해주세요.")
  private String name;

  @NotEmpty(message = "이메일을 입력해주세요.")
  @Email(message = "이메일 형식이 아닙니다.")
  private String email;

  @NotEmpty(message = "전화 번호를 입력해주세요.")
  private String phone;

//  private Boolean isAuthorized;
}
