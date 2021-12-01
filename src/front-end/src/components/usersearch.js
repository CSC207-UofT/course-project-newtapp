import React from "react"
import UserProfile from "./userprofile";
import newtApi from "../api.js"

class UserSearchForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {value: '', userData: {}};
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const data = await newtApi.getUser(this.state.value);
        this.setState({userData: data});
    }

    render() {
        return(
            <main>
                <h2>Look up a user</h2>
                <form onSubmit={this.handleSubmit}>
                    <input type="text" placeholder="Username" value={this.state.value} onChange={this.handleChange} />
                    <input type="submit" value="Submit" />
                </form>
                <UserProfile userData={this.state.userData} />
            </main>
        );
    }
}

export default UserSearchForm