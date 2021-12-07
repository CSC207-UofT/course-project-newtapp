import '../../../App.css';
import React from "react";
import ConversationTileTopic from "./conversationTileTopic";

// A component that displays a collection of ConversationTiles. Input is an array of ConversationProfiles.
// Next step is dynamically sizing these to fit title/content!
function ConversationTile({ conversation }) {
    let i = 0;
    const topics = conversation.topics.map((topic) =>
        <ConversationTileTopic key={i++} topic={topic}/> )

    return (
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
                    <div className="conversationTileJoinButton">
                        <button className="newtJoinButton">Join</button>
                    </div>
                </div>
            </div>
        </>
    );
}

export default ConversationTile;