## 1. How to specify the path to the files?
You need to specify the path to the file in the "_resources/filesPath.properties_" in the _path_ parameter (\ raplace 
to \\). For example: 

_filesPath.properties_
```properties 
path = D:\\test
```
or enter path in command line:
```bash
java -jar .\target\mvp-1.0-SNAPSHOT.jar D:\test
```
## 2. How to add a new game type?
1. Add the sport to app.redoge.mvp.enums.GameNames.
```java
public enum GameNames {
    BASKETBALL, HANDBALL, FOOTBALL, ...
}
```
2. Create a new class in app.redoge.mvp.models.players
that extends app.redoge.mvp.models.abstracts.AbstractPlayer. 
There you need to add the fields needed for scoring and override the countScore() method.
```java

public class FootballPlayer extends AbstractPlayer {
    private int goalsMade;
    private int goalsReceived;
    public FootballPlayer() {
        super();
    }

    public FootballPlayer(String playerName, String nickname, int number, String teamName) {
        super(playerName, nickname, number, teamName);
    }
    public FootballPlayer(String playerName, String nickname, int number, String teamName, int goalsMade, int goalsReceived) {
        super(playerName, nickname, number, teamName);
        this.goalsMade = goalsMade;
        this.goalsReceived = goalsReceived;
    }
    public int getGoalsMade() {
        return goalsMade;
    }

    public void setGoalsMade(int goalsMade) {
        this.goalsMade = goalsMade;
    }

    public int getGoalsReceived() {
        return goalsReceived;
    }

    public void setGoalsReceived(int goalsReceived) {
        this.goalsReceived = goalsReceived;
    }
    @Override
    public void countScore() {
        setScore(goalsMade * 2 - goalsReceived);
    }
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
```
3. In the app.redoge.mvp.util.PlayerUtil class, in the addPlayersToGame() method, 
add an if that will call the method that parses the player of the new game.

...
```java
if(gameNames.equals(GameNames.BASKETBALL)) new BasketballPlayer().addPlayersToGame(players, game);
if (gameNames.equals(GameNames.HANDBALL)) new HandballPlayer().addPlayersToGame(players, game);
if (gameNames.equals(GameNames.FOOTBALL)) new FootballPlayer().addPlayersToGame(players, game);
```
