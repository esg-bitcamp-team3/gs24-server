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
  public ResponseEntity<String> updateUser(@Login User loginUser, @RequestBody UserDTO userDTO) {
    String result = userService.updateUser(loginUser.getId(), userDTO);
    if(!result.equals("회원 정보 수정 성공")) {
      return ResponseEntity.badRequest().body(result);
    }
    return ResponseEntity.ok("회원 정보 수정 성공");
  }

  // 회원 정보 삭제
  @DeleteMapping
  public ResponseEntity<String> deleteUser(@Login User loginUser) {
    userService.deleteUser(loginUser.getId());
    return ResponseEntity.ok("회원 삭제");
  }

  // 회원 비밀번호 변경
  @PatchMapping(value = "/password")
  public ResponseEntity<String> updatePassword(@Login User loginUser, @RequestBody String password) {
    String result = userService.updatePassword(loginUser.getId(), password);
    if(!result.equals("비밀번호 수정 성공")) {
      return ResponseEntity.badRequest().body(result);
    }
    return ResponseEntity.ok("비밀번호 변경 성공");
  }


}
