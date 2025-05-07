package com.esgScore.server.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "esg_ratings")
public class EsgRating {
    @Id
    private String id;

    private String organizationId;

    private Integer no;

    private String esgGrade;

    private String environment;

    private String social;

    private String governance;

    private Integer year;

    @DBRef
    public Organization organization;
}