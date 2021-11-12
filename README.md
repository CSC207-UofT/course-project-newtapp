## Newt

---

An app for meeting people and having conversations.

---

**Database setup instructions**:

1. Set up PostgreSQL on your machine.
2. Create a user called `optimisticnewt` with password `newtdb`.
3. Create a new database called `newtapp`.
4. Give user `optimisicnewt` privileges on `newtapp` database.
5. Create User, Conversation and Message tables in your database by running the below sql commands.
6. Assign data source to User, Conversation and Message.

You can do steps 2-4 by running the following in `psql`:
```
CREATE DATABASE newtapp;
CREATE USER optimisticnewt WITH ENCRYPTED PASSWORD 'newtdb';
GRANT ALL PRIVILEGES ON DATABASE newtapp TO optimisticnewt;
```

Sql commands for step 5:

Conversation table,
```
CREATE TABLE conversation (
    Id serial PRIMARY KEY,
    title text,
    topics text[],
    location text,
    location_radius int,
    min_rating int,
    max_size int,
    closing_time text,
    is_open boolean,
    messages int[],
    users int[]
)
```

User table,
```
```

Message table,
```
```