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
### Data AccessInterface
Exposed by Data dbms, used by Leaderboard, tournament, battle,group, userService(???)  

* getSubscribedStudents(String : IDT): list[string studentID]

#### used by leaderBoardService
* getScoresOfTournament(String : IDT): Map[string: studenID; int: score]
* setScoresUserTournament(String:IDStud, String : IDT, int :score): void
* setScoresGroupBattle(String:IDGroup, String : IDB, int :score): void
* getScoresOfBattle(String: IDB): Map[ string: groupID, int: score]

#### used by notificationService(?????  maybe in another way by creating mailLists)
* getAllSignedStudent(): list[ string studID]
* getSubscribedStudent(string IDT): list[ string studID]
* getGroups(string IDB): map[string groupID; list[string studID]]
* getInvolvedEDUBattle(string IDB): list[ string eduID]

#### used by tournamentService
* addTournament(String : EduCreator):void
* grantBattleCreation(string : IDT, string : grantedEDU): void
* getCurrentTournamtn(): list[ string :tournamentID]
* getBattlesOfTourn(String : IDT):list[ string :battleID]
* checkEducatorPermission(String : IDT, String: IDEDU ): boolean response

#### used by battleService

* getDeadlinesBattle(String: IDB): tuple(subsDL; submDL)
* addBattle(String TournamentID, string assignment,  int submDL, int subsDL, int maxsize, int minsize): string IDB
* getBattleAssignement(string IDB):  string assignment
* getBattleGroupRules(string IDB):  tuple(int  maxsize,int  minsize)
* getBattleAssignement(string IDB):  string assignment
* getBattleDeadlines(string IDB):  tuple(string submDL,string  subsDL)


#### used by groupservice

* addGroup(list[string studID], String IDB): void
* get

#### used by userService

* addStudent(String: UserName): void
* addEducator(String UserName): void

### SOURCE AccessInterface
Exposed by SOURCE dbms, used by ManualEval, SoftwareEval, GithubHandler, battle  

* addBattleTestCases(string IDB,list[ string] testcase):void
* getGroupSource(String: groupID, String: battleID): string SourceCodetxt
* saveGroupSource(String: groupID, String: battleID, String: SourceCodetxt): void

## Restfull Api
These are all external api's exposed by the API GATEWAY and used by the client application. They follow rest principle, each service handles some resources revolving around a particular aspect of the application ie: battles, tournaments, ecc.  
Here follows a three of the uri path tree of the exposed resources:  
 

 (treeURI png todo)

### UserAPI

* **/user/register**  
registerUser(StringU:UserName, String : UserType):void
* **/user/login**  
login(String : psw, String : UserID):void

### TournamentApi
* **/tournament/**  
getCurrentTournament(String: UserId,String: UserType) : list< Tournament>  
* **/tournament/create_t**  
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
* **/tournament/{idT}/battle/create_b**    
createBattle(String: UserId,String: UserType, String : BattleName, tuple(int maxsize, int minsize): groupRule, string : assignemtent, tuple(date: subs, date: subm): deadline, list[ string]: testcases): void  
* **/tournament/{idT}/battle/{idB}/rules**  
 getGroupRules(String: UserId, String : BattleID): Tuple (int :MaxSize, int: MinSize)  
* **/tournament/{idT}/battle/{idB}/assignment**  
 getAssignemntText(String: UserId, String : BattleId): String AssignmentText  
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