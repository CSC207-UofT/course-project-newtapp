import React from 'react'
import Header from "./header";

const Layout = ({ children }) => {
    return(
        <>
        <div className="container">
            <title>Newt</title>
            <Header />
        </div>
        <main className="container">
            {children}
        </main>
        </>
    )
}

export default Layout