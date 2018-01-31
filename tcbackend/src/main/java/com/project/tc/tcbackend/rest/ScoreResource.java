package com.project.tc.tcbackend.rest;

import com.google.common.collect.Lists;
import com.project.tc.tcbackend.model.Result;
import com.project.tc.tcbackend.model.Score;
import com.project.tc.tcbackend.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScoreResource {

  @Autowired
  private ScoreRepository scoreRepository;

  @GetMapping
  @RequestMapping("score/{tournamentId}")
  public List<Score> getScoresByTournamentId(@PathVariable Integer tournamentId) {
    return Lists.newArrayList(scoreRepository.findAll());
  }

  @PostMapping
  @RequestMapping("score/create")
  public Score storeScore(@RequestBody Score score) {
    if (score.getAwayResult() > score.getHomeResult()) {
      score.setResult(Result.AWAY_WON);
    } else if (score.getAwayResult() < score.getHomeResult()) {
      score.setResult(Result.HOME_WON);
    } else {
      score.setResult(Result.DRAW);
    }
    return this.scoreRepository.save(score);
  }
}
