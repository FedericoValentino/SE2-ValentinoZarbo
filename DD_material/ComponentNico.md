# Components Nicola

* GitHub handler :    Interfaces with github APIs in order to: 
    * create a Repository for a Battle after the subscription deadline
    * retrieve the source code from a group after any commit and store it on a DataBase so that it can be automatically evaluated and then manually evaluated if needed during the consolidation phase
* Message broker :  It is the component tasked with dealing with messages from other services to enstablish the event based comunication between services. It keeps message queue for the various topics which some services produce and to and other subscribe to
* Email service :   Its is a third party service, used to send users various notifications.
* API gateway : It handles client access to the various system services, it also handles the authorization aspects of the services use, to ensure system security.
* Web server : It interfaces with the client browser and responds to its requests with the needed web pages(html+css+js).
* WebApp : The part of the application that runs on the client browser. It is made of a set of web pages which are able to make requests to the web server and the services the system provides.
* Github : It is the third party application that handles the battle repositories. 
    * The student users use its services outside the CDK scope to fork a CK assignement and work on its solution.
    * The CDK system interfaces with some Github services in oreder to create the repository and retrieve the solutions committed by students for evaluation.
* DBMS (Application) : It is the dbms that provides access to the database containting all the information about users, tournaments, battles and the scoring.
* DBMS (Sources) : The dbms for the database that keeps the source code related to any groups in any battles. This database is separated from the other more generic one in order to :
    * Optimize its performance , since the dimension of its records can be possibly way bigger.
    * Have better control on the source codes to be stored, since they will run inside the CDK system for evaluation, making it a security concern.
