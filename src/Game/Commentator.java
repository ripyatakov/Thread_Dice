/*
 * Created by Pyatakov Roman BPI 185 on 06.02.2020
 */
package Game;

public class Commentator implements Runnable{
    public int getRoundsPlayed() {
        return roundsPlayed;
    }

    private int roundsPlayed = 0;
    Player currentGameWinner;
    Player currentRoundWinner;

    @Override
    public void run() {

    }
}
