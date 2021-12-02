import {Link} from "react-router-dom";
import logo from '../images/logo.png';

export default function Header() {
    return (
        <>
        <nav>
            <span className="navLinks">
            <Link to="/browse" className="navLink">Browse</Link>
            <Link to="/login/create" className="navLink">Friends</Link>
            <Link to="/login/create" className="navLink">Conversations</Link>
            </span>
            <Link to="/login"><img src={logo} alt="Logo" className="navLogo"/></Link>
            <Link to="/login"><img src={logo} alt="Logo" className="navUserPhoto"/></Link>
        </nav>
        </>
    );
}