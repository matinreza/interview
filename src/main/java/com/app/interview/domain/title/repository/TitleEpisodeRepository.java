package com.app.interview.domain.title.repository;

import com.app.interview.domain.title.entity.TitleEpisode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleEpisodeRepository extends JpaRepository<TitleEpisode, Long> {
}
