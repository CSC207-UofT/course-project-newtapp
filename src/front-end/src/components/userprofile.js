import React from 'react'

//temporary condensed user profile for use with usersearch
const UserProfile = ({ userData, children }) => {
    if (Object.keys(userData).length === 0) {
        return (
            <div>{children}</div>
        )
    } else {
        return (
            <div>
                <table cellPadding={5}>
                    <tbody>
                        <tr>
                            <td>id</td>
                            <td>{userData.id}</td>
                        </tr>
                        <tr>
                            <td>username</td>
                            <td>{userData.username}</td>
                        </tr>
                        <tr>
                            <td>Location</td>
                            <td>{userData.location}</td>
                        </tr>
                    </tbody>
                </table>
                {children}
            </div>
        )
    }
}

export default UserProfile