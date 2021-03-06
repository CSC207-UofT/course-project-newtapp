// Singleton to simplify API interface for use in front end JS
import authUtil from "./auth";

const newtApi = {

    async getUser(username) {
        const response = await fetch(`http://localhost:8080/api/users/${username}`)
        return await response.json()
    },

     async createUser(formData) {
         const response = await fetch('http://localhost:8080/api/users',
             {
                 method: 'POST',
                 headers: {
                     'Content-Type': 'application/json'
                 },
                 body: JSON.stringify({
                     username: formData.username,
                     password: formData.password,
                     interests: formData.interests
                 })
             })
         if (response.status !== 201) {
             return false;
         }
         return await response.json()
     },

     async login(username, password) {
        const response = await fetch('http://localhost:8080/api/login',
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({username: username, password: password})
            })
        if (response.status !== 200) {
            return false;
        }
        const body = await response.json();
        return body.jwt;
     },

    async follow(cookies, username) {
        const bearerToken = "Bearer " + cookies.Auth;
        const response = await fetch(`http://localhost:8080/api/users/${username}/follow`,
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': bearerToken
                }
            })
        if (response.status !== 200) {
            return false;
        }
        const body = await response.json();
        return body.jwt;
    },

     async getRelevantConversations(cookies) {
        const bearerToken = "Bearer " + cookies.Auth;
        const response = await fetch('http://localhost:8080/api/relevant/conversations',
            {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': bearerToken
                }
            })
         if (response.status !== 200) {
             return false;
         }
         const body = await response.json();
         console.log(body);
         return body;
     },

    async getRelevantConversationsByFollow(cookies) {
        const bearerToken = "Bearer " + cookies.Auth;
        const response = await fetch('http://localhost:8080/api/following/conversations',
            {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': bearerToken
                }
            })
        if (response.status !== 200) {
            return false;
        }
        const body = await response.json();
        console.log(body);
        return body;
    },

    async getMyConversationsList(cookies) {
        const bearerToken = "Bearer " + cookies.Auth;
        const username = authUtil.getUsername(cookies.Auth)
        const response = await fetch(`http://localhost:8080/api/users/${username}/conversations`,
            {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': bearerToken
                }
            })
        if (response.status !== 200) {
            return false;
        }
        const body = await response.json();
        console.log(body);
        return body;
    },

    async createConversation(cookies, formData) {
        const bearerToken = "Bearer " + cookies.Auth;
        const response = await fetch('http://localhost:8080/api/conversations',
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': bearerToken
                },
                body: JSON.stringify(formData)
            })
        if (response.status !== 201) {
            return false;
        }
        const body = await response.json();
        console.log(body);
        return body;
    },

    async createMessage(cookies, id, messageBody) {
        const bearerToken = "Bearer " + cookies.Auth;
        const response = await fetch(`http://localhost:8080/api/conversations/${id}/messages`,
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/text',
                    'Authorization': bearerToken
                },
                body: messageBody
            })
        if (response.status !== 201) {
            return false;
        }
        return await response.json()
    },

    async getConversationData(cookies, id){
        const bearerToken = "Bearer " + cookies.Auth;
        const response = await fetch(`http://localhost:8080/api/conversations/${id}/view`,
            {
                            method: 'GET',
                            headers: {
                                'Content-Type': 'application/json',
                                'Authorization': bearerToken
                            }
                        })
            if (response.status !== 200) {
                return {};
            }
        const body = await response.json();
        console.log(body);
        return body;
    },

    async joinConversation(cookies, id){
        const bearerToken = "Bearer " + cookies.Auth;
        const response = await fetch(`http://localhost:8080/api/conversations/${id}/join`,
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': bearerToken
                },
            })
        if (response.status !== 200) {
            return false;
        }
        return await response.json()
    },

    async leaveConversation(cookies, id) {
        const bearerToken = "Bearer " + cookies.Auth;
        const response = await fetch(`http://localhost:8080/api/conversations/${id}/leave`,
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': bearerToken
                },
            })
        if (response.status !== 200) {
            return false;
        }
        return await response.json()
    }
}

export default newtApi