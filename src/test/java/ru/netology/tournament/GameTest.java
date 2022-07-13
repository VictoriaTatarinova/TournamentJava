package ru.netology.tournament;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.NotRegisteredException;
import ru.netology.domain.Player;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game = new Game();
    private Player player1 = new Player(1, "Bear", 1);
    private Player player2 = new Player(2, "Fox", 2);
    private Player player3 = new Player(3, "Cat", 3);
    private Player player4 = new Player(4, "Dog", 3);


    @BeforeEach
    void setUp() {
        game.register(player1);
        game.register(player2);
        game.register(player3);
        game.register(player4);
    }

    @Test
    void shouldFindRegisteredPlayers() {
        assertEquals(List.of(player1, player2, player3, player4), game.findAll());
    }

    @Test
    void shouldFindByName() {
        assertEquals(player3, game.findByName("Cat"));
    }

    @Test
    void shouldNotFindByName() {
        assertEquals(null, game.findByName("Pig"));
    }

    @Test
    void shouldWinTheFirstOne() {
        int actual = game.round("Dog", "Fox");
        assertEquals(1, actual);
    }


    @Test
    void shouldWinTheSecondOne() {
        int actual = game.round("Bear", "Cat");
        assertEquals(2, actual);
    }

    @Test
    void shouldBeDraw() {
        int actual = game.round("Cat", "Dog");
        assertEquals(0, actual);
    }

    @Test
    void shouldThrowExceptionWhenPlayer1Unreg() {
        assertThrows(NotRegisteredException.class,
                () -> game.round("Mouse", "Dog"));
    }

    @Test
    void shouldThrowExceptionWhen2PlayersUnreg() {
        assertThrows(NotRegisteredException.class,
                () -> game.round("Lion", "Tiger"));
    }

    @Test
    void shouldThrowExceptionWhenPlayer2Unreg() {
        assertThrows(NotRegisteredException.class,
                () -> game.round("Fox", "Cow"));
    }


}
