import './App.css';
import UserSearchForm from "./components/usersearch";
import CreateUserForm from "./components/createuser";

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
