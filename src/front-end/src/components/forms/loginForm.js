import React from "react"
import newtApi from "../../api.js"

class LoginForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {username: '', password: '', interest: ''};
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        // this syntax assigns one dictionary key, value pair without altering others
        this.setState({[event.target.name]: event.target.value});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const authUser = await newtApi.login(this.state.username, this.state.password, this.state.interest);
        if (!authUser) {
            // ask to try again?
        }
    }

    render() {
        return(
            <form onSubmit={this.handleSubmit}>
                <input name="username" type="text" required="required" placeholder="Username"
                       value={this.state.username} onChange={this.handleChange} className="newtTextInput"/> <br />
                <input name="password" type="password" required="required" placeholder="Password"
                       value={this.state.password} onChange={this.handleChange} className="newtTextInput"/> <br />
                <input type="submit" value="Log In" className="newtButtonDark"/>
            </form>
        );
    }
}

export default LoginForm