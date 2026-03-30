package com.app.interview.domain.title.repository;

import com.app.interview.domain.title.entity.TitleBasics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitleBasicsRepository extends JpaRepository<TitleBasics, Long> {
    List<TitleBasics> findTitleBasicsByExternalIdIn(List<String> externalIds);

    TitleBasics findTitleBasicsByExternalId(String externalId);

    @Query("""
                  select res2.titleId
                    from (
                  select res.titleId as titleId,
                         sum(res.writer) as writer,
                         sum(res.director) as director
                    from (
                  select e.person.id as writer,
                         p.person.id as director,
                         e.titleBasics.id as titleId
                    from TitlePrincipalsCrew e
              inner join e.person n
              inner join TitlePrincipalsCrew p
                      on e.titleBasics.id = p.titleBasics.id
                   where e.category = 'writer'
                     and p.category = 'director'
                     and n.deathYear is null)res
              group by res.titleId)res2
            where res2.director = res2.writer
            """)
    List<Long> findAllTitleWithConditionOfWriterAndDirectorIsSameAndAlive();

    @Query("""
             select res.id
               from (select e.id as id
             from TitlePrincipalsCrew e
            where e.category = 'actor'
              and e.person.id in(?1)
            group by e.id)res
            group by res.id
            having count(*) = 2
            """)
    List<Long> findAllTitleWithConditionOfPlayingBothActors(List<Long> actoreIdList);

}
