package com.esgScore.server.service;

import com.esgScore.server.domain.User;
import com.esgScore.server.domain.dto.JoinDTO;
import com.esgScore.server.domain.dto.UserDTO;
import com.esgScore.server.repository.UserRepository;
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
  public String createUser(JoinDTO joinDTO) {

    if(userRepository.findByLoginId(joinDTO.getLoginId()).isPresent()) {
      return "이미 가입된 로그인 아이디입니다.";
    }

    User createUser = User.builder()
      .loginId(joinDTO.getLoginId())
      .password(joinDTO.getPassword())
      .email(joinDTO.getEmail())
      .name(joinDTO.getName())
//      .isAuthorized(joinDTO.getIsAuthorized())
      .build();

    createUser = createUser.hashPassword(passwordEncoder);
    log.info("Creating user: {}", createUser);

    userRepository.save(createUser);

    return "회원가입 성공";
  }

  public User login(String loginId, String password) {
    User user = userRepository.findByLoginId(loginId).orElse(null);

    if(user == null || !passwordEncoder.matches(password, user.getPassword())) {
      return null;
    }
    return user;
  }

  @Transactional
  public String updateUser(String id, UserDTO userDTO) {
    Optional<User> user = userRepository.findById(id);

    if(user.isEmpty()) {
      return "해당하는 회원이 존재하지 않습니다.";
    }

    user.get().setName(userDTO.getName());
    user.get().setEmail(userDTO.getEmail());

    log.info("Updating user: {}", user);

    return "회원 정보 수정 성공";
  }

  public void deleteUser(String id) { userRepository.deleteById(id); }

  public List<UserDTO> getUsers() {
    return userRepository.findAll().stream().map(User::toDTO).toList();
  }

  public UserDTO getUser(String id) {
    return userRepository.findById(id).map(User::toDTO).orElse(null);
  }

  public String updatePassword(String id, String password) {
    Optional<User> user = userRepository.findById(id);

    if(user.isEmpty()){
      return "해당하는 회원이 존재하지 않습니다.";
    }
    user.get().setPassword(passwordEncoder.encode(password));
    log.info("Updating password: {}", user);

    return "비밀번호 수정에 성공";
  }
}
