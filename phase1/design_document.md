​​# Design Document

## Updated Specification

   Since Phase 0, our group has completed a number of critical components for our social media application. We began by completing the remaining tasks in our 
specification and user stories. These tasks included:
- Reworking a cleaner approach to our Request and Response Models 
- Reworking interactors for our improved Input and Output Models
- Conversation Queue
- Implementing a Gateway for reading constants from a JSON file
- Changing User IDs to be unique integer ID’s for easier sorting/management in the database

After completing our specification, we turned our focus to implementing a Web Application. This involved:
- Moving to and configuring Spring Boot
- Implementing and configuring a database using PostgreSQL
- Adjusting our Request/Response models 
- Linking our entities to read, write and edit from associated Database Repositories

## Design Decisions

   In the further development of our app, we were called to consider many design questions for better optimizing the functionality of our app.
   
 - Implementing a WebApp

   A key design decision our group made was to focus our Social Media App towards a Web Application, to maximize the reach and scope of our app. We specifically decided to use Spring Boot as our back-end API server, because of its easy implementation in Java, and PostgreSQL for the implementation of our Databases.
   
 - Int IDs

   After working on our code, we decided to change User IDs from a string to a unique integer ID. Originally, we planned to pair non-unique usernames with a unique string userID, consisting of the username itself and a number identifier, such as "alex#01". We wanted to compare the User Dds lexicographically based on the Unicode value of each string character, to be easily able to sort and search through IDs. 
   
   However, after moving to Spring Boot and implementing Databases for getting and writing data, we found PostgreSQL Databases to offer an easy way of automatically setting unique integer IDs in chronological order, so we decided to utilize PostgreSQL's handy implementation of int ids. 

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

   Finally, the Dependency Inversion Design principle states that high-level modules should depend on abstractions or interfaces rather than low-level modules and that details (like concrete implementations) should depend on abstractions. ******


## Packaging Strategies

   Our group has decided to package by the layers of Clean Architecture, to allow for easy sorting between the functionality and purpose of methods. Since our code was created with the purpose of fulfilling thoughtful and organized layers as demonstrated in Clean Architecture, packaging by layers allows for clear, concise and organized file sorting. 
   
## Design Patterns

   Our program displays the use of many important design patterns, to solve important problems effectively and efficiently in our code. 
 
 - Facade Design Pattern

   The Facade design pattern provides a simple interface to a complex subsystem, containing many moving parts. In our code, Conversations and Users have a lot of particular interactors. To simplify our code, we created manager classes for Conversations and Users in the Application Business Rules Layer, which serve as facades to delegate calls to specific interactors as needed.

- Strategy Design Pattern

    The Strategy Design pattern facilitates defining family of algorithms in separate classes, while still making their objects interchangeable. Our program features the Strategy Design Pattern through our implementation of Conversation sorting for a User’s ConversationQueue Sorting is done either randomly or by an algorithm that takes into account a User’s interests. Both these strategies implement the ConversationSorter interface and can be used interchangeably by ConversationQueue where appropriate.

    
## Progress Report

Our team has continued to work effectively in completing our Specification and moving our program to a Web App. Our team has continued to work well as a team in communicating amidst ourselves and in dividing the work between us.
Further, Clean Architecture has continued to show its benefits with an easy transfer of specific components into SpringBoot. 

## Open Questions

As our group continues to implement a Web Application, we have come across a few design questions that we are considering.
- Long vs int for user ids

  We noticed SpringBoot and PostgreSQL uses longs as variables by default for ids. Is there an advantage between using longs versus using ints?

- Using Database-set IDs vs setting our own database ids

  We found PostgreSQL's implementation of int IDs to be very useful for database organization, however, some sources on the web we came across suggested against this, because using Database generated IDs delegate an important aspect of the program's domain to third party software. Should we consider handling userIDs ourselves?

- Making our API publicly available


## Work Allocation

We have finalized all the remaining fundamental components of our application, and have turned our development to transferring/implementing a Web App using SpringBoot. 

- Alex: Refactoring User Interactors for new Response Model, Moving to SpringBoot, Design Document, Slideshow presentation
- Spencer: Reworking Request and Response Model, Data Acess Object, Moving to SpringBoot
- Will: Refactoring Conversation interactor for new Response Model, Moving to SpringBoot
- Ted: Moving to SpringBoot
- Yukthi: User Stories, Reworking Interactors for Spring Boot, Slideshow presentation
- Zachary: Refactoring Conversation manager and some conversation interactors for the new response model. Created a new gateway, JSON file and interface for access to constants used in interactors, Slideshow presentation.

We are all enjoying seeing the fruits of our labour begin to form a working application, and are excited to see where Phase 1 takes us! 
Here are some ideas of the next steps in our development:
- Finalizing our back end
- Deciding how we want to handle our front end
- UI/Design choices

What we plan to work on next:
- Alex: 
- Spencer: 
- Will: 
- Ted: 
- Yukthi: 
- Zachary:


