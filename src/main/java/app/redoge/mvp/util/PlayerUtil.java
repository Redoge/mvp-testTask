package app.redoge.mvp.util;

import app.redoge.mvp.enums.GameNames;
import app.redoge.mvp.exceptions.FileWasCorruptedExeption;
import app.redoge.mvp.exceptions.GameNotFoundException;
import app.redoge.mvp.exceptions.PlayerIsExistException;
import app.redoge.mvp.models.players.BasketballPlayer;
import app.redoge.mvp.models.Game;
import app.redoge.mvp.models.players.HandballPlayer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static app.redoge.mvp.util.WinnerFinder.addBonusScore;
public class PlayerUtil {
    /**
     * Adds players to a given game based on the game name.
     * @param players List of player names
     * @param game Game object
     * @param gameName Name of the game
     * @throws IllegalArgumentException If the game structure is invalid
     * @throws PlayerIsExistException If the player already exists in the game
     * @throws FileWasCorruptedExeption If the file structure is broken
     * @throws GameNotFoundException If the game is not found
     */
    protected static void addPlayersToGame(List<String> players, Game game, String gameName) throws IllegalArgumentException, PlayerIsExistException, FileWasCorruptedExeption {
        GameNames gameNames;
        try{
            gameNames = GameNames.valueOf(gameName.toUpperCase());
        }catch (IllegalArgumentException e){
             throw new GameNotFoundException(String.format("Game '%s' not found", gameName));
        }
        try{
            if(gameNames.equals(GameNames.BASKETBALL)) new BasketballPlayer().addPlayersToGame(players, game);
            if (gameNames.equals(GameNames.HANDBALL)) new HandballPlayer().addPlayersToGame(players, game);


            addBonusScore(game);
        }catch (IllegalArgumentException | IndexOutOfBoundsException  e){
            throw new FileWasCorruptedExeption("The file structure is broken!!!");
        }
    }
}
