import '../App.css';
import React from 'react';
import {useCookies} from "react-cookie";
import {Navigate} from "react-router-dom";

// quick check for any Auth cookie. If no cookie we can quickly redirect to
// log in before doing anything else.
export default function CookieCheck() {
    const [cookies, setCookie] = useCookies(["Auth"]);
    if (cookies.Auth === undefined){
        setCookie("Auth", null, {path: '/'});
        return (<Navigate to="/login" replace={true} />)
    }
    return (
        <></>
    );
}