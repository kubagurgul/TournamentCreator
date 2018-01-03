package com.project.tc.tcbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="player")
public class Player {
  private Integer id;
  private String name;
  private Tournament tournament;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getId() {
    return id;
  }

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  public Tournament getTournament() {
    return tournament;
  }

  public String getName() {
    return name;
  }


  public void setId(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }


  public void setTournament(Tournament tournament) {
    this.tournament = tournament;
  }
}
