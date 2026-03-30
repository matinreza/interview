package com.app.interview.domain.title.entity;

import com.app.interview.domain.common.base.BaseEntity;
import com.app.interview.domain.person.entity.Person;
import com.app.interview.domain.title.entity.enums.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TITLE_PRINCIPALS_CREW")
public class TitlePrincipalsCrew extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    private TitleBasics titleBasics;

    private Integer ordering;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category_")
    private Category category;

    private String job;

    private String characters;
}
