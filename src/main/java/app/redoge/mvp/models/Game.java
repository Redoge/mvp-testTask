package app.redoge.mvp.models;

import app.redoge.mvp.exceptions.PlayerIsExistException;
import app.redoge.mvp.models.abstracts.AbstractPlayer;

import java.util.ArrayList;
import java.util.List;

/**

 The Game class is a model that represents a game and its related players.
 */
public class Game {

    /**

     List of all players that participate in this game.
     */
    private List<AbstractPlayer> players = new ArrayList<>();
    /**

     The name of this game.
     */
    private String name;
    /**

     Initializes a new instance of the Game class.
     */
    public Game() {}
    /**

     Initializes a new instance of the Game class with the specified name.
     @param name The name of this game.
     */
    public Game(String name) {
        this.name = name;
    }
    /**

     Determines whether a player with the same nickname already exists in the list of players.
     @param player The player to check for duplicates.
     @return True if the player already exists in the list, false otherwise.
     */
    private boolean playerIsExist(AbstractPlayer player){
        return players.stream().anyMatch(p -> p.getNickname().equals(player.getNickname()));
    }
    /**

     Gets the list of all players that participate in this game.
     @return The list of players.
     */
    public List<AbstractPlayer> getPlayers() {
        return players;
    }
    /**

     Sets the list of all players that participate in this game.
     @param players The list of players.
     */
    public void setAllPlayers(List<AbstractPlayer> players) throws PlayerIsExistException {
        for(AbstractPlayer player : players){
            addPlayer(player);
        }
    }
    /**

     Adds a new player to the list of players.
     @param player The player to add.
     @throws PlayerIsExistException Thrown if a player with the same nickname already exists in the list.
     */
    public void addPlayer(AbstractPlayer player) throws PlayerIsExistException {
        if(playerIsExist(player)) throw new PlayerIsExistException(
                String.format("Player %s is exist", player.getNickname()));
        players.add(player);
    }
    /**

     Gets the name of this game.
     @return The name of this game.
     */
    public String getName() {
        return name;
    }
    /**

     Sets the name of this game.
     @param name The name of this game.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**

     Returns a string representation of the Game instance.
     */
    @Override
    public String toString() {
        return "Game{" +
                "name = '" + name + "' --> " +
                "players=" + players +
                '}';
    }
}