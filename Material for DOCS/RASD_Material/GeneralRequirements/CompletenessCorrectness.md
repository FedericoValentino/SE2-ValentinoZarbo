@OBSOLETE

# Goal completness and correctness

## G1: An educator can manage a tournament

R1:	The platform allows a signed in educator to create tournaments  
R11: The platform allows an educator to close a tournament

## G2:	An educator can create battles inside of a tournament in which he is involved

  
R2:	Educators can create battles in the context of a specific tournament they are involved in (Either by creation or by invitation)   
R2.1: The platform allows an educator that created a tournament, to invite other educator and to grant them  permission to create battles in the tournament context  
R2.2: The platform allows an educator to upload the codekata (description and software project, including test cases and build automation scripts) when creating a battle  
R2.3: The platform allows an educator to set subscribtion and submission deadlines when creating a battle  
R2.4: The platform allows an educator to set minimum and maximum group size when creating a battle  
R2.5: The platform allows an educator creating a battle to include a manual evaluation stage  

## G3:  Students can partecipate in tournaments created by an educator

R3: The platform allows students to subscribe to a tournament  
R14: The platform will notify signed students of a newly created tournament  

## G4:	Students can partecipate in battles created by an educator, alone or in groups

R4: The platform allows a student subscribed to a tournament to join a battle in that tournament context  
R4.1: The platform allows students to create a group by inviting other students when joinin a battle    
R15: The platform shall notify students who are subscribed into a tournament that a new battle is available in that tournament context  

### missing: the platform creates a github repository for a battle, platform notifies student with repository link

A1:	The students can correctly setup the Github actions workflow(we dont know how to do it)  
A4: 	Students are always able to create a group to join a battle  


## G5:	Students are scored based on their performance in battles

R7:	If manual evaluation is required during consolidation stage the platform allows an educator to go through sources and add a score to the group score  
R9.1:	The platform analyzes, runs testcases and scores the source of a group solution for a codekata battle and updates the group score accordingly  
R9:	The platform pulls group sources from Github when it receive a notification within the submission deadline  
R12: When a battle deadline expires the platform sets the battle status to consolidation stage  

A2:	An educator will complete the manual evaluation   
A3:	Github will always notifies the CKB platform after every student commit

## G6: The platform allows students and educators to compare the performance of students

R8: The platform allows all users to view student ranks from previous and current tournaments  
R8.1: For every tournament the platform maintains a ranking for students based on the sum of their battle scores  
R13: The platform allows all users who are involved in a battle to look at group ranks for that battle  
R13.1: For every battle the platform maintains a ranking of the groups based on their battle score  
R16: The plaftorm shall notify students who are participating in a battle that the final rank for that battle is available  
R17: The plaftorm shall notify students who are subscribed in a tournament that the final rank for that tournament is available   

