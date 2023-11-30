///TODO
///Mettere associazione tra score squadra e battaglia tramite RankBattle
///LeaderBoardBattle deve essere collegata per forza ad una battle
///
///Caratterizzare le rules con min e max groups
open util/integer
abstract sig User{
}

sig Educator extends User{
	tournamentsInvolved : set Tournament
}

sig Student extends User{
	tournamentRanks : disj set RankTournament,
	subscribedTournaments : set Tournament
}{this in Group.students}

sig Group{
	students : some Student,
	battleScore : disj one Score,
	battleRank: disj one RankBattle
}{this in Battle.currentGroups}

sig Tournament{
	battleList : disj some Battle,
	leaderBoard : disj one LeaderboardTournament
}{this in Educator.tournamentsInvolved and this in Student.subscribedTournaments}

sig Battle{
	currentGroups : disj set Group,
	assignment : disj one CodeKata,
	rules :disj one Rules,
	repo : disj one Repository,
	leaderBoard: disj one LeaderboardBattle
}{this in Tournament.battleList}

sig LeaderboardBattle{
	positions : disj set RankBattle
}

sig LeaderboardTournament{
	positions : disj set RankTournament
}

sig Score{}

abstract sig Rank{}

sig RankTournament extends Rank{}{this in LeaderboardTournament.positions and this in Student.tournamentRanks}

sig RankBattle extends Rank{}{this in LeaderboardBattle.positions}

sig Repository{}{this in Battle.repo}

sig CodeKata{
	testCases : disj some TestCase
}{this in Battle.assignment}

sig TestCase{}{this in CodeKata.testCases}

sig Rules{
	minSize : Int,
	maxSize : Int
}{this in Battle.rules}

///---------------------------------------------FACTS---------------------------------------

fact {
	all r :Rules | (int r.minSize>=1 and int r.minSize<=int r.maxSize) 
}

fact studentInOneGroupForBattle{
	all battle : Battle | (
		no disj g1, g2 : Group | 
			g1 in battle.currentGroups and g2 in battle.currentGroups and (
				some s : Student | s in g1.students and s in g2.students
			)
	)
}

fact rankAssociatedToBattleLeaderboard{
	all b : Battle | (
		all g : Group | g in b.currentGroups iff (g.battleRank in b.leaderBoard.positions)
		)
}

fact noTwoRanksForSameTournament{
	all t : Tournament | (
		all st : Student | t in st.subscribedTournaments iff (
			one rank : RankTournament | rank in st.tournamentRanks and rank in t.leaderBoard.positions
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
}

run show for 10



