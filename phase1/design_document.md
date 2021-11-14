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

-Changing User IDs to be unique integer ID’s for easier sorting/management in the database
	-Originally, we planned to use emails for login. We planned to use non-unique usernames, with unique string IDs consisting of the username and a number indicating the chronological order of the user. (Eg. a first alex to create an account would be Alex#01, then if another Alex created an account he would be Alex#02.) We originally decided on this because we wanted to use the compareTo method to compare the User Dds lexicographically based on the Unicode value of each character in the strings.
-We found using id’s to sort through in the database to be easier




