package com.app.interview.domain.title.service;

import com.app.interview.domain.title.entity.TitleBasics;
import com.app.interview.domain.title.entity.TitleEpisode;
import com.app.interview.domain.title.entity.TitlePrincipalsCrew;
import com.app.interview.domain.title.entity.TitleRating;
import com.app.interview.domain.title.repository.TitleBasicsRepository;
import com.app.interview.domain.title.repository.TitleEpisodeRepository;
import com.app.interview.domain.title.repository.TitlePrincipalsCrewRepository;
import com.app.interview.domain.title.repository.TitleRatingRepository;
import com.app.interview.interfaces.rest.dto.TopTitleRatingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TitleService {
    private final TitleBasicsRepository basicsRepository;
    private final TitleEpisodeRepository episodeRepository;
    private final TitlePrincipalsCrewRepository principalsRepository;
    private final TitleRatingRepository ratingRepository;

    @Transactional
    public void saveTitleBasics(TitleBasics titleBasics) {
        basicsRepository.save(titleBasics);
    }

    @Transactional
    public void saveTitleRating(TitleRating titleRating) {
        ratingRepository.save(titleRating);
    }

    @Transactional
    public void saveTitlePrincipals(TitlePrincipalsCrew titlePrincipalsCrew) {
        principalsRepository.save(titlePrincipalsCrew);
    }

    @Transactional
    public void saveTitleEpisode(TitleEpisode titleEpisode) {
        episodeRepository.save(titleEpisode);
    }

    public List<TitleBasics> findAllByExternalIdList(List<String> externalIdList) {
        return basicsRepository.findTitleBasicsByExternalIdIn(externalIdList);
    }

    public TitleBasics findByExternalId(String externalId) {
        return basicsRepository.findTitleBasicsByExternalId(externalId);
    }

    public List<Long> findAllTitleWithConditionOfWriterAndDirectorIsSameAndAlive() {
        return basicsRepository.findAllTitleWithConditionOfWriterAndDirectorIsSameAndAlive();
    }

    public List<Long> findAllTitleWithConditionOfPlayingBothActors(List<Long> actoreIdList) {
        return basicsRepository.findAllTitleWithConditionOfPlayingBothActors(actoreIdList);
    }

    public List<TopTitleRatingDto> findBestTitleOnEachYearBaseOnGenre(String genre) {
        return ratingRepository.findBestTitleOnEachYearBaseOnGenre(genre);
    }
}
