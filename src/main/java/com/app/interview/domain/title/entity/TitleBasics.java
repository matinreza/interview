package com.app.interview.domain.title.entity;

import com.app.interview.domain.common.base.BaseEntity;
import com.app.interview.domain.common.util.GenreSetConverter;
import com.app.interview.domain.title.entity.enums.Genre;
import com.app.interview.domain.title.entity.enums.TitleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "TBL_TITLE_BASICS")
public class TitleBasics extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "TITLE_TYPE")
    private TitleType titleType;

    @Convert(converter = GenreSetConverter.class)
    @Column(name = "GENRES")
    private Set<Genre> genreSet;

    @Column(name = "PRIMARY_TITLE", columnDefinition = "TEXT")
    private String primaryTitle;

    @Column(name = "ORIGINAL_TITLE", columnDefinition = "TEXT")
    private String originalTitle;
    private boolean forAdult;
    private Integer startYear;
    private Integer endYear;
    private Integer runtimeMinutes;
    private String externalId;
}
