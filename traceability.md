# Traceability matrix

Da mettere codici use case,  
MANTENERE AGGIORNATO  

Problemi trovati:
1. Dove mettere close tournament??
2. 
3. 
4. 

| goal          |  req          | Use Case      |  
|---------------|---------------|---------------|
|            g1 |      r1      |   createTournament   |               
|     g2        |   r2          | invite educator for tournament     | 
|            g2 |      r3      |   createBattle |
|     g2        |   r4          | upload CodeKata |             
|            g3 |      r5      | student join battle|              
|     g3        |   r6          |invite Students x battle|             
|            g3 |      r7      | subscribe tournament|             
|     g2        |   r8          |upload CodeKata|             
|            g |      r9      |  login student|             
|             |   r10          |login educator|             
|               |   r11          | manual evaluation on expire|            
|     g5        |   r12          | look Tournament rank|            
|     g4        |   r13          | automated evaluation|           
|     g4        |   r14          | pull sources|           
|     g        |   r15          | sign in|           
|     ???        |   r16          | close tournament|           
|     g4        |   r17          | automated avaluation on expire|           
|     g5        |   r18          | battle ranking|  
|     g5        |   r19          | tournament ranking|           


## G1: An educator can create a tournament 

R1:	The platform allows a signed in educator to create tournaments

## G2:	An educator can create battles inside of a tournament in which he is involved

R2:	The platform allows an educator that created a tournament, to invite other educator to ***(andrebbe in un goal di gestione torneo con chiudi torneo)***
	be co-creators  
R3:	The platform allows educators who are involved with a tournament to create new battles in the context of 
	that same tournament  
R4:	The platform allows an educator to upload the codekata (description and software project,
including test cases and build automation scripts) and set deadlines, group rules when creating a battle  
R8: The platform allows an educator creating a battle to include a manual evaluation stage  

## G3:	Students can partecipate and compete in battles created by an educator, alone or in groups

R5:	The platform allows a student subscribed to a tournament to join a battle in that tournament context  
R6:	The platform allows students to create a group by inviting other students when joinin a battle  
R7:	The platform allows students to subscribe to a tournament  

missing: the platform creates a github repository for a battle, platform notifies student with repository link

A1:	The students can correctly setup the Github actions workflow(we dont know how to do it)  
A4: 	Students are always able to create a group to join a battle  


## G4:	Students are scored based on their performance in battles

R11:	If manual evaluation is required when a battle deadline expires the platform allows an educator to go through sources and add a score 
R13:	The platform analyzes and scores the source of a group solution for a codekata battle  
R14:	The platform pulls group sources from Github when it receive a notification  
R17: 	When a battle deadline expires the platform starts the automated evaluation for all groups  

A2:	An educator will complete the manual evaluation   
A3:	Github will always notifies the CKB platform after every student commit

## G5:  All users can compare the performance of students in battles and tournaments

R12:	The platform allows all users to view student ranks from previous and current tournaments they are involved in 
R18:	For every battle the platform maintains a ranking of the groups based on their battle score 
R19:	For every tournament the platform maintains a ranking for students based on the sum of their battle scores  




