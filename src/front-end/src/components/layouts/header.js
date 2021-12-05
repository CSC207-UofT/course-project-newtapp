import {Link} from "react-router-dom";
import logo from '../../images/logo128.png';
import {useCookies} from "react-cookie";
import authUtil from "../../auth";

export default function Header() {
    const cookies = useCookies(['Auth'])[0];
    const username = authUtil.getUsername(cookies.Auth);
    const profile = "/" + username;
    return (
        <>
        <nav>
            <span className="navLinks">
            <Link to="/browse" className="navLink">Browse</Link>
            <Link to="/friends" className="navLink">Friends</Link>
            <Link to="/conversations" className="navLink">Conversations</Link>
            </span>
            <Link to="/"><img src={logo} alt="Logo" className="navLogo"/></Link>
            <Link to={profile}><img src={logo} alt="Logo" className="navUserPhoto"/></Link>
        </nav>
        </>
    );
}