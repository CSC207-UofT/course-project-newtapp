import React from 'react'

const UserProfile = ({ userData, children }) => {
    if (userData.length === 0) {
        return (
            <div>{children}</div>
        )
    } else {
        return (
            <div>
                <table cellPadding={5}>
                    <tr>
                        <td>id</td>
                        <td>{userData[0]}</td>
                    </tr>
                    <tr>
                        <td>username</td>
                        <td>{userData[1]}</td>
                    </tr>
                    <tr>
                        <td>Location</td>
                        <td>{userData[2]}</td>
                    </tr>
                </table>
                {children}
            </div>
        )
    }
}

export default UserProfile