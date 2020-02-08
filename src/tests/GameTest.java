package tests;

import Game.Game;
import  org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

class GameTest {
    Game mainGame = new Game("3","3","3");
    @Test
    void getN() {

        int expected = 3;
        Assertions.assertEquals(expected,mainGame.getN());
    }

    @Test
    void getM() {
        int expected = 3;
        Assertions.assertEquals(expected,mainGame.getM());
    }

    @Test
    void getK() {
        int expected = 3;
        Assertions.assertEquals(expected,mainGame.getK());
    }

    @Test
    void throwDice(){
        Assertions.assertThrows(NullPointerException.class, () -> {mainGame.throwDice(null);});
    }
    @Test
    void gameCreation(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Game("0","0","0");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Game("2","1","2");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Game("2","1","101");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Game("7","1","2");});
    }
}