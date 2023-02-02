package app.redoge.mvp.util;

import app.redoge.mvp.enums.GameNames;
import app.redoge.mvp.exceptions.FileWasCorruptedExeption;
import app.redoge.mvp.exceptions.GameNotFoundException;
import app.redoge.mvp.exceptions.PlayerIsExistException;
import app.redoge.mvp.models.Game;
import app.redoge.mvp.models.players.BasketballPlayer;
import app.redoge.mvp.models.players.HandballPlayer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static app.redoge.mvp.util.PlayerUtil.addPlayersToGame;
import static app.redoge.mvp.util.WinnerFinder.addBonusScore;
import static org.junit.jupiter.api.Assertions.*;

class PlayerUtilTest {

    @Test
    void addPlayersToIncorrectGameTest(){
        Game game = new Game("FOOTBALL");
        assertThrows(GameNotFoundException.class, () -> addPlayersToGame(new ArrayList<String>(),game, game.getName()));
    }

    @Test
    void addBasketballPlayerTest() throws FileWasCorruptedExeption, PlayerIsExistException {
        Game game = new Game("BASKETBALL");
        List<String> players = List.of("player 1;nick1;4;Team A;10;2;7",
                "player 2;nick2;8;Team A;0;10;0",
                "player 3;nick3;15;Team A;15;10;4",
                "player 4;nick4;16;Team B;20;0;0",
                "player 5;nick5;23;Team B;4;7;7",
                "player 6;nick6;42;Team B;8;10;0");
        addPlayersToGame(players, game, game.getName());
        assertEquals(6,game.getPlayers().size());
    }
    @Test
    void addHandballPlayerTest() throws FileWasCorruptedExeption, PlayerIsExistException {
        Game game = new Game("HANDBALL");
        List<String> players = List.of("player 1;nick1;4;Team A;0;20",
                "player 2;nick2;8;Team A;15;20",
                "player 3;nick3;15;Team A;10;20",
                "player 4;nick4;16;Team B;1;25",
                "player 5;nick5;23;Team B;12;25",
                "player 6;nick6;42;Team B;8;25");
        addPlayersToGame(players, game, game.getName());
        assertEquals(6,game.getPlayers().size());
    }

}