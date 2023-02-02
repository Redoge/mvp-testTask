package app.redoge.mvp.models.abstracts;

import app.redoge.mvp.exceptions.FileWasCorruptedExeption;
import app.redoge.mvp.exceptions.PlayerIsExistException;
import app.redoge.mvp.models.Game;

import java.util.List;

/**
 * Abstract class for a player.
 * This class provides basic information and functionality
 * for a player including player name, nickname, team name,
 * number, score, and abstract method to count score.
 */
public abstract class AbstractPlayer {

    /**
     * Default constructor
     */
    public AbstractPlayer() {
    }

    /**
     * Constructor with parameters for initializing player's information
     * @param playerName name of the player
     * @param nickname nickname of the player
     * @param number number of the player
     * @param teamName name of the team the player is playing for
     */
    public AbstractPlayer(String playerName, String nickname, int number, String teamName) {
        this.playerName = playerName;
        this.nickname = nickname;
        this.number = number;
        this.teamName = teamName;
    }

    private String playerName;
    private String nickname;
    private int number;
    private String teamName;

    private int score;

    /**
     * Getter for player name
     * @return name of the player
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Setter for player name
     * @param playerName name of the player
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Getter for player's nickname
     * @return nickname of the player
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Setter for player's nickname
     * @param nickname nickname of the player
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Getter for player's number
     * @return number of the player
     */
    public int getNumber() {
        return number;
    }

    /**
     * Setter for player's number
     * @param number number of the player
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Getter for player's team name
     * @return name of the team the player is playing for
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Setter for player's team name
     * @param teamName name of the team the player is playing for
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * Setter for player's score
     * @param score score of the player
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Method to add score to player's score
     * @param score score to be added to the player's score
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * Method to award 10 points to player's score for being the winner
     */
    public void isWinner(){
        score += 10;
    }
    /**
     * Getter for player's score
     * @return score of the player
     */
    public int getScore() {
        return score;
    }


    /**
     * The method countScore is an abstract method and must be overridden by subclasses.
     * It is responsible for counting the score of a player.
     */
    public abstract void countScore();
    /**
     * The method countScore is an abstract method and must be overridden by subclasses.
     */
    public abstract void addPlayersToGame(List<String> players, Game game) throws FileWasCorruptedExeption, PlayerIsExistException;

    @Override
    public String toString() {
        return "AbstractPlayer{" +
                "playerName='" + playerName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", number=" + number +
                ", teamName='" + teamName + '\'' +
                ", score=" + score +
                '}';
    }
}
