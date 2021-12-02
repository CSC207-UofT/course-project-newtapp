import {Link} from "react-router-dom";

export default function Header() {
    return (
        <>
        <nav
            style={{
                borderBottom: "solid 1px",
                paddingBottom: "1rem"
            }}
        >
            <span>Newt</span>
            <span style={{"float": "right"}}>
            <Link to="/login">Login</Link> |{" "}
            <Link to="/login/create">Create</Link>
            </span>
        </nav>

        </>
    );
}