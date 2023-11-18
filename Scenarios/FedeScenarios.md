# SS

## S1 - User signs into platform
Professor Layton wants to evaluate the performance of his programming students but he has no easy way to do so. Fortunately he knows about CodeKataBattle (CKB) that can allow him to write a few coding challenges and automatically score his students submissions. He reaches the platform and is presented with a Sign In page where he has to full fill his details and set his profile to educator.  
Luke is a student of Professor Layton and when he heard that the professor is using CKB he immediately reached it and signed up by full filling his details and setting his profile to student.  

----------

## S3 - Educator creates tournament
Professor Layton, now signed into CKB, can create a new tournament and add collaborators to it. Once the tournament has been created every user subscribed to the CKB platform is notified and can enter it.

----------

## S5 - Student subscribes  to a tournament
Once a student is logged into the website he is presented with a list of available tournaments he can join. By clicking on one of them the tournament description will be available for him to read and if he wants he can click on join to take part in that tournament  

----------

## S7 - Application scores a commit from a student
   Every time a student commits some work to the forked repository the platform is notified and starts to run its automated evaluation by pulling the sources and running tests for:
  * functional aspects, measured in terms of number of test cases that pass out of all test cases;
  * timeliness, measured in terms of time passed between the registration deadline and the last commit;
  * quality level of sources, extracted through static analysis tools.

----------

## S9 - Battle submission deadline expires
When the submission deadline expires the battle goes into a consolidation stage where, if decided at the creation, educators can manually assign scores to each team by inspecting sources. Once this stage has ended all students participating in the battle are notified about their final battle rank and the tournament score is updated.

----------

## S11 - Users look at tournament ranks
Between battles and after the end of a tournament users can look at the tournament leader board. The position of every student is determined by the sum of all battle scores.