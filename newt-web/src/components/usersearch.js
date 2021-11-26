import React from "react"
import UserProfile from "./userprofile";

class UserSearchForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {value: '', userData: []};
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    handleSubmit(event) {
        event.preventDefault();
        fetch('http://localhost:8080/api/users/' + this.state.value)
            .then(response => {
                if (!response.ok) {
                    throw new Error('failed to fetch user data');
                }
                return response.json();
            })
            .then(data => {
                console.log(data);
                this.setState({userData: [
                        data.id,
                        data.username,
                        data.location,
                        data.interests,
                        data.loginStatus,
                        data.followers,
                        data.following,
                        data.conversations
                    ]});
            })
    }

    render() {
        return(
            <main>
                <h2>Look up a user</h2>
                <form onSubmit={this.handleSubmit}>
                    <input type="text" placeholder="User's id" value={this.state.value} onChange={this.handleChange} />
                    <input type="submit" value="Submit" />
                </form>
                <UserProfile userData={this.state.userData} />
            </main>
        );
    }
}

export default UserSearchForm