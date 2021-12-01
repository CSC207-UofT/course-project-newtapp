## Newt

---

An app for meeting people and having conversations.

---

**Database setup instructions**:

1. Set up PostgreSQL on your machine.
2. Create a user called `optimisticnewt` with password `newtdb`.
3. Create a new database called `newtapp`.
4. Give user `optimisticnewt` privileges on `newtapp` database.
5. Create User, Conversation and Message tables in the database (see SQL commands below).
6. Assign data source to User, Conversation and Message.

You can complete steps 2-4 by running the following at the command line in `psql`:
```
CREATE DATABASE newtapp;
CREATE USER optimisticnewt WITH ENCRYPTED PASSWORD 'newtdb';
GRANT ALL PRIVILEGES ON DATABASE newtapp TO optimisticnewt;
```

SQL table creation commands for step 5:

```
CREATE TABLE conversations (
    id serial PRIMARY KEY,
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
    authorities text[]
)

CREATE TABLE messages (
    id serial PRIMARY KEY,
    body text,
    author integer,
    written_at text,
    last_updated_at text
)
```
