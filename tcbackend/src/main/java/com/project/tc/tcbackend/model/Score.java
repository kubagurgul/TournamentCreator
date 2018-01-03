package com.project.tc.tcbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "score")
public class Score {

  private Integer id;
  private Player playerHome;
  private Player playerAway;
  private Integer homeResult;
  private Integer awayResult;
  private Result result;
  private Tournament tournament;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getId() {
    return id;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public Player getPlayerHome() {
    return playerHome;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public Player getPlayerAway() {
    return playerAway;
  }

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  public Tournament getTournament() {
    return tournament;
  }

  @Enumerated
  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }


  public void setId(Integer id) {
    this.id = id;
  }

  public void setPlayerHome(Player playerHome) {
    this.playerHome = playerHome;
  }


  public void setPlayerAway(Player playerAway) {
    this.playerAway = playerAway;
  }

  public void setTournament(Tournament tournament) {
    this.tournament = tournament;
  }


  public Integer getHomeResult() {
    return homeResult;
  }

  public void setHomeResult(Integer homeResult) {
    this.homeResult = homeResult;
  }

  public Integer getAwayResult() {
    return awayResult;
  }

  public void setAwayResult(Integer awayResult) {
    this.awayResult = awayResult;
  }
}
