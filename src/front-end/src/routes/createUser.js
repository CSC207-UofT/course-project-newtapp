import '../App.css';
import CreateUserForm from "../components/forms/createUserForm";
import Layout from "../components/layouts/layout";
import LoginLayout from "../components/layouts/loginLayout";
import React from "react";

export default function CreateUser() {
    return (
        <LoginLayout>
            <CreateUserForm />
        </LoginLayout>
    );
}