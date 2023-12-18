# Components
High level view of the components, there may be multiple subcomponents to any of them

## Representation tier

* User application(s)
* Web Server components

## Application Tier
* AUTHENTICATION MANAGER (or user manager)
* Tournament Manager(create, view tourList, view context BattleList,add Educator)
* Battle Manager(join , gw for manual eval)
* Repository Manager(repo creation, source pull)
* GroupManager (for student group creation)
* LeaderBoard Manager(view leaderboards)
* NotificationManager (interfaces w/ the EmailService to send notication to student and educators)
* SourceEvalManager (may have both automatic and manual eval functionality)

  for tourn. and battle manager there may be 2 distinct components or sub-components specifically for educator and student
  
## Data tier

* Database

## Externa actors or services interfaced w/ system

* Github
* Email service


# Interfaces

## app tier 

* AuthenticationControll Interface (from: Authentication manager -> to: TournamentM, BattleM ) to allow components to check if a user is authorized to do some action
* LoginInterface(from AuthManager -> reprTier) allow user to sign in or login
* TournamentManagerInterface (from TManager -> to RapprTier) allow educator to tournament creation, closing, adding educator | student to subscribe to subscribe | all user to view tournament list, battle list of a tournament
* BattleManagerInt( form BManager -> to RapprTier) allow educator to create battle, manually evaluate | student to join |
* GroupManagerInterface (from GManager -> BattleManager) allow creation of groups by invitation ------------------UNLESS groupM doesnt need one as part of BattleManager
* NotificationMangerInterface ( from NotManager -> BManager, Tmanager, GroupM) for sending mails related to |Tourn: New tournaments, new battles|Battle: battleClosed, ManualEvalReq!RepoM: repository link|GroupM: group invitation
* LeaderBoardManagerInterface (From: LBManger -> RapprTier) allow all users to view Leaderboard of a tournament or battle
* RepoManaegerInterface(from RepoManager->SourceEval, battleManager) allows source eval to get sources from a repository, battleToCreate a repository
* SourceEvalInterf(from EvalManager->BattleManager) FORSE NO, DIPENDE SE L'UTENTE DEVE PASSARE DA BATTLE MANAGER PER FARE VAL MANUALE

## Data tier
dbms interface(s), un solo db?

----Functionalities offered: 
* Create Tournament
* * login,
* registration,
*  login,
*  authentication(non functional),
*   close tournament,
*    add educator,
*     tournm.subcritpion,
*  view tournList
*  ,viewBattle list of tourn.
*  create battle
*  manual evaluate
*  join battle
*  create group
*  notification (new tourn, new battle, battle closed, repolink, manual eval, group invitation
* view leaderboard(both)
* Automatic evaluation
