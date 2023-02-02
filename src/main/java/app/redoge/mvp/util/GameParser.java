package app.redoge.mvp.util;

import app.redoge.mvp.exceptions.FileWasCorruptedExeption;
import app.redoge.mvp.exceptions.PlayerIsExistException;
import app.redoge.mvp.models.Game;
import java.io.*;
import java.util.*;


public class GameParser {
    /**
     * Reads the contents of a game file and returns a Game object.
     *
     * @param link the file path of the game file to read
     * @return a Game object representing the data in the file
     * @throws IOException if an error occurs while reading the file
     * @throws FileWasCorruptedExeption if the file structure is invalid
     * @throws PlayerIsExistException if a player already exists in the game
     */
    protected static Game readGame(String link) throws IOException, FileWasCorruptedExeption, PlayerIsExistException {
        List<String> lines = FileReader.readFile(link);
        return getGame(lines);
    }

    private static Game getGame(List<String> lines) throws PlayerIsExistException, FileWasCorruptedExeption {
        String gameName = lines.get(0);
        lines.remove(0);
        return createGameByName(lines, gameName);
    }

    private static Game createGameByName(List<String> players, String gameName) throws PlayerIsExistException, FileWasCorruptedExeption {
        Game game = new Game(gameName);
        PlayerUtil.addPlayersToGame(players, game, gameName);
        return game;
    }
}
