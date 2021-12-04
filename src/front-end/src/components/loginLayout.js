import React, {useEffect} from 'react'
import Header from "./header";
import newtApi from "../api";

const LoginLayout = ({ children }) => {
    return(
        <>
            <main className="loginMain">
                <div className="loginSideDiv">
                    <div className="loginSideContent">
                        <div className="loginSideText">
                            <h2>Welcome to Newt!</h2>
                            <p>Please log in or create a new account.</p>
                        </div>
                        <div className="loginSideButtons">

                        </div>
                        <div className="loginSideChildren">
                            {children}
                        </div>
                    </div>
                </div>
            </main>
        </>
    )
}

export default LoginLayout