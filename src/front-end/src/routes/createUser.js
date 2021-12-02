import '../App.css';
import CreateUserForm from "../components/createuser";
import Layout from "../components/Layout";

export default function CreateUser() {
    return (
        <Layout>
            <h2>Create an account</h2>
            <CreateUserForm />
        </Layout>
    );
}