///TODO
//fare predicati per stati particolari ( torneo chiuso, battaglia chiusa ecc)
//mettere controlli sui valori dei score(score torneo = somma score battaglie completate)??
//ordinare

///



open util/integer


abstract sig User{
}

sig DateTime{	}{this in Rules.submissionDeadline and this in Rules.subscriptionDeadline}

sig Educator extends User{
	tournamentsInvolved : set Tournament
}

sig Student extends User{
	tournamentRank : disj set RankTournament,
	subscribedTournaments : set Tournament,
}

sig Group{
	students : some Student,
	battleRank: disj one RankBattle 
}{this in Battle.currentGroups}

sig Tournament{
	battleList : disj some Battle,
	leaderBoard : disj one LeaderboardTournament
}{this in Educator.tournamentsInvolved }

sig Battle{
	currentGroups : disj set Group,
	assignment : disj one CodeKata,
	rules :disj one Rules,
	repo : disj lone Repository,
	leaderBoard: disj one LeaderboardBattle ,
    battleStatus : one BattleStatus
}{this in Tournament.battleList}

abstract sig TournamentStatus{}
one sig OpenTournament extends TournamentStatus{}
one sig ClosedTournament extends TournamentStatus{}

abstract sig BattleStatus{}
one sig SubscriptionOpen extends BattleStatus{} //no ranking no repo in battle, 
one sig SubmissionOpen extends BattleStatus{} 
one sig ClosedClosed extends BattleStatus{} // 


sig LeaderboardBattle{
	positions : disj set RankBattle
}{this in Battle.leaderBoard}

sig LeaderboardTournament{
	positions : disj set RankTournament
}{this in Tournament.leaderBoard}

abstract sig Score{}
sig StudentScore extends Score{}{this in RankTournament.studentScore}
sig GroupScore extends Score{}{this in RankBattle.battleScore}

abstract sig Rank{
}

sig RankTournament extends Rank{
    studentScore : disj one StudentScore
}{this in LeaderboardTournament.positions and this in Student.tournamentRank}

sig RankBattle extends Rank{
    battleScore : disj lone GroupScore
}{this in LeaderboardBattle.positions and this in Group.battleRank}

sig Repository{}{this in Battle.repo}

sig CodeKata{
	testCases : disj one TestCase
}{this in Battle.assignment}

sig TestCase{}{this in CodeKata.testCases}

sig Rules{
	minSize : Int,
	maxSize : Int,
	subscriptionDeadline :one DateTime,
	submissionDeadline:one DateTime
}{this in Battle.rules}

///---------------------------------------------FACTS---------------------------------------

fact noRepoInSubscriptionPhase{
    all b : Battle | b.battleStatus=SubscriptionOpen iff (no b.repo)
}


fact minMaxSize{
	all r :Rules | (int r.minSize>=1 and int r.minSize<=int r.maxSize) 
}
fact respectSizeRule{
	all b : Battle | 
		(all g : Group | 
			(g in b.currentGroups implies ( int #g.students>=int b.rules.minSize and int #g.students<=int b.rules.maxSize) )
		)
}

fact studentInOneGroupForBattle{
	all battle : Battle | (
		all disj g1,g2 : Group |( g1 in battle.currentGroups and g2 in battle.currentGroups implies (
			no s : Student | s in g1.students and s in g2.students)
		) 

	)
//	no disj g1, g2 : Group | 
	//		g1 in battle.currentGroups and g2 in battle.currentGroups and (
		//		some s : Student | s in g1.students and s in g2.students
			//)
}

fact rankAssociatedToBattleLeaderboard{
	all b : Battle | (
		all g : Group | g in b.currentGroups iff (g.battleRank in b.leaderBoard.positions)
		)
}

fact noTwoRanksForSameTournament{
	all t : Tournament | (
		all st : Student | t in st.subscribedTournaments iff (
			one rank : RankTournament | rank in st.tournamentRank and rank in t.leaderBoard.positions
		)
	)
}

fact ifInBattleThenInTournament{
	all t : Tournament | (
		all b : Battle | (
			all g : Group | (
				all st: Student | (g in b.currentGroups and st in g.students and b in t.battleList) implies t in st.subscribedTournaments
			)
		)
	)
}

///---------------------------------------------------PREDICATES--------------------------------
pred show{
#Student > 5
#Tournament > 1
#Group > 1
#Tournament.battleList > 1
#Battle.currentGroups > 1
Rules.maxSize<5
}

pred showBattleInSubscriptionStatus{
one b : Battle | (b.battleStatus = SubscriptionOpen)
}
pred checkNewVer{
#Student > 5
#Tournament > 1
#Group > 1
#Tournament.battleList > 1
#Battle.currentGroups > 1
Rules.maxSize<5
Rules.minSize=2
all b : Battle |(
	no disj g1, g2 : Group | (	g1 in b.currentGroups and g2 in b.currentGroups) and (
				some s : Student | s in g1.students and s in g2.students)
)
}
pred noGroupBattle{
//no e : Educator| no e.tournamentsInvolved
one Battle 
lone s:Student |  #s.subscribedTournaments>0
no Group


}

run noGroupBattle for 10

