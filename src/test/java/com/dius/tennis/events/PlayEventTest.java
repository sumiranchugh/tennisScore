package com.dius.tennis.events;

import com.dius.tennis.state.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class PlayEventTest {

    PlayEvent playEvent ;

    @BeforeEach
    public void init(){
        playEvent = new PlayEvent();
    }

    @Test()
    @DisplayName("Test successfull game")
    void successfulGame() {
        assertArrayEquals(playEvent.score(0).getGameScore(), new Score[]{Score.ONE, Score.ZERO});
        assertArrayEquals(playEvent.score(0).getGameScore(), new Score[]{Score.TWO, Score.ZERO});
        assertArrayEquals(playEvent.score(0).getGameScore(), new Score[]{Score.THREE, Score.ZERO});
        assertArrayEquals(playEvent.score(0).getGameScore(), new Score[]{Score.ZERO, Score.ZERO});
        assertArrayEquals(playEvent.getScorecard().getScoreSet(), new int[]{1,0});
    }

    @Test()
    void Deuce() {
        assertArrayEquals(playEvent.score(0).getGameScore(), new Score[]{Score.ONE, Score.ZERO});
        assertArrayEquals(playEvent.score(1).getGameScore(), new Score[]{Score.ONE, Score.ONE});
        assertArrayEquals(playEvent.score(0).getGameScore(), new Score[]{Score.TWO, Score.ONE});
        assertArrayEquals(playEvent.score(1).getGameScore(), new Score[]{Score.TWO, Score.TWO});
        assertArrayEquals(playEvent.score(0).getGameScore(), new Score[]{Score.THREE, Score.TWO});
        assertArrayEquals(playEvent.score(1).getGameScore(), new Score[]{Score.DEUCE, Score.DEUCE});
    }

    @Test()
    void Advantage() {
        assertArrayEquals(playEvent.score(0).getGameScore(), new Score[]{Score.ONE, Score.ZERO});
        assertArrayEquals(playEvent.score(1).getGameScore(), new Score[]{Score.ONE, Score.ONE});
        assertArrayEquals(playEvent.score(0).getGameScore(), new Score[]{Score.TWO, Score.ONE});
        assertArrayEquals(playEvent.score(1).getGameScore(), new Score[]{Score.TWO, Score.TWO});
        assertArrayEquals(playEvent.score(0).getGameScore(), new Score[]{Score.THREE, Score.TWO});
        assertArrayEquals(playEvent.score(1).getGameScore(), new Score[]{Score.DEUCE, Score.DEUCE});
        assertArrayEquals(playEvent.score(1).getGameScore(), new Score[]{Score.DEUCE, Score.ADVANTAGE});
        assertArrayEquals(playEvent.score(1).getGameScore(), new Score[]{Score.ZERO, Score.ZERO});
        assertArrayEquals(playEvent.getScorecard().getScoreSet(), new int[]{0,1});
    }

    @Test()
    void tieBreak() {
        playEvent.getScorecard().setScoreSet(new int[]{5,6});
        playEvent.score(0);
        playEvent.score(0);
        playEvent.score(0);
        playEvent.score(0);
        assertArrayEquals(playEvent.getScorecard().getScoreSet(), new int[]{6,6});
        assertTrue(playEvent.getScorecard().isTieBreak());
    }
    @Test()
    void tieBreakWon() {
        playEvent.getScorecard().setScoreSet(new int[]{6,6});
        playEvent.getScorecard().setTieBreak(true);
        playEvent.score(0);
        playEvent.score(0);
        playEvent.score(0);
        playEvent.score(0);
        playEvent.score(0);
        playEvent.score(0);
        playEvent.score(0);
        assertArrayEquals(playEvent.getScorecard().getScoreSet(), new int[]{7,6});
        assertArrayEquals(playEvent.getScorecard().getTbScore(), new int[]{7,0});
        assertTrue(playEvent.getScorecard().isTieBreak());
    }

    @Test()
    void matchWon() {
        playEvent.getScorecard().setScoreSet(new int[]{6,5});
        playEvent.score(0);
        playEvent.score(0);
        playEvent.score(0);
        playEvent.score(0);
        assertArrayEquals(playEvent.getScorecard().getScoreSet(), new int[]{7,5});
        assertTrue(playEvent.clear);
    }

}