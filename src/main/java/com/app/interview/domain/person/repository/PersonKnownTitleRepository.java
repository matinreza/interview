package com.app.interview.domain.person.repository;

import com.app.interview.domain.person.entity.PersonKnownTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonKnownTitleRepository extends JpaRepository<PersonKnownTitle, Long> {
}
