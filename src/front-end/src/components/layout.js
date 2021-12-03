import React, {useEffect} from 'react'
import Header from "./header";

const Layout = ({ children }) => {
    return(
        <>
            <Header />
            <main className="mainContainer">
                {children}
            </main>
        </>
    )
}

export default Layout