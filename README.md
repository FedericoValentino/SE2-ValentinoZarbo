# ValentinoZarbo

## Deadlines
* Group registration deadline: 22/10/2023
* RASD submission deadline: 22/12/2023
* DD submission deadline: 07/01/2024
* Final presentation: 14/02/24

----------

## Assignment

CodeKataBattle (CKB) is a new platform that helps students improve their software development skills
by training with peers on code kata1 . Educators use the platform to challenge students by creating code
kata battles in which teams of students can compete against each other, thus proving (and improving)
their skills.  


A code kata battle is essentially a programming exercise in a programming language of choice (e.g.,
Java, Python). The exercise includes a brief textual description and a software project with build
automation scripts (e.g., a Gradle project in case of Java sources) that contains a set of test cases that
the program must pass, but without the program implementation. Students are asked to complete the
project with their code. In particular, groups of students participating in a battle are expected to follow
a test-first approach and develop a solution that passes the required tests. Groups deliver their
solution to the platform (by the end of the battle). At the end of the battle, the platform assigns scores
to groups to create a competition rank.  

--------

Each battle created by an educator belongs to a specific tournament. Tournaments are created by an
educator who can then grant to other colleagues the permission to create battles within the context of
a specific tournament. When a new tournament is created, all students subscribed to the CKB platform
are notified and they can subscribe by a given deadline. If they subscribe, they are notified of all
upcoming battles created within that tournament. To create a new battle, an educator uses the CKB
platform to perform the following steps:  

* upload the code kata (description and software project, including test cases and build
automation scripts),
* set minimum and maximum number of students per group,
* set a registration deadline,
* set a final submission deadline,
* set additional configurations for scoring (see further details in the following).
------------

After creation of a battle, students use the platform to form teams for that battle. In particular, each
student can join a battle on his/her own or by inviting other students (respecting the minimum and
maximum number of students per group set for that battle). When the registration deadline expires,
the platform creates a GitHub repository containing the code kata and then sends the link to all students
who are members of subscribed teams. At this point, students can start working on the project.
Students are asked to fork the GitHub repository of the code kata and set up an automated workflow
through GitHub Actions that informs the CKB platform (through proper API calls) as soon as students
push a new commit into the main branch of their repository.  

Thus, each push before the deadline triggers the CKB platform, which pulls the latest sources, analyzes them, and runs the tests on the corresponding executables to calculate and update the **battle score of the team**.  
The score is a natural number between 0 and 100 determined by considering some mandatory factors
evaluated in a fully automated way, and optional factors evaluated manually by educators.  

**Mandatory automated evaluation includes**:
* functional aspects, measured in terms of number of test cases that pass out of all test cases (the
higher the better);
* timeliness, measured in terms of time passed between the registration deadline and the last
commit (the lower the better);
* quality level of the sources, extracted through static analysis tools that consider multiple aspects
such as security, reliability, and maintainability (the higher the better). Aspects are selected by the
educator at battle creation time.

**Optional manual evaluation includes:**
* personal score assigned by the educator, who checks and evaluates the work done by students (the
higher the better).

The CKB platform automatically updates the battle score of a team as soon as new push actions on
GitHub are performed. So, both students and educators involved in the battle can see the current rank
evolving during the battle. When the submission deadline expires, there is a consolidation stage in
which, if manual evaluation is required, the educator uses the CKB platform to go through the sources
produced by each team to assign his/her score. Once the consolidation stage finishes, all students
participating in the battle are notified when the final battle rank becomes available.  

At the end of each battle, the platform updates the personal tournament score of each student, that is,
the sum of all battle scores received in that tournament. Thus, for each tournament, there is a rank that
measures how a student's performance compares to other students in the context of that tournament.  

This information is available for all students and educators subscribed to the CKB platform, that is, all
users can see the list of ongoing tournaments as well as the corresponding tournament rank.  

When an educator closes a tournament, as soon as the final tournament rank becomes available the
CKB platform notifies all students involved in the tournament.  

(gamification exluded) 
-----------


