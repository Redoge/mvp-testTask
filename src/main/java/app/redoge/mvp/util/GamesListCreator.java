package app.redoge.mvp.util;

import app.redoge.mvp.exceptions.FileWasCorruptedExeption;
import app.redoge.mvp.exceptions.PlayerIsExistException;
import app.redoge.mvp.models.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GamesListCreator {
    /**
     * Gets a list of Game objects based on a list of file links.
     *
     * @param links the list of file links of the game files to read
     * @return a list of Game objects representing the data in the game files
     * @throws FileWasCorruptedExeption if any of the files has an invalid structure or cannot be read
     */
    public static List<Game> getGamesByGameFileLinks(List<String> links) throws FileWasCorruptedExeption {
        List<Game> game = new ArrayList<>();
        try{
            for(String link : links){
                game.add(getGameFromFileByLink(link));
            }
        }catch (IOException | FileWasCorruptedExeption | PlayerIsExistException e){
            throw new FileWasCorruptedExeption(e.getMessage());
        }
        return game;
    }
    private static Game getGameFromFileByLink(String link) throws IOException, FileWasCorruptedExeption, PlayerIsExistException {
        return GameParser.readGame(link);
    }
}
