/*
 * Created by Pyatakov Roman BPI 185 on 06.02.2020
 */
package Game;

public class Player implements Runnable {
    Game game;
    int currentRoundScore = 0;
    int currentRoundsWin = 0;
    int id;
    public Player(Game game, int id){
        this.game = game;
        this.id = id;
    }

    @Override
    public void run() {
        while (!game.gameIsOver()){
            currentRoundScore = game.throwDice();
            game.lastPlayer = this;
        }
    }
}
