/*
 * Created by Pyatakov Roman BPI 185 on 06.02.2020
 */
package Game;

import java.util.ArrayList;

public class Commentator implements Runnable {

    int maxScore = 0;

    public Commentator(Game game, Player nullPlayer) {
        this.game = game;
        maxScore = game.getK() * 6;
        currentGameWinner = currentRoundWinner = nullPlayer;
    }

    Player currentGameWinner;
    Player currentRoundWinner;
    Game game;

    @Override
    public synchronized void run() {
        while (!game.gameIsOver() || !game.gameJournal.isEmpty()){
            if (!game.gameJournal.isEmpty()){
                System.out.println(game.gameJournal.pollFirst());
            } else{
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Поздравляю победителя " + game.currentGameWinner);
        ArrayList<Player> players = game.sortedPlayers();
        int place = 0;
        for (var a:players) {
            System.out.println(++place + " место " + a + " побед " + a.currentRoundsWin);
        }
    }

}
