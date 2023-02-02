package app.redoge.mvp;


import app.redoge.mvp.exceptions.FileNotFoundException;
import app.redoge.mvp.exceptions.FileWasCorruptedExeption;
import app.redoge.mvp.models.Game;
import app.redoge.mvp.util.FileListExtractor;
import app.redoge.mvp.util.GamesListCreator;

import java.util.List;

import static app.redoge.mvp.util.PlayerPrinter.printMvpPlayer;


public class Main {
    public static void main(String[] args) {
        try {
            List<String> gamesFileLinks = args.length==0 ? FileListExtractor.getAllCsvFilesByPath() : FileListExtractor.getAllCsvFilesByPath(args[0]);
            List<Game> games = GamesListCreator.getGamesByGameFileLinks(gamesFileLinks);
            printMvpPlayer(games);
//            printTopList(games);
        }catch (FileWasCorruptedExeption | FileNotFoundException e){
            System.out.println("MVP not found -> " + e.getMessage());
        }
    }
}
