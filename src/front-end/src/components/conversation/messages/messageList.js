import '../../../App.css';
import React from "react";
import Message from "./message";

// A component that displays list of supplied messages
function MessageList({ messageArray, userProfiles }) {
    let userMap = {}
    for (const user of Object.values(userProfiles)) {
        userMap[user.id] = user.username;
    }

    let messages;
    if (messageArray !== []) {
        messages = messageArray.map((message) => <Message key={message.id} message={message}
                                                          userMap={userMap}/> )
    } else {
        messages = null;
    }

    return (
        <>
            <div className="messageList">
                {messages}
            </div>
        </>
    );
}

export default MessageList;