package com.esgScore.server.controller;


import com.esgScore.server.annotation.Login;
import com.esgScore.server.domain.User;
import com.esgScore.server.domain.dto.UserDTO;
import com.esgScore.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
  private final UserService userService;

  // 회원 조회
  @GetMapping
  public ResponseEntity<UserDTO> getUser(@Login User loginUser) {
    UserDTO userDTO = userService.getUser(loginUser.getId());
    return ResponseEntity.ok(userDTO);
  }

  // 회원 정보 수정
  @PatchMapping
  public ResponseEntity<String> updateUser(@Login User loginUser, @ModelAttribute UserDTO userDTO) {
    String result = userService.updateUser(loginUser.getId(), loginUser.toDTO());
    return ResponseEntity.ok("회원 정보 수정 성공");
  }

  // 회원 정보 삭제



}
