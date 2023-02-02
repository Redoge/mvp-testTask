package app.redoge.mvp.models;

/**
 * The MvpPlayerResult class stores information about a player and their score.
 */
public class MvpPlayerResult {
    private String name;
    private int score;

    /**
     * Constructor that sets the player's name and score.
     * @param name the player's name
     * @param score the player's score
     */
    public MvpPlayerResult(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Default constructor for the MvpPlayerResult class.
     */
    public MvpPlayerResult() {
    }

    /**
     * Returns the player's name.
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the player's name.
     * @param name the player's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the player's score.
     * @return the player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the player's score.
     * @param score the player's score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Returns a string representation of the MvpPlayerResult object.
     * @return a string representation of the MvpPlayerResult object
     */
    @Override
    public String toString() {
        return "MvpPlayerResult{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
