import React, {useEffect} from 'react'
import Header from "./header";
import newtApi from "../api";

const LoginLayout = ({ children }) => {
    return(
        <>
            <main className="loginMain">
                {children}
            </main>
        </>
    )
}

export default LoginLayout