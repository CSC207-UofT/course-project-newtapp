import '../App.css';
import React from 'react';
import LoginLayout from "../components/loginLayout";
import CreateUserForm from "../components/forms/createUserForm";

function Login () {
    return (
        <LoginLayout>
            <CreateUserForm />
        </LoginLayout>
    );
}

export default Login