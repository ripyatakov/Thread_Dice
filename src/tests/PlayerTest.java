package tests;

import Game.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player mainPlayer = new Player(null,0);
    Player player2 = new Player(null,1);
    @Test
    void testToString() {
        Assertions.assertEquals("Игрок №_1", mainPlayer.toString());
    }

    @Test
    void compareTo() {
        /// score 0 == 0
        Assertions.assertEquals(0,mainPlayer.compareTo(player2));
    }
}