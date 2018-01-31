package com.project.tc.tcbackend.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tournament")
public class Tournament {

  private Integer id;
  private String name;
  private String url;
  private List<Score> scores = new ArrayList<>();
  private List<Player> players = new ArrayList<>();

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getId() {
    return id;
  }

  @Basic
  public String getName() {
    return name;
  }


  public String getUrl() {
    return url;
  }

  @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
  public List<Score> getScores() {
    return scores;
  }

  @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
  public List<Player> getPlayers() {
    return players;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setScores(List<Score> scores) {
    this.scores = scores;
  }

  public void setPlayers(List<Player> players) {
    this.players = players;
  }

  @Override
  public String toString() {
    return "Tournament{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", url='" + url + '\'' +
        ", scores=" + scores +
        ", players=" + players +
        '}';
  }
}
