package app.redoge.mvp.models.players;

import app.redoge.mvp.exceptions.FileWasCorruptedExeption;
import app.redoge.mvp.exceptions.PlayerIsExistException;
import app.redoge.mvp.models.Game;
import app.redoge.mvp.models.abstracts.AbstractPlayer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static app.redoge.mvp.util.WinnerFinder.addBonusScore;

/**
 * Class representing a basketball player, extends from the {@link AbstractPlayer} class
 * with additional properties such as scored points, rebounds, and assists.
 */
public class BasketballPlayer extends AbstractPlayer {
    /**
     * Number of points scored by the player.
     */
    private int scoredPoints;

    /**
     * Number of rebounds by the player.
     */
    private int rebounds;

    /**
     * Number of assists by the player.
     */
    private int assists;

    /**
     * Constructor that creates a new instance of the basketball player
     * with no arguments provided.
     */
    public BasketballPlayer() {
        super();
    }

    /**
     * Constructor that creates a new instance of the basketball player
     * with player name, nickname, number, team name, scored points, rebounds, and assists provided.
     *
     * @param playerName the name of the player
     * @param nickname the nickname of the player
     * @param number the number of the player
     * @param teamName the team name of the player
     * @param scoredPoints the number of points scored by the player
     * @param rebounds the number of rebounds by the player
     * @param assists the number of assists by the player
     */
    public BasketballPlayer(String playerName, String nickname, int number, String teamName, int scoredPoints, int rebounds, int assists) {
        super(playerName, nickname, number, teamName);
        this.scoredPoints = scoredPoints;
        this.rebounds = rebounds;
        this.assists = assists;
    }


    public int getScoredPoints() {
        return scoredPoints;
    }


    public void setScoredPoints(int scoredPoints) {
        this.scoredPoints = scoredPoints;
    }

    public int getRebounds() {
        return rebounds;
    }

    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }
    /**

     Calculates and sets the score of the player.
     The score is calculated as (scoredPoints * 2) + rebounds + assists.
     */
    @Override
    public void countScore() {
        setScore((scoredPoints * 2) + rebounds + assists);
    }
    /**
     * Adds a list of players to a game.
     *
     * @param players a list of players represented as strings
     * @param game the game to add the players to
     * @throws FileWasCorruptedExeption if the format of the player string is incorrect (not 7 semicolon-separated values)
     * @throws PlayerIsExistException if a player with the same name already exists in the game
     */
    @Override
    public void addPlayersToGame(List<String> players, Game game) throws FileWasCorruptedExeption, PlayerIsExistException {
        for(String player : players){
            List<String> params = Arrays.stream(player.split(";")).collect(Collectors.toList());
            if(params.size()!=7) throw new FileWasCorruptedExeption(game.getName() + " structure invalid");
            BasketballPlayer basketballPlayer = new BasketballPlayer(params.get(0), params.get(1),
                    Integer.parseInt(params.get(2)),params.get(3), Integer.parseInt(params.get(4)),
                    Integer.parseInt(params.get(5)), Integer.parseInt(params.get(6)));
            basketballPlayer.countScore();
            game.addPlayer(basketballPlayer);
        }
    }
}
