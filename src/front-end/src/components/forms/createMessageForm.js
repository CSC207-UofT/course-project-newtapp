import React from "react";
import { Navigate } from 'react-router-dom';
import newtApi from "../../api.js";
import {instanceOf} from 'prop-types';
import {withCookies, Cookies} from 'react-cookie';

class CreateMessageForm extends React.Component{
    static propTypes = {
        cookies: instanceOf(Cookies).isRequired,
        id: instanceOf(Number).isRequired
    }

    constructor(props) {
        super(props);
        this.state = {redirect: false, tryAgain: false, newId: 0, body: ""};
        this.previewData = ""
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        // this syntax assigns one dictionary key, value pair without altering others
        this.setState({[event.target.name]: event.target.value});
    }

    async handleSubmit(event){
        event.preventDefault()
        const { cookies, id } = this.props;
        const newMessage = await newtApi.createMessage(cookies.cookies, id, this.state.body)
        console.log(newMessage)
        this.setState({newId: newMessage.id})
        this.setState({redirect: true});
    }

    render(){
        if (this.state.redirect) {
            return(
                <Navigate to="/conversations/id/view" replace={true} />
            )
        } else {
        return (
            <form onSubmit={this.handleSubmit}>
                <textarea name="body" required="required" placeholder=""
                       value={this.state.body} onChange={this.handleChange} className="newtTextInput"/> <br />
                <input type="submit" value="Send" className="newtButtonDark"/>
            </form>
        )
        }
    }
}

export default withCookies(CreateMessageForm)