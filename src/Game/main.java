package Game;/*
 * Created by Pyatakov Roman BPI 185 on 06.02.2020
 */

public class main {
    public static void main(String[] args) {
        try{
            Game game = new Game(args[0],args[1],args[2]);

            game.startGame();
        }
        catch (Exception exc){
            System.out.println("Неверный ввод данных...");
        }
    }
}
