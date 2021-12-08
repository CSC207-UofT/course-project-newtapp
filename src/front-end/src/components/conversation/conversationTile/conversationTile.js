import '../../../App.css';
import React from "react";
import ConversationTileTopic from "./conversationTileTopic";

// A component that displays a collection of ConversationTiles. Input is an array of ConversationProfiles.
// Next step is dynamically sizing these to fit title/content!
function ConversationTile({ conversation, buttonType }) {
    let i = 0;
    const topics = conversation.topics.map((topic) =>
        <ConversationTileTopic key={i++} topic={topic}/> )

    let buttonClass;
    let buttonFunction;

    switch (buttonType) {
        case "Chat":
            buttonFunction = {};
            break;
        case "Join":
            buttonFunction = {};
            break;
        default:
            buttonFunction = {};
            buttonType = ""
            break;
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