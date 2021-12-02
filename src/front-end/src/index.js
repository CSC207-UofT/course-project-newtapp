import { render } from "react-dom";
import {
    BrowserRouter,
    Routes,
    Route
} from "react-router-dom";
import App from "./App";
import Browse from "./routes/browse"
import Login from "./routes/login";
import CreateUser from "./routes/createUser";

const rootElement = document.getElementById("root");
render(
    <BrowserRouter>
        <Routes>
            <Route path="/" element={<App />} />
            <Route path="browse" element={<Browse />} />
            <Route path="login" element={<Login />} />
            <Route path="login/create" element={<CreateUser />} />
        </Routes>
    </BrowserRouter>,
    rootElement
);
