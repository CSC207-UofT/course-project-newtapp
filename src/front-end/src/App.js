import './App.css';
import React from 'react';
import {Route, Routes} from 'react-router-dom';
import HomePage from './pages/HomePage';
import UserPage from './pages/UserPage';
import UserSearchForm from "./components/usersearch";
import CreateUserForm from "./components/createuser";

export default function App() {
    return (
        <Routes>
            <Route path='/welcome' element={<HomePage/>} />
            <Route path="/:id" element={<UserPage/>} />
        </Routes>
    )
}

/*
function App() {
  return (
    <div className="App">
        <div className="container">
            <main>
                <title>Home Page</title>
                <h1 className="heading">Newt Web App!</h1>
                <hr />
                <UserSearchForm />
                <hr />
                <CreateUserForm />
            </main>
        </div>
    </div>
  );
}

export default App;
*/
