package com.app.interview.domain.title.repository;

import com.app.interview.domain.title.entity.TitlePrincipalsCrew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitlePrincipalsCrewRepository extends JpaRepository<TitlePrincipalsCrew, Long> {
}
