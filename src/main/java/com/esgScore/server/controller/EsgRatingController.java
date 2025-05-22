package com.esgScore.server.controller;

import com.esgScore.server.domain.dto.EsgRatingDTO;
import com.esgScore.server.domain.dto.CorporationEsgRatingListDTO;
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

    @GetMapping("/{corporationId}")
    public ResponseEntity<CorporationEsgRatingListDTO> getAllEsgRatings(@PathVariable String corporationId)  {

        try {
            CorporationEsgRatingListDTO corporationEsgRatingListDTO = esgRatingService.getEsgRatingListByCorporationId(corporationId);

            return ResponseEntity.ok(corporationEsgRatingListDTO);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/code/{corporationCode}")
    public ResponseEntity<List<EsgRatingDTO>> getAllEsgRatingsByCorporationCode(@PathVariable String corporationCode)  {

        try {
            List<EsgRatingDTO> esgRating = esgRatingService.getEsgRatingListByCorporationCode(corporationCode);
            return ResponseEntity.ok(esgRating);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
