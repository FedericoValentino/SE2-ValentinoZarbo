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
	students : set Student,
	battleScore : disj one Score,
	battleRank: disj one RankBattle
}{this in Battle.currentGroups}

sig Tournament{
	battleList : set Battle,
	leaderboard : one LeaderboardTournament
}{this in Educator.tournamentsInvolved and this in Student.subscribedTournaments}

sig Battle{
	currentGroups : disj set Group,
	assignment : disj one CodeKata,
	rules :disj one Rules,
	repo : disj one Repository,
	leaderBoard: disj one LeaderboardBattle
}

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

sig Rules{}{this in Battle.rules}

///---------------------------------------------FACTS---------------------------------------

fact studentInOneGroupForBattle{
	all battle : Battle | (
		no disj g1, g2 : Group | 
			g1 in battle.currentGroups and g2 in battle.currentGroups and (
				some s : Student | s in g1.students and s in g2.students
			)
	)
}


pred show{
#Student=3
#Battle=2
#Group=2
}

run show


