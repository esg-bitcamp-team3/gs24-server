package com.esgScore.server.controller;

import com.esgScore.server.domain.EsgRating;
import com.esgScore.server.repository.EsgRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/esg")
@RequiredArgsConstructor
public class EsgRatingController {
    private final EsgRatingRepository esgRatingRepository;

    @GetMapping("/all")
    public List<EsgRating> getAllRatings() {
        return esgRatingRepository.findAll();
    }

    @GetMapping("/{companyName}")
    public EsgRating getRatingByCompanyName(@PathVariable String companyName) {
        return esgRatingRepository.findByCompanyName(companyName);
    }
}
