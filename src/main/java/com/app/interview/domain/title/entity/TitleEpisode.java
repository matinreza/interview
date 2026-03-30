package com.app.interview.domain.title.entity;

import com.app.interview.domain.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_TITLE_EPISODE")
public class TitleEpisode extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    private TitleBasics titleBasics;

    @ManyToOne
    @JoinColumn(name = "PARENT_TITLE_ID")
    private TitleBasics parentTitle;

    private Integer seasonNumber;

    private Integer episodeNumber;
}
