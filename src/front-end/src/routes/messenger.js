import '../App.css';
import Layout from "../components/layouts/layout";
import CookieCheck from "../components/cookieCheck";
import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import newtApi from "../api";
import {useCookies} from "react-cookie";

export default function Messenger() {
    const { id } = useParams()
    const cookies = useCookies(["Auth"])[0];
    const [sent, setSent] = useState(false);
    const [loaded, setLoaded] = useState(false);
    const [conversation, setConversation] = useState({});
    const [messages, setMessages] = useState([]);


    useEffect(() => {
        async function getConversation() {
            setSent(true);
            setConversation(await newtApi.getConversation(id));
        }
        if (!loaded && !sent) {
            getConversation().then();
        } else if (conversation.title !== null) {
            setLoaded(true);
            setMessages(true);
        }
    }, [sent, loaded, conversation, messages, cookies, id])

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
                    <div className={"messenger"}>
                    <MessageList messageData={conversation.messages}/>
                    <createMessageForm/>
                    </div>
                </Layout>
            </>
        )
    }
}