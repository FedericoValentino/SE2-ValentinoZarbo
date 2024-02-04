# Fede Components

* Tournament Component: The tournament component handles all aspects of a tournament, from the creation to the joining of students.

* Battle Component: The battle component handles all aspects of a battle except for the group creation, for which the GroupManagement Module is required.

* Notification Compontent: The notification component handles the notification service. Students and Educators are gonna get email notification from this component whenever: a new tournament is created, a new battle is created, a final rank for a battle is available and a final battle for a tournament is available.

* Leaderboard Component: The component handles all kinds of leaderboards present in the system. It exchanges messages with ManualEval and SoftwareEval and through those it updates the leaderboards of battles and tournaments.

* ManualEval Component: The component handles the consolidation stage of a battle(if it was required). It gives access to the analysis of sources in order to add a new score to a group for a battle

* SoftwareEval Component: The component handles the automatic scoring of new commits to the students repo. Whenever a student commits to the repo GitHub sends a notification to the GithubHandler Component which downloads the sources and signals to this component the neednes of a Evaluation

* GroupManagement Component: The component handles the creation of groups of students for battles.

