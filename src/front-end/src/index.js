import { render } from "react-dom";
import {
    BrowserRouter,
    Routes,
    Route
} from "react-router-dom";
import {CookiesProvider} from "react-cookie";
import NotFound from "./routes/notFound";
import App from "./App";
import Login from "./routes/login";
import CreateUser from "./routes/createUser";
import Browse from "./routes/browse"
import UserProfile from "./routes/userProfile";



const rootElement = document.getElementById("root");
render(
    <CookiesProvider>
        <BrowserRouter>
            <Routes>
                <Route path="*" element={<NotFound />} />
                <Route path="/" element={<App />} />
                <Route path="/login" element={<Login />} />
                <Route path="/create/user" element={<CreateUser />} />
                <Route path="/browse" element={<Browse />} />
                <Route path="/:username" element={<UserProfile />} />
            </Routes>
        </BrowserRouter>
    </CookiesProvider>,
    rootElement
);
