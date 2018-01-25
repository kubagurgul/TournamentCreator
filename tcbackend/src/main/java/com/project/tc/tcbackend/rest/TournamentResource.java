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
  public Tournament getTournamentById(@PathVariable("id") Integer tournamentId) {
    Tournament tournament = tournamentRepository.findById(tournamentId).get();
    if (tournament.getPlayers() == null) {
      tournament.setPlayers(new ArrayList<>());
    }
    return tournament;
  }

  @GetMapping
  @RequestMapping("/tournaments/exists/{id}")
  public Boolean getTournamentExistsById(@PathVariable("id") Integer tournamentId) {
    return tournamentRepository.existsById(tournamentId);
  }

  @PostMapping
  @RequestMapping("/tournaments/store")
  public Tournament storeTournament(@RequestBody Tournament tournament) {
    Tournament saved = tournamentRepository.save(tournament);
    return saved;
  }
}
