import '../../App.css';
import logo from "../../images/logo.png";
import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import newtApi from "../../api";
import {useCookies} from "react-cookie";
import authUtil from "../../auth";

function Profile({ userData }) {
    // TODO: implement dynamic Profile based on relationship to user (follows/blocked/is me)
    // const [cookies, setCookie] = useCookies(["Auth"]);
    // const [sent, setSent] = useState(false);
    // const [loaded, setLoaded] = useState(false);
    // const [user, setUser] = useState(null);
    // const [follows, setFollows] = useState()
    //
    // useEffect(() => {
    //     async function getUser() {
    //         setSent(true);
    //         setUser(await newtApi.getUser(authUtil.getUsername(cookies)));
    //         setLoaded(true);
    //     }
    //     if (!loaded && !sent) {
    //         getUser().then();
    //     } else if (loaded && user.id == null) {
    //         setSent(false);
    //     }
    // }, [sent, loaded, user, cookies])
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
                <table className="userProfileInfo">
                    <tr className="userProfileInfoRow">
                        <td>Followers</td>
                        <td>{userData.followers.length}</td>
                    </tr>
                    <tr className="userProfileInfoRow">
                        <td>Following</td>
                        <td>{userData.following.length}</td>
                    </tr>
                    <tr className="userProfileInfoRow">
                        <td>Location</td>
                        <td>{userData.location}</td>
                    </tr>
                    <tr className="userProfileInfoRow">
                        <td>Interests</td>
                        <td><ul style={{"list-style-type":'none'}}>
                            {userData.interests.map((interest) => <li>{interest}</li>)}
                        </ul></td>
                    </tr>
                </table>
                </div>
                <div className="userProfileConversationsPanel">

                </div>
            </div>
        </>
    );
}

export default Profile;