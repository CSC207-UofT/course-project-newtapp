import '../../../App.css';
import React from "react";

// A component that displays a message. Input is a message.
// Nest step is dynamically sizing these to fit title/content!
function Message({ message }) {
    return (
        <>
            <div className="message">
                <div className="messageBox">
                    {message.body}
                </div>
            </div>
            <div className="messageSidebar">
                <div className={"messageSidebarAuthor"}>
                    {message.author}
                </div>
                <div className={"messageSidebarEditButton"}>
                    <button className="newtEditButton">Edit</button>
                </div>
            </div>
        </>
    )
}

export default Message;