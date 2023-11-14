# Use cases

***dubbi*** use case compositi (optional e sotto componenti) come vanno descritti??


## UC Nico1 Create battle

|Name  | Create Battle |
|:---- |---------------|
|Actors|   Educator, Student|
|Entry condition| Educator has logged in and is inside the context of a tournament  |
|Event flow|  * Educator press "create new battle" button <br> * Edu upload the codekata assignment, tests and project build<br>* Edu sets subscription and submission deadlines and group size rules <br>*Edu complete battle creation<br> *The platform sends notification to all the students subscribed to the tournament    |
|Exit| Battle created correctly |
|Exception| Missing or wrong input by edu|

the sub use case add additional evaluation and set rules should have their own use case????


## UC Nico2 Close tournament

|Name| Close tournament|
|:---|---|
|Actors|Educator, Student|
|Entry condition| Educator logged in, Tournament still ongoing, no ongoing battle|
|Event flow| * Edu press "close tournament" in the tournament context(??) <br> * The platform closes the tournament <br> * The platform notifies all the students subscribed to the tournamen|
|Exit| Tournament closed, students notified|
|Exception| |

## UC Nico3 Join Battle

|Name|Join Battle|
|:---|---|
|Actors|Student|
|Entry condition| Student subscribed to a tournament, student logged in, battle available for subscription in tournament| 
|Event flow| * Student press "join battle" button in tournament context(??) <br> * platform shows group size rules <br> * student uses "invite" button<br> * Student inserts other students identifiers <br> * other students accepts invite <br> * Group of student is created <br> * Group of students joins battle <br>  |
|alt flow| [rules allow singleton group] <br>  * student joins battle as a group of 1|
|Exit condition| Student group joins battle|
|Exceptioin| |

## UC Nico 4 Create repository

|Name|Create repository|
|:--|--|
|Actors|Github, Student|
|Entry condition| Subscription deadline of battle expired|
|Event flow|* The platform creates a repository for the codekata battle in github <br> * The platform sends to all students subscribed to the battle the link to the repository<br> |
|Exit condition| Student recieve repository link|
|Exception| |

## UC Nico5 View Battle Ranking

|Name| View Battle Ranking|
|:--|--|
|Actor| Any user|
|Entry condition| Educator is involved with tournament of the battle, student has joined battle|
|Event flow| User press "view ranking" button in battle context(??) <br >* platform shows ranking table|
|Exit condition| User sees ranking table |