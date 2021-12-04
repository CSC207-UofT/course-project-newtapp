import '../App.css';
import CreateUserForm from "../components/forms/createUserForm";
import Layout from "../components/layout";
import LoginLayout from "../components/loginLayout";
import React from "react";
import Button from "../components/Button";

export default function CreateUser() {
    return (
        <LoginLayout>
            <div className="loginSideButtonContainer">
                <button onClick="d" className="loginSideButton">Login</button>
                <button onClick="" className="loginSideButton">Create</button>
            </div>
            <CreateUserForm />
        </LoginLayout>
    );
}