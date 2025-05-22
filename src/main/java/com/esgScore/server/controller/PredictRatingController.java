package com.esgScore.server.controller;

import com.esgScore.server.domain.dto.PredictRatingDTO;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.service.PredictRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ESG 등급 조회 요청을 처리하는 REST 컨트롤러
 */
@RestController
@RequestMapping("/api/predict-esg-ratings")
@RequiredArgsConstructor
public class PredictRatingController {
    private final PredictRatingService predictRatingService;

    @GetMapping("")
    public ResponseEntity<List<PredictRatingDTO>> getAllPredictRatings() {
        List<PredictRatingDTO> predictRatingDTOS = predictRatingService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(predictRatingDTOS);
    }

    @GetMapping("/name/{companyName}")
    public ResponseEntity<PredictRatingDTO> getPredictEsgRatingByCompanyName(@PathVariable String companyName)  {
        try {
            PredictRatingDTO esgRating = predictRatingService.getPredictRating(companyName);
            return ResponseEntity.ok(esgRating);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
