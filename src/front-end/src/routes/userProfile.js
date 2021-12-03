import '../App.css';
import React, {useEffect, useRef, useState} from 'react'
import {useParams, Navigate, useNavigate} from "react-router-dom";
import Layout from "../components/layout";
import newtApi from "../api";

const UserProfile = () => {
    const { username } = useParams()
    const [sent, setSent] = useState(false);
    const [loaded, setLoaded] = useState(false);
    const [user, setUser] = useState(null);

    useEffect(() => {
        async function getUser() {
            setSent(true);
            setUser(await newtApi.getUser(username));
            setLoaded(true);
        }
        if (!loaded && !sent) {
            getUser();
        } else if (loaded && user.id == null) {
            setSent(false);
        }
    }, [sent, loaded, user, username])

    if (!loaded) {
        return (
            <Layout>
            </Layout>
        )
    } else if (loaded && !sent) {
        return (
            <Navigate to="/" />
        )
    } else {
        return (
            <Layout>
                <p>{user.username}</p>
            </Layout>
        )
    }
}

export default UserProfile