# Design Document

### Updated Specification

   Since Phase 0, our group has completed a number of ciritical comonents for our social media application. We began by completing remaining tasks in our 
specification and user stories. These tasks included:
- Reworking a cleaner approach to our Request and Response Models 
- Reworking interactors for our improved Input and Output Models
- Conversation Queue
- Implementing a Gateway for reading constants from a JSON file
- Changing User IDs to be unique integer ID’s for easier sorting/management in the database
After completing our specification, we turned our focus to implementing a Web Application. This involved:
- Moving to and configuring SpringBoot
- Implementing and configuring a database using PostgreSQL
- Adjusting our Request/Response models 
- Linking our entities to read, write and edit from associated Database Repositories

### Design Decisions

   In the further development of our app, we were called to consider many design questions for better optimizing the functionality of our app.
   
 - Implementing a WebApp

   A key design descision our group made was to focus our Social Media App towards a Web Application, to maximize the reach and scope of our app. We specifically decided to use SpringBoot as our back-end API server, because of it's easy implementation in Java, and PostgreSQL for the implementation of our Databases.
   
 - Int IDs

   After working on our code, we decided to change User IDs from a string to a unique integer ID. Originally, we planned to pair non-unique usernames with a unique string userID, consisting of the username itself and a number identifier, such as "alex#01". We wanted to compare the User Dds lexicographically based on the Unicode value of each string character, to be easily able to sort and search through IDs. 
   
   However, after moving to SpringBoot and implementing Databses for getting and writing data, we found PostgreSQL Databases to offer an easy way of automatically setting unique integer IDs in chronological order, so we decided to utilize PostgreSQL's handy implementation of int ids. 

### Clean Architecture


### SOLID Design Principles

   The single responsibility principle states that each class, module, or function should only have full responsibility for a single functionality of the program. The single responsibility principle is evident in the Application Business Rules/Interactors layer, where each User, Message or Conversation interactor is responsible for a specific feature pertaining to a user, conversation or a message 

   The second design principle hilights how entities should be open for extension, but closed for modification. In our code, through our implementation of Clean Architecture, Entities within the Enterprise Business Rules portray essential basic components of our program. As such, any additional features to our program exist in the Application Business Rules/Interactors layer, allowing for additonal features to be easily extended as an Interactor.

   Next, the Liskov substitution principle highlights how objects in a program should be replaceable with instances of their subtypes without altering the correctness of that program. ******

   The Interface Segregation principle requires that classes only be able to perform behaviours that are useful to achieve their purpose. In other words, classes do not include behaviours they do not use. **** Facade design patterns -> splitting interactors

   Finally, the Dependency Inversion Design principle states that high-level modules should depend on abstractions or interfaces rather than low-level modules and that details (like concrete implementations) should depend on abstractions. ******


### Packaging Strategies

   Our group has decided to package by the layers of Clean Architecture, to allow for easy sorting betwen the functionality and purpose of methods. Since our code was created with the purpose of fufilling throughtful and organized layers as demonstrated in Clean Architecture, packaging by layers allows for clear, consise and organized file sorting. 
   
### Design Patterns

   Our program displays the use of many important design patterns, to solve important problems effectively and efficiently in our code. 
 
 - Facade Design Pattern

    The Facade design pattern provides a simple interface to a complex subsystem, containing many moving parts. In our code, Coversations and Users have a lot of particuilar interactors. To simplify our code, we created manager classes for Conversations and Users in the Application Buisness Rules Layer, which serve as facades to delegate calls to speciifc interactors as needed.

- Strategy Design Pattern

    The Strategy Design pattern facilitates defining family of algorithms in seperate classes, while still making their objects interchangeable. ***
    
### Progress Report

Our team has continued to work effectively in completing our Specification and moving our program to a Web App. Our team has continued to work well as a team in communicating amidst ourselves and in dividing the work between us.
Further, Clean Architecture has continued to show its benefits with an easy transfer of specific components into SpringBoot. 

### Open Questions

As our group continutes to implement a Web Application, we have come across a few design questions that we are consiering.
- Long vs int for user ids

  We noticed SpringBoot and PostgreSQL to use longs as variables by default for ids. Is there an advantage between using longs versus using ints?

- Using Database-set IDs vs setting our own database ids

  We found PostgreSQL's implementation of int IDs to be very useful for databse organization, however, some sources on the web we came across suggested using Database generated IDs to delegate an important aspect of the program's domain to third party software. Should we consider handling userIDs ourselves?

- Making our api publicly available


### Work Allocation

We have finalized all the remaining fundamental components of our application, and have turned our development to transferring/implementing a Web App using SpringBoot. 

- Alex: Refactoring User Interactor for new Response Model, Moving to SpringBoot, Design Document, Slideshow presentation
- Spencer: Moving to SpringBoot
- Will: Refactoring Conversation interactor for new Response Model, Moving to SpringBoot
- Ted: Moving to SpringBoot
- Yukthi: 
- Zachary: Refactoring Conversation manager and some conversation interactors for the new response model. Created a new gateway, JSON file and interface for access to constants used in interactors. Slideshow presentation.

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



