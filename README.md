## Tennis Score

To run the code first build the jar using mvn command (To save time I have added one in git repo)

```
mvn package 
```

This will build the jar under target folder.
Then to run the code type in below command

```
java -cp target/tennis-1.0-SNAPSHOT.jar com.dius.tennis.events.PlayEvent
```
This will open command prompt. 
To Score as player 1 type `1`, and to score as player 2 type `2`.

Score Card is published at every step.

To quit type `q`.