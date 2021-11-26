import React from 'react'
import UserSearchForm from '../components/usersearch'
import {
    container,
    heading
} from './index.module.css'

const IndexPage = () => {
    return (
        <div className={container}>
            <main>
                <title>Home Page</title>
                <h1 className={heading}>Newt Web App!</h1>
                <hr />
                <UserSearchForm />
            </main>
        </div>
    )
}

export default IndexPage