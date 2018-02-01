package com.project.tc.tcbackend.rest;

import com.google.common.collect.Lists;
import com.project.tc.tcbackend.model.Result;
import com.project.tc.tcbackend.model.Tournament;
import com.project.tc.tcbackend.obj.TeamStatsDTO;
import com.project.tc.tcbackend.repository.TournamentRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping
    @RequestMapping("/tournaments/{id}/table")
    public List<TeamStatsDTO> getTeamsStats(@PathVariable("id") Integer tournamentId) {
        Optional<Tournament> tournament = tournamentRepository.findById(tournamentId);
        List<TeamStatsDTO> stats = new ArrayList<>();
        if (tournament.isPresent()) {
            stats = tournament.get().getPlayers()
                    .stream()
                    .map(p -> {
                        System.out.println("calculating for p with id:" + p.getId());
                        TeamStatsDTO statsDTO = new TeamStatsDTO(p.getId());
                        tournament.get().getScores()
                                .stream()
                                .filter(
                                        score -> score.getPlayerAway().getId().equals(p.getId())
                                                || score.getPlayerHome().getId().equals(p.getId()))
                                .forEach(score -> {
                                    statsDTO.setPlayed(statsDTO.getPlayed() + 1);
                                    boolean isHome = score.getPlayerHome().getId().equals(p.getId());
                                    if (score.getResult() == Result.DRAW) {
                                        statsDTO.addPoints(1);
                                        statsDTO.addDraw();
                                    } else if (score.getResult() == Result.HOME_WON && isHome) {
                                        statsDTO.addPoints(3);
                                        statsDTO.addWin();
                                    } else {
                                        // no points
                                        statsDTO.addLost();
                                    }

                                    if (isHome) {
                                        statsDTO.setGf(statsDTO.getGf() + score.getHomeResult());
                                        statsDTO.setGa(statsDTO.getGa() + score.getAwayResult());
                                    } else {
                                        statsDTO.setGf(statsDTO.getGf() + score.getAwayResult());
                                        statsDTO.setGa(statsDTO.getGa() + score.getHomeResult());
                                    }

                                });
                        statsDTO.setGd(statsDTO.getGf() - statsDTO.getGa());
                        return statsDTO;
                    })
                    .sorted((Comparator.comparing(TeamStatsDTO::getPoints)))
                    .collect(Collectors.toList());
        }
        return stats;

    }
}
