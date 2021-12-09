# Progress Report

### Specification 
  Our group has chosen to create and develop a social media application that facilitates meeting new people with similar interests through conversations. 
Our application aims to deliver a platform for users to find, initiate and contribute to conversations through topics that interest them, with a focus on inspiring opportunities for people to meet and make new friends. As such, we want to give users the option of searching for conversations locally or internationally so that groups can have the opportunity to make and meet friends both in person or online. 
After creating and personalizing an account, users will be able to specify topics of interest. They will then be given the option to join existing groups on these topics. Users can also choose to create a new thread.

### CRC model
  During our first two weeks of development, we established a CRC model to highlight key components of our program and their interrelations. At the Enterprise Business Rules layer are the core components of our application: Users, Conversations, Messages and the ConversationQueue. Next, at the Application Business Rules layer are the classes UserManager, ConversationManager, MessageManager and ConversationQueueManager, which serve as interactors between our Entities and Interface Adapters. Our four managers handle Use Cases through the implementation of an input boundary interface, which is implemented by CliDriver and is utilized by the Controller to get inputs. 

### Scenario walk-through and skeleton program
  Our scenario walkthrough and skeleton program showcase an initial use of our application through a Command Line Interface. The user is able to run and interact with the program by responding to prompts through the CLI. A user can create an account by specifying a username and password and is prompted to complete their profile information with a location and interests. After creating an account, the user has the option of browsing their conversations, seeing a list of their friends, or they can view their profile and edit information (these features are not yet implemented in our Skeleton Program). When finished navigating through the app, a user can log out. 

### Open Question
  One open design question our group is considering is: How will we deal with storing objects to be accessed later? Since our project is intended to be a social network, we need our entities to persist past a single execution of the program.
  
### What has worked well
  So far, our team has been effective in implementing our skeleton program and dividing the work among ourselves. Clean Architecture has provided us with a better understanding of the design benefits of separating functionality into different components. Despite being in the initial stages of our project, Clean Architecture has proven itself useful in altering small implementation details, without having to rewrite code at every layer of our program. 

### Work-allocation
  First, we all met in person, where we engaged in open discussions to organize our ideas and to establish the purpose and aim of our project. Following this, we met online where we all contributed to an initial rough CRC model, and divided implementing a skeleton program amidst ourselves:

To implement the Scenario Walk-Through we divided the work in the following way:
- User class - Yukthi
- Conversation class - Zachary
- ConversationQueue class - Spencer
- Message class - Will
- CreateUserInteractor class - Alex
- UserLoginInteractor class- Alex
- CreateConversationInteractor class - Ted
- GetRelevantConversationsInteractor class - Ted
- Controller/Presenter class - Spencer
- Main method - Spencer

Finally, in preparation for our Phase 0 meeting, we further divided the remaining final edits and polishes among ourselves:
- Progress Report - Alex 
- Interactor Refactoring - Ted
- CRC edits and Walkthrough - Zach
- Specification - Will and Yukthi
- Finishing up Skeleton Code - Spencer

### Looking forward
We are all looking forward to continuing the development of our application. Here are some ideas of the next steps in our development:
- Implementing remaining interactions between users - Yukthiw
- Reworking our temporary database for conversations and users / Data Access Interface - Alex
- Reworking the interactors/request model - Ted
- Rethinking the interface adapter layer as it currently violates Clean Architecture - Spencer
- Implement Message Interactors - Will
- Implement Conversation Interactors - Zach 

After we finalize all the remaining fundamental components of our application, we can then turn our attention to transferring/implementing our app to an accessible format for users. Could include:
- Transferring our work to a web app → Design / UI choices.
- Deciding what kind of database we want to use.
- The design differences between the frontend of the app and the backend.
- Considering how we will deal with user privacy/security.

Name for the app? A preliminary idea: Render ( tinder and Reddit )
	
