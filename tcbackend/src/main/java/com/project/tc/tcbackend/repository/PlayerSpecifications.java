package com.project.tc.tcbackend.repository;

import com.project.tc.tcbackend.model.Player;
import com.project.tc.tcbackend.model.Player_;
import com.project.tc.tcbackend.model.Tournament_;
import org.springframework.data.jpa.domain.Specification;

public class PlayerSpecifications {

  public static Specification<Player> playerByTournament(Integer id) {
    return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Player_.tournament).get(Tournament_.id), id);
  }

}
