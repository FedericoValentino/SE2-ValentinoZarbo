# Fede Interfaces


| Interface | Methods |
| --------- | ------- |
| NotificationRead | * readNewTournaments(): Message: String<br> * readFinalTournamentRank(): Message: String<br> * readNewBattle(): Message: String<br> * readFinalBattleRank(): Message: String<br> * readInvitation(): Message: String<br> |
| TournamentTopicWrite | * writeNewTournament(Message: String)<br> * writeFinalTournamentRank(Message: String) <br> |
| BattleTopicWrite | * writeNewBattle(Message: String)<br> * writeFinalBattleRank(Message: String) <br> |
| ScoringTopicWrite | * writeNewScore(Message: String)<br> |
| ScoringTopicRead | * readNewScore(): Message: String<br> |
| InvitationsTopicWrite | * writeNewGroup(Message: String) <br>|
| NewCommitsTopicWrite | * writeNewEvalRequired(Message: String) <br> |


All the messages exchanged are JSON Strings, containing for every topic different stuff:

| TOPIC | JSON Contents |
| ----- | ------------- |
| Tournaments | boolean newTournamentHasStarted, boolean TournamentHasEnded |
| Battles | boolean newBattleHasStarted, boolean BattleHasEnded |
| Invitations | int type, int groupID, list[] userID |
| Commits | boolean newCommit, int groupID |
| Scores | int groupID, int newScore |
| Repository | boolean newRepo |
| RepoLinks | int BattleID, String linkToRepo |

