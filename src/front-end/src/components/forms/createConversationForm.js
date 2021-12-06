import React from "react";
import { Navigate } from 'react-router-dom';
import newtApi from "../../api.js";
import { instanceOf } from 'prop-types';
import {withCookies, Cookies} from 'react-cookie';

class CreateConversationForm extends React.Component {
    static propTypes = {
        cookies: instanceOf(Cookies).isRequired
    };

    constructor(props) {
        super(props);
        this.state = {redirect: false, tryAgain: false, newId: 0,
                title: "",
                topics: "",
                location: "",
                minRating: 0,
                maxSize: 0};
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        // this syntax assigns one dictionary key, value pair without altering others
        this.setState({[event.target.name]: event.target.value});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const formData = {locationRadius: 0, title: this.state.title, topics: this.state.topics.split(','),
            location: this.state.location, minRating: parseInt(this.state.minRating),
            maxSize: parseInt(this.state.maxSize)};
        console.log(formData);
        const { cookies } = this.props;
        const newConvo = await newtApi.createConversation(cookies.cookies, formData);
        if (!newConvo) {
            // Something wrong
            this.setState({tryAgain: true});
        } else {
            // Redirect to login page
            this.setState({newId: newConvo.id})
            this.setState({redirect: true});
        }
    }

    render() {
        if (this.state.redirect) {
            return (<Navigate to="/login" replace={false} />)
        } else if (this.state.tryAgain) {
            return (
                <div>
                    <form onSubmit={this.handleSubmit} className="createConversationForm">
                        <input name="title" type="text" required="required" placeholder="Title"
                               value={this.state.title} onChange={this.handleChange}
                               className="newtTextInput"/> <br/>
                        <input name="topics" type="text" required="required" placeholder="Topics (Comma separated!)"
                               value={this.state.topics} onChange={this.handleChange}
                               className="newtTextInput"/> <br/>
                        <input name="location" type="text" required="required" placeholder="Location"
                               value={this.state.location} onChange={this.handleChange}
                               className="newtTextInput"/><br/>
                        <input name="maxSize" type="number" step="1" required="required" placeholder="Maximum Size"
                               value={this.state.maxSize} onChange={this.handleChange}
                               className="newtTextInput"/>
                        <div style={{"padding":"16px"}}>
                            <p style={{"display": "inline"}}>Minimum user rating:</p>
                            <input type="radio" id="1" name="minRating" required="required"
                                   value={1} onChange={this.handleChange}
                                   className="newtRadioInput"/><label htmlFor="1">1</label>
                            <input type="radio" id="2" name="minRating" required="required"
                                   value={2} onChange={this.handleChange}
                                   className="newtRadioInput"/><label htmlFor="2">2</label>
                            <input type="radio" id="3" name="minRating" required="required"
                                   value={3} onChange={this.handleChange}
                                   className="newtRadioInput"/><label htmlFor="3">3</label>
                            <input type="radio" id="4" name="minRating" required="required"
                                   value={4} onChange={this.handleChange}
                                   className="newtRadioInput"/><label htmlFor="4">4</label><br/>
                        </div>
                        <input type="submit" value="Create Conversation" className="newtButton"/>
                        <div className="formWarningText">
                            <p>Something went wrong, sorry! Please try again!</p>
                        </div>
                    </form>
                </div>
            );
        } else {
            return (
                <div>
                    <form onSubmit={this.handleSubmit} className="createConversationForm">
                        <input name="title" type="text" required="required" placeholder="Title"
                               value={this.state.title} onChange={this.handleChange}
                               className="newtTextInput"/> <br/>
                        <input name="topics" type="text" required="required" placeholder="Topics (Comma separated!)"
                               value={this.state.topics} onChange={this.handleChange}
                               className="newtTextInput"/> <br/>
                        <input name="location" type="text" required="required" placeholder="Location"
                               value={this.state.location} onChange={this.handleChange}
                               className="newtTextInput"/><br/>
                        <div>
                            <p style={{"display": "inline"}}>Maximum capacity</p>
                            <input name="maxSize" type="number" step="1" required="required" placeholder="Maximum Size"
                                   value={this.state.maxSize} onChange={this.handleChange}
                                   className="newtTextInput"/>
                        </div>
                        <div style={{"padding":"16px"}}>
                            <p style={{"display": "inline"}}>Minimum user rating:</p>
                            <input type="radio" id="1" name="minRating" required="required"
                                   value={1} onChange={this.handleChange}
                                   className="newtRadioInput"/><label htmlFor="1">1</label>
                            <input type="radio" id="2" name="minRating" required="required"
                                   value={2} onChange={this.handleChange}
                                   className="newtRadioInput"/><label htmlFor="2">2</label>
                            <input type="radio" id="3" name="minRating" required="required"
                                   value={3} onChange={this.handleChange}
                                   className="newtRadioInput"/><label htmlFor="3">3</label>
                            <input type="radio" id="4" name="minRating" required="required"
                                   value={4} onChange={this.handleChange}
                                   className="newtRadioInput"/><label htmlFor="4">4</label><br/>
                        </div>
                        <input type="submit" value="Create Conversation" className="newtButton"/>
                    </form>
                </div>
            );
        }
    }
}

export default withCookies(CreateConversationForm)