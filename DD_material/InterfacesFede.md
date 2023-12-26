# Fede Interfaces


| Interface | Methods |
| --------- | ------- |
| NotificationRead | * readNewTournament(): Message: String<br> * readFinalTournamentRank(): Message: String<br> * readNewBattle(): Message: String<br> * readFinalBattleRank(): Message: String<br> * readInvitation(): Message: String<br> |
| TournamentTopicWrite | * writeNewTournament(Message: String)<br> * writeFinalTournamentRank(Message: String) <br> |
| BattleTopicWrite | * writeNewBattle(Message: String)<br> * writeFinalBattleRank(Message: String) <br> |
| ScoringTopicWrite | * writeNewScore(Message: String)<br> |
| ScoringTopicRead | * readNewScore(): Message: String<br> |
| InvitationsTopicWrite | * writeNewGroup(Message: String) <br>|
| NewCommitsTopicWrite | * writeNewEvalRequired(Message: String) <br> |


