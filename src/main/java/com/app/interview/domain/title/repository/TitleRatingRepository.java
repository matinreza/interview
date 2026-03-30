package com.app.interview.domain.title.repository;

import com.app.interview.domain.title.entity.TitleRating;
import com.app.interview.domain.title.entity.enums.Genre;
import com.app.interview.interfaces.rest.dto.TopTitleRatingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitleRatingRepository extends JpaRepository<TitleRating, Long> {
    @Query("""
                select new com.app.interview.interfaces.rest.dto.TopTitleRatingDto(
                                        t.id,
                                        t.startYear,
                                        max(e.averageRating)
                                    )
                from TitleRating e
                inner join e.titleBasics t
                where concat(',', t.genreSet, ',') like concat('%,', :genre, ',%')
                group by t.id, t.startYear
            """)
    List<TopTitleRatingDto> findBestTitleOnEachYearBaseOnGenre(@Param("genre") String genre);
}
