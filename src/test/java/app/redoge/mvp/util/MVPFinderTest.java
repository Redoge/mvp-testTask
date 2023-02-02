package app.redoge.mvp.util;


import app.redoge.mvp.enums.GameNames;
import app.redoge.mvp.exceptions.PlayerIsExistException;
import app.redoge.mvp.models.Game;
import app.redoge.mvp.models.MvpPlayerResult;
import app.redoge.mvp.models.players.BasketballPlayer;
import app.redoge.mvp.models.players.HandballPlayer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static app.redoge.mvp.util.MVPFinder.findMVP;
import static app.redoge.mvp.util.MVPFinder.getTopList;
import static app.redoge.mvp.util.WinnerFinder.addBonusScore;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MVPFinderTest {

    List<Game> games = getCorrectGames();

    MVPFinderTest() throws PlayerIsExistException {
    }


    @Test
    void findMVPTestCorrect() {
        String expected = "nick4";
        String actual = findMVP(games).getName();
        assertEquals(expected, actual, String.format("%s is not %s",expected, actual));
    }


    @Test
    void getTopListTest() {
        assertEquals(List.of(
                new MvpPlayerResult("nick4", 100).toString(),
                new MvpPlayerResult("nick3", 84).toString(),
                new MvpPlayerResult("nick1", 47).toString(),
                new MvpPlayerResult("nick2", 0).toString()
        ).toString(), getTopList(games).stream().map(MvpPlayerResult::toString).collect(Collectors.toList()).toString());
    }

    @Test
    void findMVPTestIncorrect() {
        assertThrows(PlayerIsExistException.class, this::getIncorrectGames);
    }


    public List<Game> getCorrectGames() throws PlayerIsExistException {
        Game basketball = new Game(GameNames.BASKETBALL.name());
        Game handball = new Game(GameNames.HANDBALL.name());
        BasketballPlayer bp1 = new BasketballPlayer("player1", "nick1", 1, "A",
                10,2,7);
        BasketballPlayer bp2 = new BasketballPlayer("player2", "nick2", 2, "A",
                0,10,0);
        BasketballPlayer bp3 = new BasketballPlayer("player3", "nick3", 3, "B",
                15,10,4);
        BasketballPlayer bp4 = new BasketballPlayer("player4", "nick4", 4, "B",
                20,0,0);

        HandballPlayer hp1 = new HandballPlayer("player1", "nick1", 1, "A",
                10,2);
        HandballPlayer hp2 = new HandballPlayer("player2", "nick2", 2, "A",
                0,10);
        HandballPlayer hp3 = new HandballPlayer("player3", "nick3", 3, "B",
                15,10);
        HandballPlayer hp4 = new HandballPlayer("player4", "nick4", 4, "B",
                20,0);

        bp1.countScore();
        bp2.countScore();
        bp3.countScore();
        bp4.countScore();
        hp1.countScore();
        hp2.countScore();
        hp3.countScore();
        hp4.countScore();

        basketball.setAllPlayers(List.of(bp1, bp2, bp3, bp4));
        addBonusScore(basketball);

        handball.setAllPlayers(List.of(hp1, hp2, hp3, hp4));
        addBonusScore(handball);

        return List.of(basketball, handball);
    }
    public List<Game> getIncorrectGames() throws PlayerIsExistException {
        Game basketball = new Game(GameNames.BASKETBALL.name());
        Game handball = new Game(GameNames.HANDBALL.name());
        BasketballPlayer bp1 = new BasketballPlayer("player1", "nick1", 1, "A",
                10,2,7);
        BasketballPlayer bp2 = new BasketballPlayer("player1", "nick1", 1, "B",
                0,10,0);

        HandballPlayer hp1 = new HandballPlayer("player1", "nick1", 1, "A",
                10,2);
        HandballPlayer hp2 = new HandballPlayer("player2", "nick2", 2, "B",
                0,10);

        bp1.countScore();
        bp2.countScore();
        hp1.countScore();
        hp2.countScore();
        basketball.setAllPlayers(List.of(bp1, bp2));
        addBonusScore(basketball);
        handball.setAllPlayers(List.of(hp1, hp2));
        addBonusScore(basketball);
        return List.of(basketball, handball);
    }
}