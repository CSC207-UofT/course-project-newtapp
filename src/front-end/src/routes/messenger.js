import '../App.css';
import Layout from "../components/layouts/layout";
import CookieCheck from "../components/cookieCheck";
import React, {useEffect, useState} from "react";
import newtApi from "../api";
import {useCookies} from "react-cookie";

export default function Messenger() {
    const cookies = useCookies(["Auth"])[0];
    const [sent, setSent] = useState(false);
    const [loaded, setLoaded] = useState(false);
    const [messages, setMessages] = useState([]);
    const [conversation, setConversation] = useState({});

    useEffect(() => {
        async function getConversation() {
            setSent(true);
            setConversations(await newtApi.getRelevantConversations(cookies));
        }
        if (!loaded && !sent) {
            getConversations().then();
        } else if (conversations !== []) {
            setLoaded(true);
        }
    }, [sent, loaded, conversations, cookies])

    if (!loaded) {
        return (
            <>
                <CookieCheck />
                <Layout>
                </Layout>
            </>
        )
    } else {
        return (
            <>
                <CookieCheck />
                <Layout>
                    <ConversationList conversations={conversations} />
                </Layout>
            </>
        )
    }
}