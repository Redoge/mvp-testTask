/**
 * HandballPlayer class represents a handball player and it extends AbstractPlayer.
 * This class contains all the information required for a handball player and it 
 * implements the countScore method which is required to calculate the score of a 
 * handball player. 
 */
package app.redoge.mvp.models.players;

import app.redoge.mvp.exceptions.FileWasCorruptedExeption;
import app.redoge.mvp.exceptions.PlayerIsExistException;
import app.redoge.mvp.models.Game;
import app.redoge.mvp.models.abstracts.AbstractPlayer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static app.redoge.mvp.util.WinnerFinder.addBonusScore;


public class HandballPlayer extends AbstractPlayer {
    /**
     * The goals made by the handball player
     */
    private int goalsMade;
    /**
     * The goals received by the handball player
     */
    private int goalsReceived;

    /**
     * Default constructor for HandballPlayer. It calls the super class constructor
     * without any parameters.
     */
    public HandballPlayer() {
        super();
    }

    /**
     * Parameterized constructor for HandballPlayer. It calls the super class constructor
     * with the given parameters and sets the goalsMade and goalsReceived fields with
     * the given parameters.
     *
     * @param playerName Name of the player
     * @param nickname Nickname of the player
     * @param number Jersey number of the player
     * @param teamName Name of the team the player plays for
     * @param goalsMade The number of goals made by the player
     * @param goalsReceived The number of goals received by the player
     */
    public HandballPlayer(String playerName, String nickname, int number, String teamName, int goalsMade, int goalsReceived) {
        super(playerName, nickname, number, teamName);
        this.goalsMade = goalsMade;
        this.goalsReceived = goalsReceived;
    }
    /**
     * Getter for the goalsMade field.
     *
     * @return The number of goals made by the player
     */
    public int getGoalsMade() {
        return goalsMade;
    }

    /**
     * Setter for the goalsMade field.
     *
     * @param goalsMade The number of goals made by the player
     */
    public void setGoalsMade(int goalsMade) {
        this.goalsMade = goalsMade;
    }

    /**
     * Getter for the goalsReceived field.
     *
     * @return The number of goals received by the player
     */
    public int getGoalsReceived() {
        return goalsReceived;
    }

    /**
     * Setter for the goalsReceived field.
     *
     * @param goalsReceived The number of goals received by the player
     */
    public void setGoalsReceived(int goalsReceived) {
        this.goalsReceived = goalsReceived;
    }

    /**
     * Implements the countScore method from the AbstractPlayer class.
     * This method calculates the score of the player based on the goals made
     * and goals received.
     */
    @Override
    public void countScore() {
        setScore(goalsMade * 2 - goalsReceived);
    }
    /**
     * Adds a list of players to a game.
     *
     * @param players a list of players represented as strings
     * @param game the game to add the players to
     * @throws FileWasCorruptedExeption if the format of the player string is incorrect (not 6 semicolon-separated values)
     * @throws PlayerIsExistException if a player with the same name already exists in the game
     */
    @Override
    public void addPlayersToGame(List<String> players, Game game) throws FileWasCorruptedExeption, PlayerIsExistException {
        for(String player : players){
            List<String> params = Arrays.stream(player.split(";")).collect(Collectors.toList());
            if(params.size()!=6) throw new FileWasCorruptedExeption(game.getName() + " structure invalid");
            HandballPlayer handballPlayer = new HandballPlayer(params.get(0), params.get(1),
                    Integer.parseInt(params.get(2)),params.get(3), Integer.parseInt(params.get(4)), Integer.parseInt(params.get(5)));
            handballPlayer.countScore();
            game.addPlayer(handballPlayer);
        }
    }
}