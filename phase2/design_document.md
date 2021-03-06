# Design Document - Optimistic Newts

## Table of contents
1. [Our Mission](#Our-Mission)
2. [Specification](#Specification)
3. [Setup Instructions](#Setup-Instructions)
4. [Progress in Phase 2](#Progress-in-Phase2)
5. [SOLID design principles](#SOLID-Design-Principles)
6. [Clean Architecture](#Clean-Architecture)
7. [Design Patterns](#Design-Patterns)
8. [Design Decisions](#Design-Decisions)
9. [Accessibility Report](#Accessibility-Report)
10. [Use of GitHub Features](#Use-of-GitHub-Features)
11. [Code Style and Documentation](#Code-Style-and-Documentation)
12. [Testing](#Testing)
13. [Packaging Strategies](#Packaging-Strategies)
14. [Refactoring](#Refactoring)
15. [Open Questions](#Open-Questions)
16. [Progress Report and Work Allocation](#Progress-Report-and-Work-Allocation)
17. [Final Thoughts](#Final-Thoughts)


## Our Mission <a name="Our-Mission"></a>
Our team is developing an application that facilitates meeting new people with similar interests through conversations. We want to allow our users to connect with other users without experiencing the social pressure induced by social media platforms.

## Specification <a name="Specification"></a>
Our group has chosen to create and develop a social media application that facilitates meeting new people with similar interests through conversations. Our application aims to deliver a platform for users to find, initiate and contribute to conversations through topics that interest them, with a focus on inspiring opportunities for people to meet and make new friends. As such, we want to give users the option of searching for conversations locally or internationally so that groups can have the opportunity to make and meet friends both in person or online. After creating and personalizing an account, users will be able to specify topics of interest. They will then be given the option to join existing groups on these topics. Users can also choose to create a new thread.

## Setup Instructions <a name="Setup-Instructions"></a>

[Setup Walkthrough Video](https://www.youtube.com/watch?v=0o1EFIUH57c)

### Database Setup

1. Set up PostgreSQL on your machine.
2. Create a new database called `newtapp`.
3. Create `users`, `conversations` and `messages` tables in the database (see SQL commands below).

You can complete steps 2 and 3 by running the following in `psql`:
```
CREATE DATABASE newtapp;

\c newtapp

CREATE TABLE conversations (
    id serial PRIMARY KEY,
    title text,
    topics text[],
    location text,
    location_radius int,
    min_rating int,
    max_size int,
    is_open boolean,
    messages int[],
    users int[],
    author_id int
);

CREATE TABLE users (
    id serial PRIMARY KEY,
    username text NOT NULL UNIQUE,
    password text NOT NULL,
    location text,
    interests text[],
    total_rating int,
    num_ratings int,
    following int[],
    followers int[],
    blocked_users int[],
    conversations int[],
    authorities text[],
    rated_users int[]
);

CREATE TABLE messages (
    id serial PRIMARY KEY,
    body text,
    author integer,
    written_at text,
    last_updated_at text,
    conversation_id int
);
```

### Start up the Back-end API Server

1. Navigate to the project directory.
2. `mvn clean compile exec:java`

### Start up the Front-end Web Server

1. Install an up-to-date version of Node.js if you do not already have it.
2. Navigate to `src/front-end/` from the project directory.
3. `npm ci` to install dependencies (don't be alarmed by the warnings, they only concern the dev environment, you can confirm this with `npm audit -production`).
4. `npm start` to run the web server.




## Progress in Phase 2 <a name="Progress-in-Phase2"></a>
Since Phase 2, our group worked towards completing several critical components for our social media application. We began by finishing the remaining tasks in our specifications and user stories. Some of these tasks included:
- Implementing Authentication
- Password Encryption
- Interactors for Message, conversation editing
- Additional sorting algorithms for conversations, such as based on friends or your location

In Phase 2, we continued working on the implementation of our Web Application. This involved:
- Moving to and configuring React
- Familiarizing ourselves with JavaScript and CSS
- Data Transfer Objects for secure transfer of conversation, user and messages
- Deciding essential components of our front-end
- Using React Routes to enable navigation among views of various components

## SOLID Design Principles <a name="SOLID-Design-Principles"></a>
In the development of our App, we considered many uses of SOLID design principles to better demonstrate effective, well-designed code.

The single responsibility principle
   
   - The single responsibility principle states that each class, module, or function should only have full responsibility for a single functionality of the program. The single responsibility principle is evident in the Application Business Rules/Interactors layer, where each User, Message or Conversation interactor is responsible for a specific feature about a user, conversation or a message

Open/Closed principle
  
  - The second design principle highlights how entities should be open for extension, but closed for modification. In our code, through our implementation of Clean Architecture, Entities within the Enterprise Business Rules portray essential basic components of our program. As such, any additional features to our program exist in the Application Business Rules/Interactors layer, allowing for additional features to be easily extended as an Interactor.

The Liskov substitution principle
   
   - Next, the Liskov substitution principle highlights how objects in a program should be replaceable with instances of their subtypes without altering the correctness of that program.
   At the moment, our code does not use the Liskov Substitution Principle, as our program does not make use of subclasses and subtypes with concrete parents. In the future, an additional feature our group might consider implementing is different ???types??? of users, such as premium users or moderators, who would all be of type user and therefore maintain interchangeability.

Interface Segregation principle
   
   - The Interface Segregation principle requires that classes only be able to perform behaviours that are useful to achieve their purpose. In other words, classes do not include behaviours they do not use. In the Application Business Rules layer of our code, we used the Facade design pattern to split interactors so that they only implement specific features, limiting their scope to only the behaviours that they use.

The Dependency Inversion principle
   
   - Finally, the Dependency Inversion Design principle states that high-level modules should depend on abstractions or interfaces rather than low-level modules and that details (like concrete implementations) should depend on abstractions. In our past implementation, we applied this principle in our output boundary interface, which we used to send data from the use cases to the presenter (inner layer to outer layer). However, we changed our application to a Spring Boot application, and now our data output is handled by Spring Boot. The dependency inversion is also a core element of how we access data in our database. In our current implementation, the use cases fetched entities from the database using a data access interface implemented by the various repository associated with each entity. It ensures that the use cases depend not on the database itself but the data access interface.

## Clean Architecture <a name="Clean-Architecture"></a>

   Clean architecture plays a fundamental role in our application. As such, we developed our software so that we can easily modify each component of it without affecting other pieces. While challenging at first, implementing clean architecture throughout our application proved itself essential and extremely useful. Here is an overview of the architecture of our software:

   Our application's first and outermost layer is the web interface and has been implemented for Phase 2. Our front-end of our application provides a user-friendly interface for users to interact with our program. Our front end takes input from the user and sends it to the Spring Boot API server which makes appropriate calls to the controllers.
 
   Following this, the controller transforms the input into a request given to a use case manager responsible for handling use cases. The use cases implement the input boundary interface to handle the request sent by the controller using the manager. It ensures that use cases do not depend on controllers directly but instead on the input boundary interface. Also, since the controllers handle the user's input, the user never directly utilizes the use cases.
Once the use cases accept the request, it fetches the entities from the database using a data access interface. The data access interface implemented by each repository associated with each entity is an explicit use of dependency inversion to maintain a clean architecture.
   Lastly, the output is returned through the controller and the information relevant to the user is displayed on the web application's graphical user interface. 
   
- At the Enterprise Business Rules layer are the core components of the application: `User`, `Conversation`, `Message`.
- At the Application Business Rules layer are the classes `UserManager`, `ConversationManager`, `MessageManager`. The three managers handle Use Cases also in that layer.
- At the Interface Adapters layer are the controllers: `UserController`, responsible for users, as well as `ConversationController`, responsible for handling conversations and messages
- At the Framework & Drivers are the database and the web interface.

Throughout our project, Clean Architecture played a very useful role, as we moved and implemented additional functionalities. Along the way,
- Moving to SpringBoot: Clean Architecture meant we could largely plug in our existing back-end on top of SpringBoot's functionality
- Implementing PostgreSQL: The use of a gateway meant we could easily move from a mock database to using PostgreSQL
- Implementing our frontend using React: The structure of our code allowed us to build our front-end on top of our existing code.

## Design Patterns <a name="Design-Patterns"></a>
Our program displays the use of many important design patterns, to solve important problems effectively and efficiently in our code.

Facade Design Pattern

   - The Facade design pattern provides a simple interface to a complex subsystem, containing many moving parts. Conversations and Users have a lot of small interactors, so to simplify our code, we created ???manager??? facade classes for Conversations and Users to delegate calls to specific interactors as needed, rather than having all of these small interactor methods together in one class.

   - A further feature our group considered for our manager facade is dependency injection, for injecting relevant internal components for our Manager facade. Doing so would make our program more compliant with the Open/Closed Principle, because it would inject relevant input dependencies into our outer manager facade., making it easy to change the specific implementation of our interactors. However, as our different interactors all require specific input and are not uniform across all of the use cases, adding dependency injection would require reworking almost everything, including our request model. As such, considering the number of user and conversation use cases we have, our group decided it was an unrealistic goal to aim for in the completion of Phase 2.

Strategy Design Pattern

   - The Strategy Design pattern facilitates defining families of algorithms in separate classes, while still making their objects interchangeable. An important feature of our app is recommending new conversations based on a User???s interests. So, to sort through which Conversations to recommend we have a few different algorithms in mind. We employed the Strategy Design Pattern for this, creating classes for each of our specific sorting algorithms that implement our ConversationSorter interface. Our application utilizes two Conversation Sorters: 
      - `Interest Sorter`, that assigns priority to conversations based on the number of matching interests
      - `Random Sorter`, which assigns priority to conversations randomly.
  
 
   - In Phase 2, our group added more algorithms for getting relevant conversations to pass to the Interest sorter: 
      - a location sorter `GetRelevantConversationsByLocation`, to recommend conversations matching a user's location
      - a follower sorter, `GetRelevantConversationsByFollow` to recommend conversations a user's friends are in.

Builder Design Pattern
   - Our group considered the use of the Builder Design Pattern for our Interactor Request Model. The builder design pattern is useful for constructing complex objects in steps, allowing for the production of different types and representations of an object using the same construction code. In the case of our Interactors, we use our request model with each use case for receiving and replying to requests for interactions between components of our program. Although this model would be useful for our program, our group decided against implementing this design pattern, because it would require ConcreteBuilder for each use case implementation. In other words, we would not use the same ConcreteBuilder often. As such, we decided it was more important to focus our time on our remaining specifications and towards our front-end.

## Design Decisions <a name="Design-Decisions"></a>
In the further development of our app, we were called to consider many design questions for better optimizing the functionality of our app.

Implementing a WebApp
   - A key design decision our group made was to focus our Social Media App towards a Web Application, to maximize the reach and scope of our app. We specifically decided to use Spring Boot as our back-end API server, because of its easy implementation in Java, and PostgreSQL for the implementation of our Databases.

Int IDs
   - Originally, we planned to pair non-unique usernames with a unique string userID, consisting of the username itself and a number identifier, such as "evan#10". However, in considering how our data will be stored in a database, we decided it would be much simpler to assign each user a unique integer identifier (and conveniently our database can handle the creation of these values for us). In considering these things, we also decided to enforce unique usernames. As it is not clear to us how to easily and securely allow a user with a non-unique username to login to their account using their username.

Request and Response Models for requests between users and interactors
   - After implementing initial Request and Response models for communication between our Presenters and Interfaces, we decided to redesign our input and output models for more simplicity and to implement a cleaner design.
Originally, we had different request models for each interactor, making sending and receiving requests and responses unnecessarily complex. To adjust this, we created one request model for all interactors. However, after further implementing our program as a web app, we then decided to use the request and responses as implemented and handled by Spring Boot.

Message Controller
   - Our group decided not to include a controller for our Message entity, but rather to handle the use cases of messages through the conversation controller.

Implementing our Front-End
   - When implementing our front-end, we originally wanted to use Gatsby for implementing a fast and efficient front-end. However, after further research, we realized Gatsby to be static. As such, we decided to simply stick to React for our front end, as we want our app to be able to update as new chats, conversations, and friends come in.

## Accessibility Report <a name="Accessibility-Report"></a>

   Design principles are essential to design considerations for guiding the design of environments, products and communications. Incorporating design principles make products or applications accessible to the greatest range of people possible, incorporating equitable usages for people of all backgrounds, abilities and education.

Principle 1 - Equitable use: the design is useful and marketable to people with diverse abilities.
  
  - Our app aims to deliver a safe, simple and appealing experience for all users. Some ways we could further implement equitable use is supporting text-to-speech for those unable to type, or by including high-contrast designs or an easily available high-contrast mode for the visually impaired. Implementing a toggle between light and dark modes for our web page could add further customizability for users.

Principle 2 - Flexibility in Use: The design accommodates a wide range of individual preferences and abilities.
 
   - Providing users with the flexibility to configure an app to their preference is an important design consideration. In the future, we could consider making our application accessible from multiple different devices and platforms, giving users the flexibility to use our app to their liking. As such, this would also involve making mobile-friendly designs.

Principle 3 - Simple and Intuitive: The design is easy to understand, regardless of the user's experience, knowledge, language skills, or current concentration level.

   - We hope to design an app that is accessible, equitable and understandable to those of all education levels and languages. Providing the option to translate our app to different languages, or to sort through conversations based on a user's language could aid in promoting intuitive uses for people across the globe. Further, designing a simple, straightforward and familiar app and chat layout can help to make our app more accessible to all, regardless of one's level of focus.

Principle 4 - Perceptible Information: The design communicates necessary information effectively to the user, regardless of ambient conditions or the user's sensory abilities.
   
   - Including a variety of modes for the presentation of information is not only important for making information easily perceivable, but also a nice way to make applications look visually pleasing and appealing. As such, we hope to make important information like creating accounts and logging in easily perceivable on our app, while also using colours to make important UI components stand out to users.
Principle 5 - Tolerance for Error: The design minimizes hazards and the adverse consequences of accidental or unintended actions.
   
   - Features like autocorrection for typing or confirmation of actions are a useful way to minimize accidental or unintended actions. Keeping important information behind multiple pages, like password information or account deletion, is important to prevent accidental touches, or to prevent unintentional loss of progress.

Principle 6 - Low Physical Effort: The design can be used efficiently and comfortably and with a minimum of fatigue.
   
   - Tho this principle largely does not apply to the design of applications, it is important to stay away from requiring extravagant inputs or operating forces from users. For example, users should not be required to click buttons at high rates to interact with our app.

Principle 7 - Size and Space for Approach and Use: Appropriate size and space are provided for approach, reach, manipulation, and use regardless of the user's body size, posture, or mobility.
   
   - Once again, this design principle does not specifically relate to the implementation of a tech app, tho it is important to ensure text and data are presented in a legible, understandable and clear way as to not require extraneous strain from users.
   
   As a social media application, we hope to make our application accessible and usable for all age groups, ranging from teens to adults. Expressing one's opinions and communicating is invaluable and an essential part of human life, so we think our app will apply to all age groups who enjoy learning, interacting with others and sharing opinions.
   
   In particular, we think our app is less likely to be used by younger kids and older adults, who are either too young to be on social media platforms and to contribute and understand conversations in a meaningful way, or of age where they find social media as uninteresting or un-useful.

## Use of GitHub Features <a name="Use-of-GitHub-Features"></a>
   Throughout the development of our web app, our group has been making use of several features available on GitHub. One such feature is branching. In phase 2, we made branches for each new feature that we implemented. Once the feature had been implemented, we used pull requests to provide opportunities for code review, before merging the development branch into our main branch.
   
   ![Feature Branching](https://github.com/CSC207-UofT/course-project-newtapp/blob/main/phase2/images/Feature%20branching.JPG)
   
   In addition, we initially only used the projects feature on GitHub to delegate work and ensure that each member knew what the other members were doing. As we progressed through the project we found that we could also make use of the issues feature as well and that the issues could also be items in the projects tab that can be moved as necessary.
   In Phase 1, our group decided to transition to a new repository for our Spring Boot transition. We wanted to separate our previous project from the transitioned Spring Boot project so we created a new repository called course-project-newtapp, whereas our old repository was called course-project-optimistic-newts.
   
   ![GitHub Projects](https://github.com/CSC207-UofT/course-project-newtapp/blob/main/phase2/images/Projects%20Tab.JPG)
   
   ![GitHub Issues](https://github.com/CSC207-UofT/course-project-newtapp/blob/main/phase2/images/issues%20tab.JPG)
   
## Code Style and Documentation <a name="Code-Style-and-Documentation"></a>
   Following Phase 2, our code continues to have a number of IntelliJ unused method warnings, but please know that we are aware of each warning. They mostly reflect unused code relating to parts of our program to be used for future functionality. All of the code accessible through our API server have no warnings that we have left unintentionally.

## Testing <a name="Testing"></a>
   
   In phase 2, our group worked towards adding more tests for our program. 

   ![Feature Branching](https://github.com/CSC207-UofT/course-project-newtapp/blob/main/phase2/images/Test%20Coverage.jpg)

## Packaging Strategies <a name="Packaging-Strategies"></a>
   Our group has decided to package by the layers of Clean Architecture, to allow for easy sorting between the functionality and purpose of methods. Since our code was created to fulfil thoughtful and organized layers as demonstrated in Clean Architecture, packaging by layers allows for clear, concise and organized file sorting. 
   
   In Phase 2, we further decided to nest specific layers of Architecture, to make code organization and location faster and more efficient.

## Refactoring <a name="Refactoring"></a>

We did a fair share of refactoring for Phase 2. Here are some examples of refactoring we did:

- Request Model (https://github.com/CSC207-UofT/course-project-optimistic-newts/pull/37): The first version of our request model was overly complicated. We simplified it a lot by making one request model format that all Use Cases can implement. In our current request model, our Use Cases only need to implement an Input Boundary so that they can accept a request. The controller now fills the request and sends it to the appropriate Use Case.
Spring Boot (this repository ): We transformed our original application into a Spring Boot application to facilitate the implementation of a web user interface and a database. Also, Spring boots handling of HTTP requests has proven itself to be very convenient.

- Constants (https://github.com/CSC207-UofT/course-project-optimistic-newts/pull/35): We refactored our constants' names to fit the naming convention.

- Constructor (https://github.com/CSC207-UofT/course-project-newtapp/commit/5a4191f7db6457c287c59a979ee9785a22f16d82): We used refactoring of the constructor to delete the use of an obsolete variable, LocationRadius.

## Open Questions <a name="Open-Questions"></a>

   As our group worked towards our final product for Phase 2, we have learnt a lot about Web Apps, Databases and their implementations and relations. As such, we have some further questions about what the next steps in the development pipeline could look like.
Bringing an app to the public
   - Based on our already existing front and back ends, what are the steps to finally bring an app to the public?
   - What kind of database would we use in a real implementation of our app? What is the best database hosting platforms?
   - How and where would we host the website itself?
   - Are there any remaining security or functionality considerations we would need to make?

## Progress Report and Work Allocation <a name="Progress-Report-and-Work-Allocation"></a>

   In Phase 2, we worked towards finalizing all the remaining fundamental components of our application and turned our development to transferring/implementing a Web App front-end using React.
   Our team has continued to work effectively in completing our Specification and moving our program to a Web App. Our team has continued to work well as a group in communicating amidst ourselves and in dividing the work between us.
   
Alex
   - In Phase 2, I aided in our front-end development. I worked on implementing DataTransfer Objects for Conversation and Message Data, as secure ways of transferring information from higher layers of the program to the Front-End. I also implemented more sorting algorithms for our ConversationSorter Strategy Design Pattern. Lastly, I worked on the final changes for the design document and prepared slides for our presentation.
An important Pull Request I made throughout this project was with the design document, which plays an extremely important role in discussing the work of our team during this project.
https://github.com/CSC207-UofT/course-project-newtapp/pull/112

Spencer
   - For phase 2, I worked on implementing password encryption, user authentication, and the front end. I enjoyed working on these things because they were each very interesting topics to learn about that are not specifically part of this course.

   - A significant PR I made during this project is linked below. This pull request implemented the Authentication features that enable our API to be used securely without requiring username+password credentials on each secured request.
https://github.com/CSC207-UofT/course-project-newtapp/pull/62

Will
   - Since phase 1, I tried helping with the front-end of our app but soon realized that I am a lot more efficient in the back-end. Consequently, I finished implementing our User Controller, wrote tests for use cases and fixed bugs in our app.

   - A significant PR I made during this project is implementing our database. This Pull request implements our Postgres database through Spring boot to store messages. It demonstrates a significant contribution to the team because it shows that I contributed to setting up our Spring Boot app and our database, both essential for our app.
https://github.com/CSC207-UofT/course-project-newtapp/commit/91d3406b37efabaff08150aa512eb5d27af08875

Ted
   - For phase 2, I worked on the backend - implemented some remaining message interactors necessary for the app, edited conversation manager to use those interactors, and implemented conversation controller as well as necessary conversation and message data assemblers.
   - A significant pull request I made in the development of our app was for implementing the ConversationController. This handles the mapping between the front end and the backend.
https://github.com/CSC207-UofT/course-project-newtapp/pull/81

Yukthi
   - For phase 2 I primarily worked on the backend, focusing on finishing the use case classes as well as adding tests to already implemented use case classes. I    enjoyed being able to implement the backend functionality responsible for the actions that our users may choose to do on the app.
   - A significant PR I made during this project was for the Delete User Interactor. This interactor is a use case involved in deleting a user from the database, along with removing any references and relationships associated with that user. This included changing conversation authors, removing from followed lists, as well as removing from following lists.
https://github.com/CSC207-UofT/course-project-newtapp/pull/32

Zachary
   - For phase 2, I worked on implementing the mock repositories to be able to test all the interactors. I also worked on implementing tests for the interactors. Also spent some time creating the logo for our app and helping in the creation of the presentation.
   - A significant pull request I made in the development of our app was for the conversation class and some of the test cases. This pull request still plays a significant role in the functionality of the program currently.
https://github.com/CSC207-UofT/course-project-optimistic-newts/pull/6

## Final Thoughts <a name="Final-Thoughts"></a>

Our team has enjoyed working on this project and has learnt invaluable tools and skills about the implementation and functionality of web apps and databases. Our team looks forwards to seeing where we can take our idea further in the future. 

Thank you for your continued help, support and time Evan. We have all really enjoyed working with you and having you as our TA. Your aid and strong interest for web apps has been incredibly helpful and we are infinitely grateful for your help in guiding us towards a web app. Hope to see you in future CS courses! :)
