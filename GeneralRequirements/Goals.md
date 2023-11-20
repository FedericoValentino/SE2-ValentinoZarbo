## Phenomena

### WORLD

* W1:	An educator wants to create a tournament to evaluate students performance

* W2:	Students fork the created repository for a battle on Github

* W3:	An educator creates the battle assignment

* W4:	Students write source code for the code kata battle

* W5:	Students create commits on Github

* W6:	Students decide to join a battle

* W7:	Students create groups for battles

* W8:	Students setup an automated workflow for the forked repository on Github

* W9: 	Students decide to join a tournament

* W10: 	Educator decides to close tournament

---------------------------------------------------------------------------------------------------------------

### SHARED

#### WORLD CONTROLLED

* SP1:	An educator fills out a tournament creation form

* SP2:	An educator uploads the details of a code kata battle(the assignment, the rules, the tests)

* SP3:	A group(maybe singleton) joins a battle respecting the rules regarding the min and max group size

* SP4:	An educator logs into the platform

* SP5: 	A student logs into the platform

* SP6:	The system requires additional manual evalution by an educator for a battle(if required by the rules) 
	#godown

* SP7:	The educator inserts an additional manual score for a battle

* SP8:	A student invites other students to a group to partecipate to a battle

* SP9:	Github on commit notifies the code kata platform

* SP10:	An educator registers an account on the platform

* SP11:	A student registers an account on the platform

* SP12:	A student subscribes to a tournament

* SP13: 	Students and educator look at the rank for a battle they are involved in

* SP14:	Students and educator look at the rank for a tournament

* sp15:	Educator closes tournament

* sp16: 	User looks at list of available tournament

* SP17:	A student accepts and invitet o a group to partecipate to a battle


---------------------------------------------------------------------------------------------------------------
#### MACHINE CONTROLLED

* SP1:	The platform notifies all students when a tournament is created

* SP2:	The platfotm notifies all students subscribed to a tournament of new upcoming battles

* SP3:	The platform notifies the final score to all students subscribed to a battle, when that battle ends

* SP4:	When the platform is notifies about a commit, it pulls from the committed repository to start the 
	mandatory analysis

* SP5:	The platform creates the github repository for a battle

* SP6:	The platform sends links to the created repository for the battle to all students who are subscribed 
	to the battle

---------------------------------------------------------------------------------------------------------------

### MACHINE

* M1:	The platform does the mandatory evaluation at each commit from a student for a battle

* M2:	The platform updates scores and leaderboard of a group

* M3:	The platform updates students personal scores at the end of a battle

* M4: 	Close battle on submission deadline 



---------------------------------------------------------------------------------------------------------------

## SCENARIOS:

* s1:	User sign in[wsp10, wsp11]
* s2:	User logs in[wsp4,wsp5]
* s3:	Educator creates tournament [w1, wsp1, msp1]
* s4: 	Educator creates battle [w3,wsp2, msp2]
* s5:	Student subscribe tournament [w9,wsp12, wsp16]
* s6: 	Team of students join battle [w5,w7,wsp8,w8, msp5, msp6]
* s7:	Application scores commit from student[wsp9, msp4,m1, m2]
* s8:	Users looks at battle rank(only involved) [wsp13]
* s9:	Battle subssion deadline expires [m3, m4,msp3]
* s10: 	Educator close torunament [w10,wsp15, msp14]	
* s11:	Users look at tournament rank[WSP14]



---------------

## GOALS

G1: An educator can manage a tournament 

G2:	An educator can create battles inside of a tournament in which he is involved

G3:	Students can partecipate and compete in battles created by an educator, alone or in groups

G4:	Students are scored based on their performance in battles

G5:	The platform allows students and educators to compare the performance of students

---------------------------------------------------------------------------------------------------------------

## RequirementS

R1:	The platform allows a signed in educator to create tournaments -> G1

R2:	The platform allows an educator that created a tournament, to invite other educator to 
	be co-creators -> G2

R3:	The platform allows educators who are involved with a tournament to create new battles in the context of 
	that same tournament -> G2

R4:	The platform allows an educator to upload the codekata (description and software project, 
including test cases and build automation scripts) and set deadlines, group rules when creating a battle -> G2

R5:	The platform allows a student subscribed to a tournament to join a battle in that tournament context-> G3

R6:	The platform allows students to create a group by inviting other students when joinin a battle -> G3

R7:	The platform allows students to subscribe to a tournament -> G3

R8: The platform allows an educator creating a battle to include a manual evaluation stage -> G2

R9:	The platform allows Students to login -> General Requirement

R10: The platform allows Educators to login -> General Requirement

R11:	If manual evaluation is required when a battle deadline expires the platform allows an educator to go through sources and add a score -> G4

R12:	The platform allows all users to view student ranks from previous and current tournaments they are involved in -> G5

R13:	The platform analyzes and scores the source of a group solution for a codekata battle-> G4

R14:	The platform pulls group sources from Github when it receive a notification

R15:	The platform allows all user to sign in

R16: 	The platform allows educators to close tournaments

R17: 	When a battle deadline expires the platform starts the automated evaluation for all groups

R18:	For every battle the platform maintains a ranking of the groups based on their battle score 

R19:	For every tournament the platform maintains a ranking for students based on the sum of their battle scores 





-----------------------------------------------------------------------------------------------------------------

## ASSUMPTIONS

A1:	The students can correctly setup the Github actions workflow(we dont know how to do it)

A2:	An educator will complete the manual evaluation

A3:	Github will always notifies the CKB platform after every student commit

A4: 	Students are always able to create a group to join a battle

A5:	Educators will only close a tournament when no battle is still ongoing


#TODO Educator uses the CKB platform to go through all sources for manual evaluation
#TODO Students can look at tournaments rank he is or was subscribed to
#TODO Educators can look at tournaments ranks for students that are or were subscribed to
#TODO 