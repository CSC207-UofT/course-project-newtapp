import '../App.css';
import React from 'react';
import LoginLayout from "../components/loginLayout";
import LoginForm from "../components/forms/loginForm";

function Login () {
    return (
        <LoginLayout>
            <LoginForm />
        </LoginLayout>
    );
}

export default Login