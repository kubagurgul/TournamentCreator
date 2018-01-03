package com.project.tc.tcbackend;

import com.project.tc.tcbackend.model.Tournament;
import com.project.tc.tcbackend.repository.PlayerRepository;
import com.project.tc.tcbackend.repository.ScoreRepository;
import com.project.tc.tcbackend.repository.TournamentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TcbackendApplicationTests {
	@Autowired
	private TournamentRepository tournamentRepository;
	@Autowired
	private ScoreRepository scoreRepository;
	@Autowired
	private PlayerRepository playerRepository;

	@Test
	public void contextLoads() {
	}

}
