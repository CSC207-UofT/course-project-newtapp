import '../../../App.css';
import React from "react";
import ConversationTile from "./conversationTile";

// A component that displays one provided ConversationProfile
function ConversationList({ conversations }) {
    const tiles = conversations.map((conversation) => <ConversationTile key={conversation.id} conversation={conversation}/> )


    return (
        <>
            <div className="conversationList">
                {tiles}
            </div>
        </>
    );
}

export default ConversationList;