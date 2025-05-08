package com.esgScore.server.controller;


import com.esgScore.server.annotation.Login;
import com.esgScore.server.domain.dto.UserDTO;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
  private final UserService userService;

  // 회원 조회
  @GetMapping
  public ResponseEntity<UserDTO> getUser(@Login UserDTO loginUser) {
    UserDTO userDTO = userService.getUser(loginUser.getUsername());
    return ResponseEntity.ok(userDTO);
  }

  // 회원 정보 수정
  @PatchMapping
  public ResponseEntity<String> updateUser(@Login UserDTO loginUser, @RequestBody UserDTO userDTO) {
    try{
      return ResponseEntity.ok(userService.updateUser(loginUser.getUsername(), userDTO));
    } catch ( NotFoundException e){
      return ResponseEntity.notFound().build();
    }
  }

  // 회원 정보 삭제, 탈퇴
  @DeleteMapping
  public ResponseEntity<String> deleteUser(@Login UserDTO loginUser) {
    userService.deleteUser(loginUser.getUsername());
    return ResponseEntity.ok("회원 삭제");
  }

  // 회원 비밀번호 변경
  @PatchMapping(value = "/password")
  public ResponseEntity<String> updatePassword(@Login UserDTO loginUser, @RequestBody String password) {
    try {
      return ResponseEntity.ok(userService.updatePassword(loginUser.getUsername(), password));
    }catch (NotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }


}
