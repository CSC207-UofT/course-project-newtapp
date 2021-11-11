## Newt

---

An app for meeting people and having conversations.

---

**Database setup instructions**:

1. Set up PostgreSQL on your machine.
2. Create a user called `optimisticnewt` with password `newtdb`.
3. Create a new database called `newtapp`.
4. Give user `optimisicnewt` privileges on `newtapp` database.

You can do steps 2-4 by running the following in `psql`:
```
CREATE DATABASE newtapp;
CREATE USER optimisticnewt WITH ENCRYPTED PASSWORD 'newtdb';
GRANT ALL PRIVILEGES ON DATABASE newtapp TO optimisticnewt;
```
