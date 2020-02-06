package Game;/*
 * Created by Pyatakov Roman BPI 185 on 06.02.2020
 */

import java.util.Random;

public class Game {
    int minN = 2, maxN = 6; //players
    int minK = 2, maxK = 5; //dices
    int minM = 1, maxM = 100; //wins
    private int n,m,k;
    private Player[] players;
    private Commentator commentator;
    Player lastPlayer;
    public boolean gameIsOver(){
        return (commentator.currentGameWinner.currentRoundsWin != maxM);
    }
    private void CheckInput(int n, int k, int m){
        if ((n < minN || n > maxN) ||
                (k < minK || k > maxK) ||
                (m < minM || m > maxM)){
            throw new IllegalArgumentException("Wrong numbers in input :(");
        }
    }
    public Game(String N, String K, String M){
        System.out.println(N + K + M);
        n = Integer.parseInt(N);
        k = Integer.parseInt(K);
        m = Integer.parseInt(M);
        CheckInput(n,k,m);
        players = new Player[n];
        for (int i = 0; i < n; i++) {
            players[i] = new Player(this,i);
        }
    }

    public synchronized int throwDice() {
        int answ = 0;
        if (isChecked) {
            Random rnd = new Random();
            for (int i = 0; i < k; i++) {
                answ = (rnd.nextInt(5)) + 1;
            }
            isChecked = false;
            try {
                wait();
            } catch (InterruptedException exc){
                exc.printStackTrace();
            }
        }
        return answ;
    }
    boolean isChecked = true;


    public void startGame(){
        Thread[] threads = new Thread[n];
        for (int i = 0; i < n; i++) {
            threads[i] = new Thread(players[i],"Player " + i);

        }
    }
}
