package com.esgScore.server.controller;

import com.esgScore.server.domain.dto.EsgRatingDTO;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.service.EsgRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/esg-ratings")
@RequiredArgsConstructor
public class EsgRatingController {
    private final EsgRatingService esgRatingService;

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
}
