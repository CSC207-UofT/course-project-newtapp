import '../App.css';
import Layout from "../components/layouts/layout";
import CookieCheck from "../components/cookieCheck";
import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import newtApi from "../api";
import {useCookies} from "react-cookie";
import MessageList from "../components/conversation/messages/messageList";
import CreateMessageForm from "../components/forms/createMessageForm";

export default function Messenger() {
    const { id } = useParams()
    const cookies = useCookies(["Auth"])[0];
    const [sent, setSent] = useState(false);
    const [loaded, setLoaded] = useState(false);
    const [conversation, setConversation] = useState({title: null});

    useEffect(() => {
        async function getConversation() {
            setSent(true);
            setConversation(await newtApi.getConversationData(cookies, id));
        }
        if (!loaded && !sent) {
            getConversation().then();
        } else if (conversation.title !== null) {
            setLoaded(true);
        }
    }, [sent, loaded, conversation, cookies, id])

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
                    <MessageList messageArray={conversation.messageData} userProfiles={conversation.userProfiles}/>
                    <CreateMessageForm id={conversation.id}/>
                    </div>
                </Layout>
            </>
        )
    }
}