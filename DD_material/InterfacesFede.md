# Fede Interfaces


| Interface | Methods |
| --------- | ------- |
| NotificationRead | * read(Topic: int): Message: String - TournamentTopic<br> * read(Topic: int): Message: String - Battles Topic<br> * read(Topic: int): Message: String - Invitations Topic<br> * read(Topic: int): Message: String - Repository<br> * read(Topic: int): Message: String - RepoLinks<br>  |
| TournamentTopicWrite | * write(Topic: int, Message: String) - Tournament Topic |
| BattleTopicWrite | * write(Topic: int, Message: String) - Battles Topic |
| ScoringTopicWrite | * write(Topic: int, Message: String) - Scoring Topic|
| ScoringTopicRead | * read(Topic: int): Message: String - Scoring Topic|
| InvitationsTopicWrite | * write(Topic: int, Message: String) - Invitations Topic |
| NewCommitsTopicWrite | * write(Topic: int, Message: String) - Commits Topic |


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

