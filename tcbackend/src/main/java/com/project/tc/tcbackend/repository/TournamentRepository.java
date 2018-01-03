package com.project.tc.tcbackend.repository;

import com.project.tc.tcbackend.model.Tournament;
import org.springframework.data.repository.CrudRepository;

public interface TournamentRepository extends CrudRepository<Tournament, Integer> {

}
