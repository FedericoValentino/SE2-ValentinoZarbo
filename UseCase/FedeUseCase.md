# FedeUC

## UC Fede1 Sign into platform

| Name | Sign into platform |
|:---- | ------------------------ |
|Actors| Users(Educators, Students)|
|Entry condition| A user accesses the platform for the very first time|
|Event flow| * User reaches the platform<br>* User clicks on "sign in" button<br> * User fills out own info and decides if they want to be an Educator or student<br> * User concludes the registration by clicking "finish" button|
|Exit | User is successfully registered on the platform as either educator or student |
|Exception | Missing or wrong input by user during registration form |

## UC Fede2 Create Tournament

| Name | Create Tournament |
|:---- | ----------------- |
|Actors| Educator, Students |
|Entry condition | Educator is logged into the platform |
|Event flow | * Educator presses "Create Tournament" button <br> * Educator fills out details of tournament <br> * Educator adds other collaborators to tournament(other Educators) <br> * Educator completes tournament creation <br> * Platform notifies all Students of new tournament |
| Exit | Tournament is created successfully |
| Exception | Missing or wrong input by Educator during tournament creation form |

## UC Fede3 Subscribe to Tournament 

| Name | Subscribe to Tournament |
|:---- | ----------------------- |
|Actors| Students|
|Entry condition | Student is logged into the platform |
|Event flow | * Student is notified of new tournament <br> * Student presses "Join tournament" button |
|Exit | Student is subscribed to tournament |
|Exception | |

## UC Fede4 Score Commit

|Name | Score Commit |
|:--- | ------------ |
|Actors| Github |
|Entry Condition| Github notified platform of student commit |
|Event flow|* Platform receives notification of commit <br> * Platfowm pulls sources and compiles them <br> * Platform starts evaluation(functional, timeliness, quality)<br> * Platform updates score for battle|
|Exit| Battle leader board is updated|
|Exception| Compilation of sources fails |

## UC Fede5 Look at tournament ranks

|Name | Look at tournament ranks |
|:--- | ------------ |
|Actors| Students, Educators|
|Entry Condition| Student or Educator are on the main tournament page and are involved|
|Event flow| Student or educator click on tournament leaderboard|
|Exit| Platform shows Tournament leader board|
|Exception| Tournament hasn't yet started |

## UC Fede6 Manual Evaluation

|Name | Manual Evaluation |
|:--- | ------------ |
|Actors | Educators |
|Entry Condition| Battle deadline Expires |
|Event flow| * Platform notifies educators that battle ha ended and manual evaluation is required(as decided during battle creation) <br> * Educators use platform to analyze sources |
|Exit| Educator add a manual score |
|Exception| Educator adds illegal score |
