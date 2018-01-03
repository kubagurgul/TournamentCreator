package com.project.tc.tcbackend;

import com.project.tc.tcbackend.model.Player;
import com.project.tc.tcbackend.model.Result;
import com.project.tc.tcbackend.model.Score;
import com.project.tc.tcbackend.model.Tournament;
import com.project.tc.tcbackend.repository.PlayerRepository;
import com.project.tc.tcbackend.repository.ScoreRepository;
import com.project.tc.tcbackend.repository.TournamentRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTest {

  @Autowired
  private TournamentRepository tournamentRepository;
  @Autowired
  private PlayerRepository playerRepository;
  @Autowired
  private ScoreRepository scoreRepository;

  @Before
  public void setup() {
    tournamentRepository.deleteAll();
    playerRepository.deleteAll();
    scoreRepository.deleteAll();
  }

  @Test
  public void testPlayer() {
    Tournament t = new Tournament();
    t.setName("tournament yyy");
    t.setUrl("xrtg4h5");
    Tournament tt = this.tournamentRepository.save(t);

    Player p = new Player();
    p.setName("df");
    p.setTournament(tt);
    Player pp = playerRepository.save(p);

    Assert.assertNotNull(pp);
    Assert.assertEquals("df", pp.getName());
    Assert.assertEquals(tt.getId(), pp.getTournament().getId());

    Player p1 = new Player();
    p1.setName("bolo");
    p1.setTournament(tt);
    Player pp1 = playerRepository.save(p1);

    Assert.assertNotNull(pp1);
    Assert.assertEquals("bolo", pp1.getName());
    Assert.assertEquals(tt.getId(), pp1.getTournament().getId());


  }

  @Test
  public void testScore() {
    Tournament t = new Tournament();
    t.setName("tournament qqq");
    t.setUrl("345y56");
    Tournament tt = this.tournamentRepository.save(t);

    Player p = new Player();
    p.setName("p1");
    p.setTournament(tt);
    Player pp = playerRepository.save(p);

    Player p1 = new Player();
    p1.setName("p2");
    p1.setTournament(tt);
    Player pp1 = playerRepository.save(p1);

    Score s = new Score();
    s.setPlayerAway(pp);
    s.setPlayerHome(pp1);
    s.setAwayResult(10);
    s.setHomeResult(5);
    s.setTournament(t);
    s.setResult(Result.AWAY_WON);

    Score ss = scoreRepository.save(s);
    Assert.assertNotNull(ss);
    Assert.assertEquals(new Integer(10), ss.getAwayResult());
    Assert.assertEquals(new Integer(5), ss.getHomeResult());
    Assert.assertEquals(t.getId(), ss.getTournament().getId());
    Assert.assertEquals(pp.getId(), ss.getPlayerAway().getId());
    Assert.assertEquals(pp1.getId(), ss.getPlayerHome().getId());
    Assert.assertEquals(Result.AWAY_WON, ss.getResult());

  }

  @Test
  public void testTournament() {
    Tournament t = new Tournament();
    t.setName("Test Tournament");
    t.setUrl("sZX123dt3D");
    Tournament tt = this.tournamentRepository.save(t);

    Assert.assertEquals("Test Tournament", tt.getName());
    Assert.assertEquals("sZX123dt3D", tt.getUrl());
    Assert.assertEquals(new Integer(1), tt.getId());

    Tournament t1 = new Tournament();
    t1.setName("Test TournamentXX");
    t1.setUrl("sZX123dt3DXX");
    Tournament tt1 = this.tournamentRepository.save(t1);

    Assert.assertEquals("Test TournamentXX", tt1.getName());
    Assert.assertEquals("sZX123dt3DXX", tt1.getUrl());
    Assert.assertEquals(new Integer(2), tt1.getId());
  }

  @After
  public void cleanDatabase() {

  }
}
