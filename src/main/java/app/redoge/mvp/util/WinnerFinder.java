package app.redoge.mvp.util;

import app.redoge.mvp.models.Game;
import app.redoge.mvp.models.abstracts.AbstractPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinnerFinder {
    private static String getWinnerTeamName(Game game){
        Map<String, Integer> teamWithScore = new HashMap<>();
        for(AbstractPlayer player : game.getPlayers()){
            String teamName = player.getTeamName();
            teamWithScore.merge(teamName, player.getScore(), Integer::sum);
        }
        return findWinnerTeamName(teamWithScore);
    }

    private static String findWinnerTeamName(Map<String, Integer> teamsWithScore){
        List<String> keys = List.copyOf(teamsWithScore.keySet());
        if(teamsWithScore.get(keys.get(0)) > teamsWithScore.get(keys.get(1))){
            return keys.get(0);
        }
        else return keys.get(1);
    }
    /**
     * Adds a bonus score to the players of the winning team of the given game.
     * @param game The game to add the bonus score to.
     */
    public static void addBonusScore(Game game){
        String teamName = getWinnerTeamName(game);
        for(AbstractPlayer player : game.getPlayers()){
            if(player.getTeamName().equals(teamName)) player.isWinner();
        }
    }
}
