package com.app.interview.domain.person.entity;

import com.app.interview.domain.common.base.BaseEntity;
import com.app.interview.domain.title.entity.TitleBasics;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_PERSON_KNOWN_TITLE")
public class PersonKnownTitle extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    private TitleBasics titleBasics;

}
