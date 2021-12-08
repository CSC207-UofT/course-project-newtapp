import '../../../App.css';
import React from "react";
import Message from "./message";

// A component that displays list of supplied messages
function MessageList({ messageArray }) {
    const messages = messageArray.map((message) => <Message key={message.id} message={message}/> )

    return (
        <>
            <div className="messageList">
                {messages}
            </div>
        </>
    );
}

export default MessageList;