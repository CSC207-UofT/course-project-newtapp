import React from "react"
import newtApi from "../api.js"

class LoginUserForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {username: '', password: ''};
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        // this syntax assigns one dictionary key, value pair without altering others
        this.setState({[event.target.name]: event.target.value});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const newId = await newtApi.createUser(this.state);
        if (newId != null) {
            // redirect to login page or log in the new user or something else not sure
            console.log(newId)
        }
    }

    render() {
        return(
            <form onSubmit={this.handleSubmit}>
                <input name="username" type="text" required="required" placeholder="Username"
                       value={this.state.username} onChange={this.handleChange} /> <br />
                <input name="password" type="password" minLength="6" required="required" placeholder="Password"
                       value={this.state.password} onChange={this.handleChange} /> <br />
                <input type="submit" value="Submit" />
            </form>
        );
    }
}

export default LoginUserForm