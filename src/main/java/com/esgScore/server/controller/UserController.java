package com.esgScore.server.controller;


import com.esgScore.server.annotation.Login;
import com.esgScore.server.domain.User;
import com.esgScore.server.domain.dto.CheckPasswordDTO;
import com.esgScore.server.domain.dto.SignupDTO;
import com.esgScore.server.domain.dto.UserDTO;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.repository.main.UserRepository;
import com.esgScore.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  // 회원 조회
  @GetMapping("/mypage")
  public ResponseEntity<UserDTO> getUser(@Login UserDTO loginUser) {
    UserDTO userDTO = userService.getUser(loginUser.getId());
    return ResponseEntity.ok(userDTO);
  }

  // 회원 정보 수정
  @PatchMapping
  public ResponseEntity<String> updateUser(@Login UserDTO loginUser, @RequestBody UserDTO userDTO) {
    try {
      log.info(userDTO.toString());
      return ResponseEntity.ok(userService.updateUser(loginUser.getId(), userDTO));
    } catch (NotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  // 회원 정보 삭제, 탈퇴
  @DeleteMapping
  public ResponseEntity<String> deleteUser(@Login UserDTO loginUser) {
    userService.deleteUser(loginUser.getUsername());
    return ResponseEntity.ok("회원 삭제");
  }

  // 비밀번호 변경 시 기존 비밀번호와 맞는지 확인
  @PostMapping("/check-password")
  public ResponseEntity<Boolean> getCHeckPassword(@Login UserDTO loginUser, @RequestBody CheckPasswordDTO password) {
    log.info(password.toString());
    userService.checkPassword(loginUser.getId(), password.getPassword());
    return ResponseEntity.status(HttpStatus.OK).body(true);
  }

  // 회원 비밀번호 변경
  @PatchMapping("/password")
  public ResponseEntity<String> updatePassword(@Login UserDTO loginUser, @RequestBody String password) {
    try {
      return ResponseEntity.ok(userService.updatePassword(loginUser.getId(), password));
    } catch (NotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/check")
  public ResponseEntity<Boolean> checkLogin(@Login UserDTO loginUser) {
    if(userService.checkLogin(loginUser)) return ResponseEntity.ok(true);
    return ResponseEntity.ok(false);
  }
}
