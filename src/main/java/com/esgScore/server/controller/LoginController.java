package com.esgScore.server.controller;

import com.esgScore.server.domain.User;
import com.esgScore.server.domain.dto.JoinDTO;
import com.esgScore.server.domain.dto.LoginDTO;
import com.esgScore.server.repository.UserRepository;
import com.esgScore.server.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class LoginController {
  private final UserService userService;
  private final UserRepository userRepository;

  @GetMapping("/test")
  public ResponseEntity<String> test() {
    return ResponseEntity.ok("test");
  }

  @PostMapping("/signup")
  public ResponseEntity<String> signUp(@Validated @RequestBody JoinDTO joinDTO) {

    String message = userService.createUser(joinDTO);

    if(message.equals("이미 가입된 로그인 아이디입니다.")){
      return ResponseEntity.status(409).body(message);
    }

    return ResponseEntity.created(null).body(message);
  }

  @PostMapping("/login")
  public ResponseEntity<String> logIn(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {

    User user = userService.login(loginDTO.getLoginId(), loginDTO.getPassword());

    // 로그인 실패
    if(user == null){
      return ResponseEntity.status(401).build();
    }

    HttpSession session = request.getSession();
    session.setAttribute("user", user);

    // 200 ok
    return ResponseEntity.ok("로그인 성공");
  }

  @PostMapping("logout")
  public ResponseEntity<String> logout(HttpServletRequest request) {
    HttpSession session = request.getSession();
    session.invalidate();

    return ResponseEntity.ok("로그아웃 되었습니다");
  }

  @GetMapping("/session")
  public ResponseEntity<Void> session(HttpServletRequest request) {
    HttpSession session = request.getSession();

    User user = (User) session.getAttribute("user");
    if(user == null || userRepository.findById(user.getId()).isEmpty()){
      return ResponseEntity.status(401).build();
    }

    return ResponseEntity.ok().build();
  }
}
