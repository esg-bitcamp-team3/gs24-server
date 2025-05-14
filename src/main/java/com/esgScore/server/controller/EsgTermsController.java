package com.esgScore.server.controller;

import com.esgScore.server.domain.dto.EsgTermDTO;
import com.esgScore.server.service.EsgTermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/terms")
@RequiredArgsConstructor
public class EsgTermsController {
  private final EsgTermsService esgTermsService;

  @PostMapping
  public ResponseEntity<String> uploadJson(@RequestParam("file") MultipartFile file) {
    try {
      esgTermsService.importEsgTermsData(file);
      return ResponseEntity.ok("JSON 파일이 성공적으로 업로드되었습니다.");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("JSON 업로드 실패:" + e.getMessage());
    }
  }

  @GetMapping
  public ResponseEntity<List<EsgTermDTO>> getEsgTerms() {
    try {
      return ResponseEntity.status(200).body(esgTermsService.getAllEsgTerms());
    } catch (Exception e) {
      throw new InternalError(e);
    }
  }
}
