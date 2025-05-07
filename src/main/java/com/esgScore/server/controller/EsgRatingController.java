package com.esgScore.server.controller;

import com.esgScore.server.domain.dto.EsgRatingDTO;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.service.EsgRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ESG 등급 조회 요청을 처리하는 REST 컨트롤러
 */
@RestController
@RequestMapping("/api/esg-ratings")
@RequiredArgsConstructor
public class EsgRatingController {
    private final EsgRatingService esgRatingService;

    /**
     * 특정 조직 ID에 해당하는 모든 ESG 등급 정보를 반환하는 API
     * @param organizationId 조직의 고유 식별자
     * @return 해당 조직의 ESG 등급 정보를 담은 DTO 리스트를 ResponseEntity로 감싸 반환
     */
    @GetMapping("/{organizationId}")
    public ResponseEntity<List<EsgRatingDTO>> getAllEsgRatings(@PathVariable String organizationId)  {

        try {
            List<EsgRatingDTO> esgrating = esgRatingService.getEsgRatingList(organizationId);
            return ResponseEntity.ok(esgrating);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/code/{organizationCode}")
    public ResponseEntity<List<EsgRatingDTO>> getAllEsgRatingsByOrganizationCode(@PathVariable String organizationCode)  {

        try {
            List<EsgRatingDTO> esgrating = esgRatingService.getEsgRatingListByOrganizationCode(organizationCode);
            return ResponseEntity.ok(esgrating);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
