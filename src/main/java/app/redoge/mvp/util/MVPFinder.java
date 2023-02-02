package app.redoge.mvp.util;

import app.redoge.mvp.models.Game;
import app.redoge.mvp.models.MvpPlayerResult;
import app.redoge.mvp.models.abstracts.AbstractPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MVPFinder {
    /**
     * Returns the MVP player result found from the given list of games.
     * @param games List of Game objects
     * @return MVP player result
     */
    public static MvpPlayerResult findMVP(List<Game> games){
        return findMaxScoreUsername(getAllPlayers(games));
    }

    private static Map<String, Integer> getAllPlayers(List<Game> games){
        Map<String, Integer> players = new HashMap<>();
        for(Game game: games){
            for(AbstractPlayer player: game.getPlayers()){
                players.merge(player.getNickname(), player.getScore(), Integer::sum);
            }
        }
        return players;
    }

    private static MvpPlayerResult findMaxScoreUsername(Map<String, Integer> users){
        String result = "";
        int max = Integer.MIN_VALUE;
        for(String str: users.keySet()){
            if(users.get(str)>max) {
                result = str;
                max = users.get(str);
                ;}
        }
        return new MvpPlayerResult(result, max);
    }
    /**
     * Returns the list of MVP player results sorted in descending order of their scores.
     * @param games List of Game objects
     * @return List of MVP player results
     */
    public static List<MvpPlayerResult> getTopList(List<Game> games){
        List<MvpPlayerResult> users = mapGamesToMvpPlayerResult(games);
        return users.stream().sorted((t, t1)->(t.getScore()>t1.getScore())?-1:1).collect(Collectors.toList());
    }
    /**
     * Maps the given list of games to the list of MVP player results.
     * @param games List of Game objects
     * @return List of MVP player results
     */
    private static List<MvpPlayerResult> mapGamesToMvpPlayerResult(List<Game> games){
        Map<String, Integer> users = getAllPlayers(games);
        return users.keySet().stream().map(t->new MvpPlayerResult(t, users.get(t))).collect(Collectors.toList());
    }

}
