import '../../App.css';
import logo from "../../images/logo.png";
import React from "react";
import ConversationTileTopic from "../conversation/conversationTile/conversationTileTopic";

function Profile({ userData }) {
    // TODO: implement dynamic Profile based on relationship to user (follows/blocked/is me)
    let i = 0;
    const topics = userData.interests.map((topic) =>
        <ConversationTileTopic key={i++} topic={topic}/> )

    return (
        <>
            <div className="userProfileBanner">
                <span className="userProfilePhotoContainer">
                    <img src={logo} alt="Logo" className="userProfilePhoto"/>
                </span>
                <span className="userProfileNameContainer">
                    <h1 className={"userProfileName"}>{userData.username}</h1>
                </span>
                <span className="userProfileFollowButton">
                    <button className="newtButton">Follow</button>
                </span>
            </div>
            <div className="userProfileContent">
                <div className="userProfileInfoPanel">
                    <ul className="userProfileInfo">
                        <li>Followers: {userData.followers.length}</li>
                        <li>Following: {userData.following.length}</li>
                        <li>Location: {userData.location}</li>
                        <li>Interests:</li>
                    </ul>
                    <div className="userProfileInterests">
                        {topics}
                    </div>
                </div>
                <div className="userProfileConversationsPanel">
                </div>
            </div>
        </>
    );
}

export default Profile;