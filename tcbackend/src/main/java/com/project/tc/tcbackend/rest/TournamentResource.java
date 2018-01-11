package com.project.tc.tcbackend.rest;

import com.google.common.collect.Lists;
import com.project.tc.tcbackend.model.Tournament;
import com.project.tc.tcbackend.repository.TournamentRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TournamentResource {
  @Autowired
  private TournamentRepository tournamentRepository;

  @GetMapping
  @RequestMapping("/tournaments")
  public List<Tournament> getAllTournaments() {
    ArrayList<Tournament> tournaments = Lists.newArrayList(tournamentRepository.findAll());
    return tournaments;
  }

  @GetMapping
  @RequestMapping("/tournaments/{id}")
  public Tournament getTournamentById(@PathVariable Integer tournamentId) {
    return tournamentRepository.findById(tournamentId).get();
  }

  @PostMapping
  @RequestMapping("/tournaments/create")
  public Tournament storeTournament(@RequestBody Tournament tournament) {
    Tournament saved = tournamentRepository.save(tournament);
    return saved;
  }
}
