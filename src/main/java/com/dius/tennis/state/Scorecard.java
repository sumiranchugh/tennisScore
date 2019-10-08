package com.dius.tennis.state;


import java.util.Arrays;

import static com.dius.tennis.state.Score.DEUCE;
import static com.dius.tennis.state.Score.ZERO;

public class Scorecard {

    Score[] gameScore;
    int[] scoreSet;
    int[] tbScore;
    boolean tieBreak;

    public Scorecard() {
        clear();
    }

    public int[] getTbScore() {
        return tbScore;
    }

    public void setTbScore(int[] tbScore) {
        this.tbScore = tbScore;
    }

    public Score[] getGameScore() {
        return gameScore;
    }

    public void setGameScore(Score[] gameScore) {
        this.gameScore = gameScore;
    }

    public int[] getScoreSet() {
        return scoreSet;
    }

    public void setScoreSet(int[] scoreSet) {
        this.scoreSet = scoreSet;
    }

    public boolean isTieBreak() {
        return tieBreak;
    }

    public void setTieBreak(boolean tieBreak) {
        this.tieBreak = tieBreak;
    }

    @Override
    public String toString() {
        return "Scorecard{" +
                "GAME SCORE = " + Arrays.toString(gameScore) +
                ", SET SCORE=" + Arrays.toString(scoreSet) +
                ", TIE BREAK SCORE=" + Arrays.toString(tbScore) +
                ", IS A TIE - BREAK=" + tieBreak +
                '}';
    }

    public void init() {
        gameScore[0] = ZERO;
        gameScore[1] = ZERO;
    }

    public void deuce() {
        gameScore[0] = DEUCE;
        gameScore[1] = DEUCE;
    }

    public void clear() {
        gameScore = new Score[]{ZERO, ZERO};
        scoreSet = new int[]{0, 0};
        tbScore = new int[]{0, 0};
    }
}