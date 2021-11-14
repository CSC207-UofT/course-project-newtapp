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




