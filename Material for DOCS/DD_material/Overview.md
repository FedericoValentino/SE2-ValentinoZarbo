# Overview

This is an high level description of the systme.  
The system of the CDK application is composed of a set of microservices wich comunicate between eachothers asynchronously by using a message broker (or a number of message brokers) (event based internal architecture) and between service and end-user via a API Gateway.  
The user application is a WebApp, the pages html(+js +css) is provided by a web server and the page content is gained by a number of call to the system APIs.  
The system also exploites a third party email service to communicate notification to the user and uses Github APIs for the management of battle repository. 

## 