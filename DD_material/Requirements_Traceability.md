# Requirements Traceability

R1:	The platform allows a signed in educator to create tournaments

* API Gateway: sign in
* Tournament Service: creation of tournaments

R2:	Educators can create battles in the context of a specific tournament they are involved in (Either by creation or by invitation)

* Battle Service: creation of battles

R2.1: The platform allows an educator that created a tournament, to invite other educator and to grant them permission to create battles in the tournament context

* Tournament Service: Invitation to collaborate to a tournament

R2.2:	The platform allows an educator to upload the codekata (description and software project, 
including test cases and build automation scripts) when creating a battle

* Battle Service: creation of battles

R2.3: The platform allows an educator to set subscribtion and submission deadlines when creating a battle

* Battle Service: creation of battles

R2.4: The platform allows an educator to set minimum and maximum group size when creating a battle

* Battle Service: creation of battles

R2.5: The platform allows an educator creating a battle to include a manual evaluation stage

* Battle Service: creation of battles
* Manual Evaluation Service: Allows an educator to add a manual score

R3:	The platform allows students to subscribe to a tournament

* Tournament Service: allows students to subscribe to new torunaments

R4:	The platform allows a student subscribed to a tournament to join a battle in that tournament context

* Battle Service: allows a student to subscribe to a new battle

R4.1: The platform allows students to create a group by inviting other students when joinin a battle

* Battle Service: allows students to create and join groups

R5:	The platform allows Students to login

* API gateway: allows login

R6: The platform allows Educators to login

* API gateway: allows login

R7: If manual evaluation is required during consolidation stage the platform allows an educator to go through sources and add a score to the group score

* Manual Evaluation Service: Allows an educator to add a manual score

R8: The platform allows all users to view student ranks from previous and current tournaments

* Leaderboard Service: Retrives from ApplicationDB all info to rebuild leaderboards

R8.1: For every tournament the platform maintains a ranking for students based on the sum of their battle scores

* Leaderboard Service: Holds all tournament information in ApplicationDB

R9: The platform pulls group sources from Github when it receives a notification within the submission deadline

* GitHub Handler: receives notifications from Github and stores each group source in SourceDB

R9.1: The platform analyzes, runs testcases and scores the source of a group solution for a codekata battle and updates the group score accordingly

* Software Evaluation Service: Whenever a pull is performed by Github Handler the service compiles and analyzes sources to then submit a new score
* Leaderboard Service: Updates score in ApplicationDB

R10: The platform allows all user to sign in

* API Gateway: allows register

R11: The platform allows educators to close tournaments

* Tournament Service: allows educators to manage tournaments

R12: When a battle deadline expires the platform sets the battle status to consolidation stage

* Battle Service: Managed all battle related deadlines

R13: The platform allows all users who are involved in a battle to look at group ranks for that battle

* Leaderboard Service: Retrives from ApplicationDB all info to rebuild leaderboards

R13.1: For every battle the platform maintains a ranking of the groups based on their battle score

* Leaderboard Service: Holds all battle information in ApplicationDB

R14: The platform will notify signed students of a newly created tournament

* Tournament Service: Will write a new message on message broker every time a new tournament is created
* Notification Service: Will read from message broker any new message regarding the creation of a tournament and notify students through Email Service

R15: The platform shall notify students who are subscribed into a tournament that a new battle is available in that tournament context

* Battle Service: Will write a new message on message broker every time a new battle in a tournament is created
* Notification Service: Will read from message broker any new message regarding the creation of a battle and notify subscribed students through Email Service

R16: The plaftorm shall notify students who are participating in a battle that the final rank for that battle is available

* Battle Service: Will write a new message on message broker every time a battle in a tournament has ended
* Notification Service: Will read from message broker any new message regarding the end of a battle and notify subscribed students through Email Service

R17: The plaftorm shall notify students who are subscribed in a tournament that the final rank for that tournament is available

* Battle Service: Will write a new message on message broker every time a tournament has ended
* Notification Service: Will read from message broker any new message regarding the end of a tournament and notify subscribed students through Email Service

R18: The platform shall create a new repository with Github for every new battle in any tournament after the submission deadline expires

* Battle Service: Will write a new message on message broker when the registration deadline expires
* GitHub Handler: Will read a create repository message from the message broker and communicate with GitHub to create a new repository

R19: The platform shall send the battle repository link to students who joined that battle after the submission deadline expires

* GitHub Handler: Will write a message everytime a new repository is created
* Notification Service: Will read a message containing the link to the repository and send an email through the email service to every student that has joined the repository's battle
