package com.app.interview.domain.title.entity;

import com.app.interview.domain.common.base.BaseEntity;
import com.app.interview.domain.title.entity.enums.Language;
import com.app.interview.domain.title.entity.enums.Region;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_TITLE_AKAS")
public class TitleAkas extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    private TitleBasics titleBasics;

    private Integer ordering;
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "REGION_")
    private Region region;

    @Enumerated(EnumType.STRING)
    @Column(name = "LANGUAGE_")
    private Language language;
    private String attribute;
    private boolean original;
}
