package com.app.interview.domain.person.entity;

import com.app.interview.domain.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_PERSON")
public class Person extends BaseEntity {
    private String fullName;
    private Integer birthYear;
    private Integer deathYear;
    private String profession;
    private String externalId;
    private String externalKnowIds;
}
