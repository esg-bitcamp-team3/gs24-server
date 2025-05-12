package com.esgScore.server.controller;

import com.esgScore.server.domain.dto.SignupDTO;
import com.esgScore.server.domain.dto.LoginDTO;
import com.esgScore.server.domain.dto.UserDTO;
import com.esgScore.server.exceptions.AuthenticationException;
import com.esgScore.server.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class LoginController {
  private final UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<String> signUp(@Validated @RequestBody SignupDTO signupDTO) {

    String message = userService.createUser(signupDTO);

    return ResponseEntity.status(HttpStatus.CREATED).body(message);
  }

  @PostMapping("/login")
  public ResponseEntity<String> logIn(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {

    UserDTO user = userService.login(loginDTO);

    HttpSession session = request.getSession();
    session.setAttribute("user", user);

    UserDTO sessionUser = (UserDTO) session.getAttribute("user");
    log.info("Login user: {}", sessionUser);

    // 200 ok
    return ResponseEntity.ok("로그인 성공");
  }

  @PostMapping("/logout")
  public ResponseEntity<String> logout(HttpServletRequest request) {
    HttpSession session = request.getSession();
    session.invalidate();

    return ResponseEntity.ok("로그아웃 되었습니다");
  }

  @GetMapping("/session")
  public ResponseEntity<Boolean> session(HttpServletRequest request) {
    HttpSession session = request.getSession();

    UserDTO user = (UserDTO) session.getAttribute("user");

    log.info("Login user: {}", user);
    if(user == null){
      throw new AuthenticationException("로그인이 필요합니다.") ;
    }

    return ResponseEntity.ok(true);
  }
}
