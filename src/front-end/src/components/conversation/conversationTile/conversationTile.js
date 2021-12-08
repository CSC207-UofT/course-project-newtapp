import '../../../App.css';
import React, {useState} from "react";
import ConversationTileTopic from "./conversationTileTopic";
import {useCookies} from "react-cookie";
import {Navigate} from "react-router-dom"
import newtApi from "../../../api";

// A component that displays a collection of ConversationTiles. Input is an array of ConversationProfiles.
function ConversationTile({ conversation, buttonType }) {
    const cookies = useCookies(["Auth"])[0];
    const [redirect, setRedirect] = useState(false);
    const navTo = `/conversations/${conversation.id}/view`

    let i = 0;
    const topics = conversation.topics.map((topic) =>
        <ConversationTileTopic key={i++} topic={topic}/> )

    let buttonFunction;

    switch (buttonType) {
        case "Chat":
            buttonFunction = handleChat;
            break;
        case "Join":
            buttonFunction = handleJoin;
            break;
        default:
            buttonType = ""
            break;
    }

    function handleJoin() {
        newtApi.joinConversation(cookies, conversation.id);
        setRedirect(true);
    }

    function handleChat() {
        setRedirect(true);
    }

    if (buttonType === "") {
        return(
            <>
            <div className="conversationTile">
                <div className="conversationTileTitleBox">
                    <h2 className="conversationTileTitle">{conversation.title}</h2>
                </div>
                <div className="conversationTileTopics">
                    {topics}
                </div>
                <div className="conversationTileSidebar">
                    <div className="conversationTileSidebarExtra">
                        {conversation.location}<p className="emoji"> &#x1F30D;</p>
                    </div>
                    <div className="conversationTileSidebarExtra">
                        {conversation.currSize} / {conversation.maxSize}<p className="emoji"> &#x1F438;</p><
                        /div>
                </div>
            </div>
        </>
        )
    } else if (redirect) {
        return(
            <>
                <Navigate to={navTo}/>
            </>
        )
    } else {
        return(
            <>
                <div className="conversationTile">
                    <div className="conversationTileTitleBox">
                        <h2 className="conversationTileTitle">{conversation.title}</h2>
                    </div>
                    <div className="conversationTileTopics">
                        {topics}
                    </div>
                    <div className="conversationTileSidebar">
                        <div className="conversationTileSidebarExtra">
                            {conversation.location}<p className="emoji"> &#x1F30D;</p>
                        </div>
                        <div className="conversationTileSidebarExtra">
                            {conversation.currSize} / {conversation.maxSize}<p className="emoji"> &#x1F438;</p><
                    /div>
                        <div className="conversationTileButton">
                            <button className="newtBigButton" onClick={buttonFunction}>{buttonType}</button>
                        </div>
                    </div>
                </div>
            </>
        )
    }
}

export default ConversationTile;