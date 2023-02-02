package app.redoge.mvp.util;

import app.redoge.mvp.models.Game;
import app.redoge.mvp.models.MvpPlayerResult;
import com.jakewharton.fliptables.FlipTable;

import java.util.List;

import static app.redoge.mvp.util.MVPFinder.getTopList;

public class PlayerPrinter {
    /**
     * Prints the top list of players.
     * @param games List of Game objects
     */
    public static void printTopList(List<Game> games){
        String[] headers = { "Nickname", "Score" };
        List<MvpPlayerResult> topList = getTopList(games);
        String[][] data = new String[topList.size()][2];
        for(int i = 0; i < topList.size(); i++){
            data[i][0] = topList.get(i).getName();
            data[i][1] = String.valueOf(topList.get(i).getScore());
        }
        System.out.println("\nPlayer Top list:\n"+FlipTable.of(headers, data));
    }
    /**
     * Prints the MVP player found from the given list of games.
     * @param games List of Game objects
     */
    public static void printMvpPlayer(List<Game> games){
        MvpPlayerResult mvpPlayerResult = MVPFinder.findMVP(games);
        printMvpPlayer(mvpPlayerResult);
    }
    /**
     * Prints the given MVP player result.
     * @param player MVP player result
     */
    public static void printMvpPlayer(MvpPlayerResult player){
        System.out.printf("MVP is: %s - %s points \n", player.getName(), player.getScore());
    }
}
