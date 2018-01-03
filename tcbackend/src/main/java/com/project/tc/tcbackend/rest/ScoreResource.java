package com.project.tc.tcbackend.rest;

import com.google.common.collect.Lists;
import com.project.tc.tcbackend.model.Score;
import com.project.tc.tcbackend.repository.ScoreRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    return this.scoreRepository.save(score);
  }
}
