/*
 * Created by Pyatakov Roman BPI 185 on 06.02.2020
 */
package Game;

public class Commentator implements Runnable{

    int maxScore = 0;

    public Commentator(Game game,Player nullPlayer){
        this.game = game;
        maxScore = game.getK() * 6;
        currentGameWinner = currentRoundWinner = nullPlayer;
    }

    private int playersPlayed = 0;
    Player currentGameWinner;
    Player currentRoundWinner;
    Game game;
    @Override
    public void run() {
        while (!game.gameIsOver()){
            if (!game.isChecked()){
                Player current = game.lastPlayer;
                currentRoundWinner = (currentRoundWinner.currentRoundScore < current.currentRoundScore)?
                        current:
                        currentRoundWinner;
                if (current.currentRoundScore >= maxScore){
                    roundEnd();
                }
                System.out.println("");
                game.setChecked(true);
            }
        }
    }
    public void roundEnd(){
        currentGameWinner = (currentRoundWinner.currentRoundsWin > currentGameWinner.currentRoundsWin)?
                currentRoundWinner:
                currentGameWinner;
        currentRoundWinner.currentRoundsWin++;
        for (int i = 0; i < game.getN(); i++) {
            game.roundEnd();
        }
    }
}
