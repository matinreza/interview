package com.app.interview.bootstrap;

import com.app.interview.domain.common.util.TsvFileReader;
import com.app.interview.domain.person.entity.Person;
import com.app.interview.domain.person.service.PersonService;
import com.app.interview.domain.title.entity.TitleBasics;
import com.app.interview.domain.title.entity.TitleEpisode;
import com.app.interview.domain.title.entity.TitlePrincipalsCrew;
import com.app.interview.domain.title.entity.TitleRating;
import com.app.interview.domain.title.entity.enums.Category;
import com.app.interview.domain.title.entity.enums.Genre;
import com.app.interview.domain.title.entity.enums.TitleType;
import com.app.interview.domain.title.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.StructuredTaskScope;

@Service
@RequiredArgsConstructor
public class DataConvertor {
    private static final Logger LOGGER = LogManager.getLogger(DataConvertor.class);

    private TsvFileReader reader;
    private final TitleService titleService;
    private final PersonService personService;

    @Value("${converter.file.path}")
    private String filePath;

    private void readTitleBasics() {
        reader.readTsvFile((row) -> {
            TitleBasics titleBasics = new TitleBasics();
            titleBasics.setTitleType(TitleType.valueOf(row[1].replaceAll("short", "short_")));
            titleBasics.setPrimaryTitle(row[2]);
            titleBasics.setOriginalTitle(row[3]);
            titleBasics.setForAdult(Boolean.parseBoolean(row[4]));
            if (!Objects.equals(row[5], "\\N"))
                titleBasics.setStartYear(Integer.parseInt(row[5]));
            if (!Objects.equals(row[6], "\\N"))
                titleBasics.setEndYear(Integer.parseInt(row[6]));
            if (!Objects.equals(row[7], "\\N"))
                titleBasics.setRuntimeMinutes(Integer.parseInt(row[7]));

            Set<Genre> genreSet = new HashSet<>();
            for (String g : row[8].replaceAll("Reality-TV", "Reality_TV")
                    .replaceAll("Game-Show", "Game_Show")
                    .replaceAll("Tk-Show", "Tk_Show")
                    .replaceAll("Talk-Show", "Tk_Show")
                    .replaceAll("Musical", "Music")
                    .replaceAll("Film-Noir", "Film_Noir")
                    .replaceAll("Sci-Fi", "Sci_Fi").split(","))
                if (!g.equals("\\N"))
                    genreSet.add(Genre.valueOf(g));
            titleBasics.setGenreSet(genreSet);
            titleBasics.setExternalId(row[0]);
            titleService.saveTitleBasics(titleBasics);
        }, "title.basics.tsv");
    }

    private void readNameBasics() {
        reader.readTsvFile((row) -> {
            Person person = new Person();
            person.setExternalId(row[0]);
            person.setFullName(row[1]);
            if (!Objects.equals(row[2], "\\N"))
                person.setBirthYear(Integer.parseInt(row[2]));
            if (!Objects.equals(row[3], "\\N"))
                person.setDeathYear(Integer.parseInt(row[3]));
            person.setProfession(row[4]);
            person.setExternalKnowIds(row[5]);
            personService.savePerson(person);
        }, "name.basics.tsv");
    }

    private void readTitleRating() {
        reader.readTsvFile(row -> {
            TitleRating titleRating = new TitleRating();
            titleRating.setTitleBasics(titleService.findByExternalId(row[0]));
            titleRating.setAverageRating(Double.parseDouble(row[1]));
            titleRating.setNumVotes(Integer.parseInt(row[2]));
            titleService.saveTitleRating(titleRating);
        }, "title.ratings.tsv");

    }

    private void readTitlePrincipals() {
        reader.readTsvFile(row -> {
            TitlePrincipalsCrew p = new TitlePrincipalsCrew();
            p.setTitleBasics(titleService.findByExternalId(row[0]));
            p.setOrdering(Integer.parseInt(row[1]));
            p.setPerson(personService.findPersonByExternalId(row[2]));
            p.setCategory(Category.valueOf(row[3]));
            if (!Objects.equals(row[4], "\\N"))
                p.setJob(row[4]);
            if (!Objects.equals(row[5], "\\N"))
                p.setCharacters(row[5]);
            titleService.saveTitlePrincipals(p);
        }, "title.principals.tsv");
    }

    private void readTitleCrew() {
        reader.readTsvFile(row -> {
            if (!Objects.equals(row[1], "\\N")) {
                for (String director : row[1].split(",")) {
                    TitlePrincipalsCrew titlePrincipalsCrew = new TitlePrincipalsCrew();
                    titlePrincipalsCrew.setTitleBasics(titleService.findByExternalId(row[1]));
                    Person person = personService.findPersonByExternalId(director);
                    titlePrincipalsCrew.setPerson(person);
                    titlePrincipalsCrew.setCategory(Category.director);
                    titleService.saveTitlePrincipals(titlePrincipalsCrew);
                }
            }
            if (!Objects.equals(row[2], "\\N")) {
                for (String writer : row[2].split(",")) {
                    TitlePrincipalsCrew titlePrincipalsCrew = new TitlePrincipalsCrew();
                    titlePrincipalsCrew.setTitleBasics(titleService.findByExternalId(row[2]));
                    Person person = personService.findPersonByExternalId(writer);
                    titlePrincipalsCrew.setPerson(person);
                    titlePrincipalsCrew.setCategory(Category.writer);
                    titleService.saveTitlePrincipals(titlePrincipalsCrew);
                }
            }
        }, "title.crew.tsv");
    }

    private void readTitleEpisode() {
        reader.readTsvFile(row -> {
            TitleEpisode episode = new TitleEpisode();
            episode.setTitleBasics(titleService.findByExternalId(row[0]));
            episode.setParentTitle(titleService.findByExternalId(row[1]));
            if (!Objects.equals(row[2], "\\N")) {
                episode.setSeasonNumber(Integer.parseInt(row[2]));
            }
            if (!Objects.equals(row[3], "\\N")) {
                episode.setEpisodeNumber(Integer.parseInt(row[3]));
            }
            titleService.saveTitleEpisode(episode);
        }, "title.episode.tsv");
    }

    public void convert() throws InterruptedException {
        LOGGER.info("Data is Converting...");
        reader = new TsvFileReader(filePath);
        try (var scope = StructuredTaskScope.open()) {
            scope.fork(this::readTitleBasics);
            scope.fork(this::readNameBasics);
            scope.join();
        }
        try (var scope = StructuredTaskScope.open()) {
            scope.fork(this::readTitleRating);
            scope.fork(this::readTitlePrincipals);
            scope.fork(this::readTitleCrew);
            scope.fork(this::readTitleEpisode);
            scope.join();
        }
        LOGGER.info("Data is converted...");
    }
}