import React from "react"

class UserSearchForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {value: ''};
        this.userData = null;
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    handleSubmit(event) {
        event.preventDefault();
        const fetchAt = 'http://localhost:8080/api/users/' + this.state.value;
        this.userData = fetch(fetchAt);
    }

    render() {
        return(
            <form onSubmit={this.handleSubmit}>
                <label>
                    Userid:
                    <input type="text" value={this.state.value} onChange={this.handleChange} />
                </label>
                <input type="submit" value="Submit" />
                <p>{this.userData}</p>
            </form>
        );
    }
}

export default UserSearchForm