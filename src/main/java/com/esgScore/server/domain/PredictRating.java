package com.esgScore.server.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Builder
@Document(collection = "predictRatings")
public class PredictRating {
    @Id
    private String id;

    @Field("company_name")
    private String companyName;
    private String date;
    @Field("e_score")
    private Integer eScore;
    @Field("s_score")
    private Integer sScore;
    @Field("g_score")
    private Integer gScore;
}