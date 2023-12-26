# INterfaces
## Nico

### Rest APIs
**todo** openAPI of the interfaces
- [ ] TournamentApi
- [ ] LeaderBoardApi
- [ ] BattleApi
- [ ] ManualEvalApi
- [ ] UserAPI

### Dbms Interfaces

- [ ] DataAccessInterface
- [ ] SourceAccessInterface

### other

- [ ] WebInterface//api user interface( simply exposes the rest APIs)

## Dmbs api
### DataAccessInterface
Exposed by Data dbms, used by Leaderboard, tournament, battle, userService(???)  

* getSubscribedStudents(String : IDT): list[string studentID]

#### used by leaderBoardService
* getScoresOfTournament(String : IDT): Map[string: studenID; int: score]
* setScoresUserTournament(String:IDStud, String : IDT, int :score): void
* setScoresGroupBattle(String:IDGroup, String : IDB, int :score): void
* getScoresOfBattle(String: IDB): Map[ string: groupID, int: score]

#### used by tournamentService
* getCurrentTournamtn(): list[ string :tournamentID]
* getBattlesOfTourn(String : IDT):list[ string :battleID]
* checkEducatorPermission(String : IDT, String: IDEDU ): boolean response

#### used by battleService
* getDeadlinesBattle(String: IDB): tuple(subsDL; submDL)
* getGroupRules()
* getAssignment()
*
#### used by userService

* addStudent(String: UserName): void
* addEducator(String UserName): void

### SOURCEAccessInterface
Exposed by SOURCE dbms, used by ManualEval, SoftwareEval, GithubHandler  

* getGroupSource(String: groupID, String: battleID): string SourceCodetxt
* saveGroupSource(String: groupID, String: battleID, String: SourceCodetxt): void

## Restfull Api
These are all external api's exposed by the API GATEWAY and used by the client application. They follow rest principle, each service handles some resources revolving around a particular aspect of the application ie: battles, tournaments, ecc.  
Here follows a three of the uri path tree of the exposed resources:  
 

 (treeURI png todo)

### UserAPI

* **/user/create**  
registerUser(StringU:UserName, String : UserType):void
* **/user/login**  
login(String : psw, String : UserID):void

### TournamentApi
* **/tournament/**  
getCurrentTournament(String: UserId,String: UserType) : list< Tournament>  
* **/tournament/create**  
createTournament(String: UserId,String: UserType, String : TournamentName): void  
* **/tournament/{idT}/addCollaborator**   
addCollaborator(String: UserId,String: UserType, String : CollaboratorID):void  
* **/tournament/{idT}/close**  
closeTournament(String: UserId,String: UserType, String : TournamentID): void 
*  **/tournament/{idT}/subscribe**  
subscribeTournament(String: UserId,String: UserType, String : TournamentID) : void
* **/tournament/{idT}/battle/**  
getTournamentsBattles(String: UserId,String: UserType, String : TournamentID): List< Battle >  


### BattleAPI
* **/tournament/{idT}/battle/{idB}/rules**  
 getGroupRules(String: UserId, String : BattleID): Tuple (int :MaxSize, int: MinSize)  
* **/tournament/{idT}/battle/{idB}/assignement**  
 getAssignemntText(String: UserId, String : BattleId): String AssignemtnText  
* **/tournament/{idT}/battle/{idB}/deadlines**  
 getDeadlines(String: UserId, String : BattleID):Tuple (int :SubscriptionDL, int: SubmissionDL)  
* **/tournament/{idT}/battle/{idB}/join**  
joinBattle( String: UserId, String : BattleID, String: UserType, OPTIONAL List< StudentID> ): void
  


### LeaderBoardApi

* **/tournament/{idT}/lbt**  
getLeaderBoardTournament(): LeaderBoard
* **/tournament/{idT}/battle/{idB}/lbb**  
getLeaderBoardBattle():Leaderboard  


### Manual EvaluationAPI

* **/tournament/{idT}/battle/evalSource**  
getSourcesForEval(String: UserId, String : BattleID, String: UserType ): String SourceCode
* **/tournament/{idT}/battle/evalSource/score**  
addManualScore(String: UserId, String : BattleID, String: UserType, int : score)
: void