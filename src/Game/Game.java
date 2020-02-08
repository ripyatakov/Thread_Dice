package Game;/*
 * Created by Pyatakov Roman BPI 185 on 06.02.2020
 */

import java.util.ArrayDeque;
import java.util.LinkedList;

import java.util.Random;

public class Game {
    ArrayDeque<String> gameJournal = new ArrayDeque<>();
    int minN = 2, maxN = 6; //players
    int minK = 2, maxK = 5; //dices
    int minM = 1, maxM = 100; //wins
    private Random rnd = new Random();

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public int getK() {
        return k;
    }

    private int n, m, k;
    private Player[] players;
    Commentator commentator;
    Player lastPlayer;
    Player currentRoundWinner;
    Player currentGameWinner;

    private boolean gameIsOver = false;

    public boolean gameIsOver() {
        return gameIsOver;
    }

    private void CheckInput(int n, int k, int m) {
        if ((n < minN || n > maxN) ||
                (k < minK || k > maxK) ||
                (m < minM || m > maxM)) {
            throw new IllegalArgumentException("Wrong numbers in input :(");
        }
    }

    public Game(String N, String K, String M) {
        System.out.println(N + K + M);
        n = Integer.parseInt(N);
        k = Integer.parseInt(K);
        m = Integer.parseInt(M);
        CheckInput(n, k, m);
        players = new Player[n];
        for (int i = 0; i < n; i++) {
            players[i] = new Player(this, i);
        }
        commentator = new Commentator(this, players[0]);
        currentGameWinner = players[0];
        currentRoundWinner = players[0];
    }

    public synchronized int throwDice(Player player) {
        playersPlayed++;
        if (fastFinish) {
            player.setWait(true);
            if (playersPlayed == n) {
                roundEnd();
            }
            return 0;
        }
        int answ = 0;
        for (int i = 0; i < k; i++) {
            answ += (rnd.nextInt(6)) + 1;
        }
        lastPlayer = player;
        if (answ > currentRoundWinner.currentRoundScore) {
            currentRoundWinner = player;
        }
        if (playersPlayed == getN() || answ == k * 6) {
            gameJournal.addLast(player + " набрал " + answ);
        } else {
            gameJournal.addLast(player + " набрал " + answ + " лидирует " + currentRoundWinner);
        }
        //gameJournal.addLast(strToJournal(false,player,answ));
        synchronized (commentator) {
            commentator.notify();
        }
        player.setWait(true);
        if (answ == 6 * k) {
            fastFinish = true;
        }
        if (playersPlayed >= getN()) {
            playersPlayed = n;
            player.setWait(false);
            roundEnd();
        }
        return answ;
    }

    int playersPlayed = 0;
    boolean fastFinish = false;

    synchronized void roundEnd() {
        fastFinish = false;
        currentRoundWinner.currentRoundsWin++;
        if (currentRoundWinner.currentRoundsWin > currentGameWinner.currentRoundsWin) {
            currentGameWinner = currentRoundWinner;
        }
        if (currentRoundWinner.currentRoundsWin != getM()) {
            gameJournal.addLast("Раунд закончился, победил: " + currentRoundWinner + " со счетом " + currentRoundWinner.currentRoundScore +"" +
                    " выиграл игр "+currentRoundWinner.currentRoundsWin + "\nЛидер " +
                    "" + currentGameWinner + " " + (currentGameWinner.currentRoundsWin) + "\n");
        } else {
            gameJournal.addLast("Раунд закончился, победил: " + currentRoundWinner);
        }
        playersPlayed = 0;
        synchronized (commentator) {
            commentator.notify();
        }
        if (currentGameWinner.currentRoundsWin >= m) {
            gameIsOver = true;
        }
        for (int i = 0; i < n; i++) {
            players[i].roundEnd();
        }
        for (int i = 0; i < n; i++) {
            synchronized (players[i]) {
                players[i].notifyAll();
            }
        }
    }

    public String strToJournal(boolean isEnd, Player player, int score) {
        if (isEnd) {
            return "Раунд закончился, победил: " + currentRoundWinner + " Лидер " +
                    "" + currentGameWinner + " " + (currentGameWinner.currentRoundsWin);
        } else {
            if (playersPlayed == getN()) {
                return player + " набрал " + score;
            } else {
                return player + " набрал " + score + " лидирует " + currentRoundWinner;
            }
        }
    }

    public void startGame() {
        Thread[] threads = new Thread[n];

        for (int i = 0; i < n; i++) {
            threads[i] = new Thread(players[i], "Player " + i);
            threads[i].start();
        }
        Thread commentatorThread = new Thread(commentator, "Commentator");
        commentatorThread.start();
    }
}
