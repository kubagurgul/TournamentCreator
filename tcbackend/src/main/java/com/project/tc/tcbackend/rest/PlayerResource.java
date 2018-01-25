package com.project.tc.tcbackend.rest;

import com.project.tc.tcbackend.model.Player;
import com.project.tc.tcbackend.repository.PlayerRepository;
import com.project.tc.tcbackend.repository.PlayerSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.where;

@RestController
public class PlayerResource {

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping
    @RequestMapping("/players/byTournament/{tournamentId}")
    public List<Player> getPlayersByTournamentId(@PathVariable("tournamentId") Integer tournamentId) {
        List<Player> players = playerRepository.findAll(where(PlayerSpecifications.playerByTournament(tournamentId)));
        return players;
    }

    @GetMapping
    @RequestMapping("/players")
    public List<Player> getAllPlayers() {
        List<Player> all = playerRepository.findAll();
        return all;
    }

    @PostMapping
    @RequestMapping("/players/create")
    public Player storePlayer(@RequestBody Player player) {
        return playerRepository.save(player);
    }


}
