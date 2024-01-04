# Run diagram descriptions

All the interaction that use rest APIs start with the client request to an api that may contain a number of parameters, which are listed in the interface description of the api in the apposite document section, and end with either a positive response, eventually containing requested data and/or hyperlink for contextual page navigation ( following rest's HATEOAS principle), or a negative response in case of any error.  
For reading semplicity only positive response are shown in the diagrams, as any other error response sent to the client results always only in an error alert in the client application page.  
In the interaction where the notification service is involved an email containing a message for the users is sent. The message content and destination is in the diagram description.  
In some complex diagrams where the message broker, a more specific description of its interaction with the other services is provided.


## Sign Up

same as login

## Create Tournament

Once the tournament has been created, the client is redirected to the new tournament page and an email is sent to all CDK student users notifying them of the new tournament existence.  

## Add collaborator

An email is sent to the invited educator, notifying him of its newly granted ability in the tournament.

## Subscribe to tournament

## Score commit

The interaction starts from github notication to the system, once the source code has been pulled it is stored in the source dbms,and a message notifying the new sources is sent to the message broker.  
The software evaluation service is subscribed to the source topic and sees the notification, therfore it reads the sources from the source dbms and starts to score the source code. Then it notifies the leaderboard service with the new score through the message broker, wich stores it as the group score in the application dbms.

## Look at t leaderboard

## Manual evaluation

At the start of the interaction the client requests the souce code text to be scored, once it has been received and reviewed by the educator user another request is sent containing the score assigned to the group manually by the educator.

## Create battle

At the end of the interaction the systems response contains the hypelink to the newly created battle resource.  
Also an email is sent to all the student users that are subscribed to the tournament, notifying them of the new available battle.

## Join battle

During the interaciont the notifycation service sends an email the student listed from the user when joining the battle, notifying them of their position as group members in the battle.  
If the battle group rules allow it and the user decide to join alone, the notification service won't read any message from the message broker, and no email will be sent.  


## Create repo

This interaction commence from inside the system once the battle subscription deadline expires, no user client is involved.  
The github handler makes use of GH API's to create a repository and receives its link, which is then stored in the source dbms.  
Finally an email containing the the repository link is sent to the students who joined the battle.


## login

After the user has logged in, the positive rest response contains the hypertext to redirect the client to the tournaments main page. 

## close tournament



## view battle leaderboard

The response recieved from the client contains the leaderboard data wich will be visualized.