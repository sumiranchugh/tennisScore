package com.dius.tennis.events;

import com.dius.tennis.state.Scorecard;

import static com.dius.tennis.state.Score.*;

class PlayEvent {

    int winIndex;
    int loseIndex;
    Scorecard scorecard = new Scorecard();
    boolean clear;

//    public static void main(String[] args) {
//        System.out.println("Match starts");
//        PlayEvent event = new PlayEvent();
//        event.score(0);
//        event.score(1);
//        event.score(0);
//        event.score(1);
//        event.score(0);
//        event.score(1);
//        event.score(1);
//        event.score(0);
//        event.score(0);
//        event.score(0);
//    }

    Scorecard score(int winIndex) {
        if (clear) {
            System.out.println("clear scorecard for next game");
            scorecard.clear();
        }
        this.winIndex = winIndex;
        loseIndex = 1 - winIndex;
        if (scorecard.isTieBreak()) {
            handleTieBreak();
        } else {
            handleGame();
        }
        clear  = handleSet();
        return scorecard;
    }

    private boolean handleSet() {
        int winnerScore = scorecard.getScoreSet()[winIndex];
        int loserScore = scorecard.getScoreSet()[loseIndex];
        if (winnerScore == 6 && winnerScore == loserScore) {
            scorecard.setTieBreak(true);
            return false;
        }
        if (winnerScore >= 6 && winnerScore - loserScore >= 2) {
            System.out.println("Player" + winIndex + " WON THE MATCH");
            return true;
        }
        return false;

    }

    private void handleTieBreak() {
        scorecard.getTbScore()[winIndex] += 1;
        int winnerScore = scorecard.getTbScore()[winIndex];
        int loserScore = scorecard.getTbScore()[loseIndex];
        if (winnerScore >= 7 && winnerScore - loserScore >= 2) {
            System.out.println("Player" + winIndex + " WON THE GAME");
            scorecard.getScoreSet()[winIndex] += 1;
            clear = true;
        }
    }

    private void handleGame() {

        switch (scorecard.getGameScore()[winIndex]) {
            case ZERO:
                scorecard.getGameScore()[winIndex] = (ONE);
                break;
            case ONE:
                scorecard.getGameScore()[winIndex] = (TWO);
                break;
            case TWO:
                if (scorecard.getGameScore()[loseIndex].equals(THREE)) {
                    scorecard.deuce();
                    System.out.println("Its a deuce");
                } else {
                    scorecard.getGameScore()[winIndex] = (THREE);
                }
                break;
            case THREE:
                scorecard.getScoreSet()[winIndex] += 1;
                System.out.println("player won the game");
                scorecard.init();
                break;
            case DEUCE:
                if (scorecard.getGameScore()[loseIndex].equals(ADVANTAGE)) {
                    scorecard.deuce();
                } else {
                    scorecard.getGameScore()[winIndex] = ADVANTAGE;
                }
                break;
            case ADVANTAGE:
                scorecard.init();
                scorecard.getScoreSet()[winIndex] = scorecard.getScoreSet()[winIndex] + 1;
                System.out.println("player won the game");
                break;
        }

    }

    public Scorecard getScorecard() {
        return this.scorecard;
    }
}