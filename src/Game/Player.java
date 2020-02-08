/*
 * Created by Pyatakov Roman BPI 185 on 06.02.2020
 */
package Game;

public class Player implements Runnable {
    Game game;
    int currentRoundScore = 0;
    int currentRoundsWin = 0;
    int id;

    public Player(Game game, int id) {
        this.game = game;
        this.id = id;
    }

    @Override
    public synchronized void run() {
        while (!game.gameIsOver()) {
            currentRoundScore = game.throwDice(this);
            if (wait){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void roundEnd() {
        wait = false;
        currentRoundScore = 0;
    }

    private boolean wait;
    void setWait(boolean t){
        wait = t;
    }

    public String toString() {
        return ("Игрок №_" + (id+1));
    }
}
