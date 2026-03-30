package com.app.interview.domain.title.entity;

import com.app.interview.domain.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TITLE_RATING")
public class TitleRating extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "ID")
    private TitleBasics titleBasics;
    private Double averageRating;
    private Integer numVotes;
}
