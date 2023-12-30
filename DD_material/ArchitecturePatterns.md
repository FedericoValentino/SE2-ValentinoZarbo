# Architectural styles used

## Microservices 

A microservice architecture is needed mainly in order to freely scale some component indipendently from others, like the **software evaluation** service, which at time may need much more computational resources, meanwhile other services are less likely to require as much, as they don't perform heavy activities like code analysis and execution.
Other then that, this style of architecture provides many more general advantages, making easy to choose. 
### API GATEWAY

The API gateway provvides many benefits as the only entry point between the client and services.
Here are the ones which were more valued: Security and authentication; Load balancing; Caching. 

## Hybrid architecture (REST and EBA)
### Event Based architecture

Used in the system backend for the communication between microservices.  
The use of aynchronous communication allows for more flexibility and scalability, both important for some of the microservices, in particular the **software evaluation** service, which needs to scale indipndently from other services becaus of its higher computational needs.
Moreover the the system interfaces with Guthub APIs which have also an EBA.

### Rest(ful APIs)

The services wich provide functionalities for the front end expose RESTful APIs.  
This way there is a greater separation of concern between front end and backend, as when developing the client application a team doesnt need to concern themeself with the internal structure of the system made of services, but only need to know the URI tree path used to access resources and their operations. 