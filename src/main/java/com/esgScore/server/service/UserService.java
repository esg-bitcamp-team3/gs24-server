package com.esgScore.server.service;

import com.esgScore.server.domain.User;
import com.esgScore.server.domain.dto.LoginDTO;
import com.esgScore.server.domain.dto.SignupDTO;
import com.esgScore.server.domain.dto.UserDTO;
import com.esgScore.server.exceptions.DuplicateException;
import com.esgScore.server.exceptions.InvalidRequestException;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.repository.main.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public String createUser(SignupDTO signupDTO) {
    Optional<User> user = userRepository.findByUsername(signupDTO.getUsername());

    if(user.isPresent()) {
      throw new DuplicateException("존재하는 아이디 입니다.");
    }

    User createUser = User.builder()
      .username(signupDTO.getUsername())
      .password(passwordEncoder.encode(signupDTO.getPassword()))
      .email(signupDTO.getEmail())
      .name(signupDTO.getName())
      .phone(signupDTO.getPhone())
      .build();

    userRepository.save(createUser);

    return "회원가입 성공";
  }

  public UserDTO login(LoginDTO loginDTO) {
    User user = userRepository.findByUsername(loginDTO.getUsername()).orElseThrow(() -> new NotFoundException("없는 아이디 입니다."));

    if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
      throw new NotFoundException("비밀번호가 틀려요");
    }
    return user.toDTO();
  }

  @Transactional
  public String updateUser(String id, UserDTO userDTO) {
    User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("없는 사용자입니다."));

    if (userDTO.getName() != null) {
      user.setName(userDTO.getName());
    }
    if (userDTO.getEmail() != null) {
      user.setEmail(userDTO.getEmail());
    }
    if (userDTO.getPhone() != null) {
      user.setPhone(userDTO.getPhone());
    }

    userRepository.save(user);

    return "회원 정보 수정 성공";
  }

  public void deleteUser(String id) { userRepository.deleteById(id); }

  public List<UserDTO> getUsers() {
    return userRepository.findAll().stream().map(User::toDTO).toList();
  }

  public UserDTO getUser(String id) {
    return userRepository.findById(id).map(User::toDTO).orElseThrow(() -> new NotFoundException("없는 사용자입니다."));
  }

  public Boolean checkPassword(String id, String password) {
    User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("없는 유저입니다"));
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new InvalidRequestException("현재 비밀번호가 일치하지 않습니다.", List.of("currentPassword"));
    }
    return true;
  }

  @Transactional
  public String updatePassword(String id, String password) {
    User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("없는 사용자입니다."));

    user.setPassword(passwordEncoder.encode(password));
    userRepository.save(user);

    return "비밀번호 수정 성공";
  }

  public Boolean checkLogin(UserDTO user) {
    return userRepository.findById(user.getId()).isPresent();
  }
}
