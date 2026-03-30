package com.app.interview.interfaces.rest.controller;

import com.app.interview.domain.common.util.ErrorCode;
import com.app.interview.domain.title.entity.enums.Genre;
import com.app.interview.domain.title.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/title")
@RequiredArgsConstructor
public class TitleRest {

    private final TitleService titleService;

    @GetMapping("/v1/list")
    public ResponseEntity<ResponseData> titleList(@RequestParam(name = "directorWriterIsSame",
                                                          required = false,
                                                          defaultValue = "false") boolean directorWriterIsSame,
                                                  @RequestParam(name = "actoreIdList",
                                                          required = false) List<Long> actoreIdList) {
        if (directorWriterIsSame)
            return ResponseEntity.ok(new ResponseData(titleService.findAllTitleWithConditionOfWriterAndDirectorIsSameAndAlive(), null));
        else
            return ResponseEntity.ok(new ResponseData(titleService.findAllTitleWithConditionOfPlayingBothActors(actoreIdList), null));
    }

    @GetMapping("/v1/bestEachYear/list")
    public ResponseEntity<ResponseData> findBestTitleOnEachYearBaseOnGenre(@RequestParam(name = "genre") String genre) {
        try {
            return ResponseEntity.ok(new ResponseData(titleService.findBestTitleOnEachYearBaseOnGenre(Genre.valueOf(genre).name()), null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok(new ResponseData(null, new ErrorResponse("", ErrorCode.INVALID_ENUM_VALUE)));
        }
    }
}
