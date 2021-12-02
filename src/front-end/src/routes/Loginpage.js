import './App.css';
import React from 'react';
import Button from "../components/Button";
import {Link} from "react-router-dom";
import CreateUserForm from "../components/createuser";
import LoginUserForm from "./Loginuser";

function login (){
    return (
        <div className='container'>
            <h1>{'Welcome to Newt App'}</h1>
            <Button
                color={'blue'}
                text={'Login'}
                onClick={<Link to="/login">Login</Link>}
            />
            <Button
                color={'blue'}
                text={'Create an account'}
                onClick={<CreateUserForm />}
            />
            <LoginUserForm />
            <CreateUserForm />
        </div>
    );
}