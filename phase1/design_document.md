# Design Document

## Our Mission

Our team is developing an application that facilitates meeting new people with similar interests through conversations.
We want to give our users the opportunity to connect with other users without experiencing the social pressure induced by social media platforms.

## Updated Specification

   Since Phase 0, our group has completed a number of critical components for our social media application. We began by completing the remaining tasks in our 
specification and user stories. These tasks included:
- Conversation Queues: for sorting conversations based on user interests
- Changing User IDs to be unique integer IDs for easier sorting/management in the database


Some things that we reworked:
- Request and Response Models for requests between the controllers and interactors
- Our Data Access Objects for interacting with the persistence layer
- Our controller layer to work with Spring Boot and our updated interactors


After completing our specification, we turned our focus to implementing a Web Application. This involved:
- Moving to and configuring Spring Boot
- Installing PostgreSQL on each of our systems
- Implementing and configuring a database using PostgreSQL
- Adjusting our Request/Response models 
- Linking our entities to read, write and edit from associated Database Repositories

## Design Decisions

   In the further development of our app, we were called to consider many design questions for better optimizing the functionality of our app.
   
 - Implementing a WebApp

   A key design decision our group made was to focus our Social Media App towards a Web Application, to maximize the reach and scope of our app. We specifically decided to use Spring Boot as our back-end API server, because of its easy implementation in Java, and PostgreSQL for the implementation of our Databases.
   
 - Int IDs

   Originally, we planned to pair non-unique usernames with a unique string userID, consisting of the username itself and a number identifier, such as "evan#10". However, in considering how our data will be stored in a database, we decided it would be much simpler to assign each user a unique integer identifier (and conveniently our database can handle the creation of these values for us). In considering these things, we also decided to enforce unique usernames. As it is was not clear to us how to easily and securely allow a user with a non-unique username to login to their account using their username.
 
-  Request and Response Models for requests between users and interactors

   After implementing an initial Request and Response models for communication between our Presenters and Interfaces, we decided to redesign our input and output models for more simplicity and to implement a cleaner design. 
   
   Originally, we had different requestmodels for each interactor, making sending and recieving requests and responses unnecessarily complex. To adjust this, we created one requestmodel for all interactors. However, after further implementing our program as a web app, we then decided to use the request and responses as implemented and handled by Spring Boot.

## Clean Architecture

Clean architecture plays a fundamental role in our application. 
As such, we developed our software so that we can easily modify each component of it without affecting other pieces. 
Nonetheless challenging at first, implementing clean architecture throughout our application proved itself essential and extremely useful. 
Here is an overview of the architecture of our software:

Our application's first and outermost layer is the web interface, not yet implemented for Phase 1. 
We plan to use the front-end of our application to take input from the user and send it to the appropriate controller.

Following this, the controller transforms the input into a request given to a use case manager responsible for handling use cases. 
The use cases implement the input boundary interface to handle the request sent by the controller using the manager.
It ensures that use cases do not depend on controllers directly but instead on the input boundary interface.
Also, since the controllers handle the user's input, the user never directly utilizes the use cases.

Once the use cases accept the request, it fetches the entities from the database using a data access interface. 
The data access interface implemented by each repository associated with each entity is an explicit use of dependency inversion to maintain a clean architecture.

Lastly, and not implemented yet, the output is returned through the controller and the
information relevant to the user is displayed on the web application's graphical user interface (not implemented).

- At the Enterprise Business Rules layer are the core components of the application: User, Conversation, Message.
- At the Application Business Rules layer are the classes UserManager, ConversationManager, MessageManager. The three managers handle Use Cases also in that layer.
- At the Interface Adapters layer are the controllers. (UserController is implemented and MessageController and ConversationController remain to be implemented)
- At the Framework & Drivers are the database and the web interface.

## SOLID Design Principles

In the development of our App, we considered many uses of SOLID design principles to better demonstrate effective, well-designed code.

- The single responsibility principle
  
  The single responsibility principle states that each class, module, or function should only have full responsibility for a single functionality of the program. The single responsibility principle is evident in the Application Business Rules/Interactors layer, where each User, Message or Conversation interactor is responsible for a specific feature pertaining to a user, conversation or a message 


- Open/Closed principle

  The second design principle highlights how entities should be open for extension, but closed for modification. In our code, through our implementation of Clean Architecture, Entities within the Enterprise Business Rules portray essential basic components of our program. As such, any additional features to our program exist in the Application Business Rules/Interactors layer, allowing for additional features to be easily extended as an Interactor.


- The Liskov substitution principle

   Next, the Liskov substitution principle highlights how objects in a program should be replaceable with instances of their subtypes without altering the correctness of that program. ******


- Interface Segregation principle

   The Interface Segregation principle requires that classes only be able to perform behaviours that are useful to achieve their purpose. In other words, classes do not include behaviours they do not use. In the Application Business Rules layer of our code, we used the Facade design pattern to split interactors so that they only implement specific features, limiting their scope to only the behaviours that they use.


- The Dependency Inversion principle

  Finally, the Dependency Inversion Design principle states that high-level modules should depend on abstractions or interfaces rather than low-level modules and that details (like concrete implementations) should depend on abstractions. In our past implementation, we applied this principle in our output boundary interface, which we used to send data from the use cases to the presenter (inner layer to outer layer). However, we changed our application to a Spring Boot application, and now our data output is handled by Spring Boot. The dependency inversion is also a core element of how we access data in our database. In our current implementation, the use cases fetched entities from the database using a data access interface implemented by the various repository associated with each entity. It ensures that the use cases depend not on the database itself but the data access interface.

## Packaging Strategies

   Our group has decided to package by the layers of Clean Architecture, to allow for easy sorting between the functionality and purpose of methods. Since our code was created with the purpose of fulfilling thoughtful and organized layers as demonstrated in Clean Architecture, packaging by layers allows for clear, concise and organized file sorting. 
   
## Design Patterns

   Our program displays the use of many important design patterns, to solve important problems effectively and efficiently in our code. 
 
 - Facade Design Pattern

   The Facade design pattern provides a simple interface to a complex subsystem, containing many moving parts. Conversations and Users have a lot of small interactors, so to simplify our code, we created “manager” facade classes for Conversations and Users to delegate calls to specific interactors as needed, rather than having all of these small interactor methods together in one class.


- Strategy Design Pattern

    The Strategy Design pattern facilitates defining family of algorithms in separate classes, while still making their objects interchangeable. An important feature of our app is recommending new conversations based on a User’s interests. So, to sort through which Conversations to recommend we have a few different algorithms in mind. We employed the Strategy Design Pattern for this, creating a ConversationSorter interface then creating implementing classes for each of our specific sorting algorithms. This way they are easily interchangeable.

    
## Progress Report

Our team has continued to work effectively in completing our Specification and moving our program to a Web App. Our team has continued to work well as a team in communicating amidst ourselves and in dividing the work between us.
Further, Clean Architecture has continued to show its benefits with an easy transfer of specific components into SpringBoot. 

## Open Questions

As our group continues to implement a Web Application, we have come across a few design questions that we are considering.
- Are 32 bit integers sufficient for unique User ids?
    We noticed in Evan’s “Intro to Web Apps” example that Java’s long type was used for ids. What are the pros and cons to using long instead of int for ids?


- Using Database-set IDs vs setting our own database ids
    We found PostgreSQL's implementation of int IDs to be very useful for database organization, however, some sources on the web we came across suggested against this because using Database generated IDs delegates an important aspect of the program's domain to third party software. Should we consider handling userIDs ourselves? What considerations must we make if we choose to do this?


- Making our API publicly available
    We hope to have our API publicly available for Phase 2. Where can we host our app? What changes need to be made to our app and database to support running remotely? What security concerns must we address in making our API public?


## Work Allocation

We have finalized all the remaining fundamental components of our application, and have turned our development to transferring/implementing a Web App using SpringBoot. 

- Alex: Refactoring User Interactors for new Response Model, Moving to SpringBoot, Design Document, Slideshow presentation
- Spencer: Reworking Request and Response Model, Data Acess Object, Moving to SpringBoot
- Will: Refactoring Conversation interactor for new Response Model, Moving to SpringBoot
- Ted: AddFollower Interface, UserManager, Moving to SpringBoot
- Yukthi: User Stories, Reworking Interactors for Spring Boot, Slideshow presentation
- Zachary: Refactoring Conversation manager and some conversation interactors for the new response model. Created a new gateway, JSON file and interface for access to constants used in interactors, Slideshow presentation.

We are all enjoying seeing the fruits of our labour begin to form a working application, and are excited to see where Phase 1 takes us! 
Here are some ideas of the next steps in our development:
- Finalizing our back end
- Deciding how we want to handle our front end
- UI/UX Design choices
- In the long run: bringing our app live!

What we plan to work on next:
- Alex: Implementing Front End Design
- Spencer: Finalizing our Backend
- Will: Implementing Front End Design
- Ted: Finalizing our Backend
- Yukthi: Implementing Front End Design
- Zachary: Finalizing our Backend


