package com.esgScore.server.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "esg_ratings")
public class EsgRating {
    @Id
    private String id;

    @Field("NO")
    private Integer no;

    @Field("기업명")
    private String companyName;

    @Field("기업코드")
    private String companyCode;

    @Field("ESG등급")
    private String esgGrade;

    @Field("환경")
    private String environment;

    @Field("사회")
    private String social;

    @Field("지배구조")
    private String governance;

    @Field("평가연도")
    private Integer year;
}