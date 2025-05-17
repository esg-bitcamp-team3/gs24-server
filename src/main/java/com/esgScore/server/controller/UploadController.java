package com.esgScore.server.controller;

import com.esgScore.server.service.CorporationCsvImportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
@Slf4j
public class UploadController {

    private final CorporationCsvImportService corporationCsvImportService;

    @PostMapping("/corporation")
    public ResponseEntity<String> uploadCorporationCSV(@RequestParam("file") MultipartFile file) {
        try {
            corporationCsvImportService.saveCorporationData(file);
            return ResponseEntity.ok("CSV 파일이 성공적으로 업로드되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("CSV 업로드 실패:" + e.getMessage());
        }
    }

    @PostMapping("/esg")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
        try {
            corporationCsvImportService.saveESGRating(file);
            return ResponseEntity.ok("CSV 파일이 성공적으로 업로드되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("CSV 업로드 실패:" + e.getMessage());
        }
    }
}
