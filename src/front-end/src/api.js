// Singleton to simplify API interface for use in front end JS
const newtApi = {

    async getUser(username) {
        const response = await fetch(`http://localhost:8080/api/users/${username}`)
        return await response.json()
    },

     async createUser(username, password, interest) {
         const response = await fetch('http://localhost:8080/api/users',
             {
                 method: 'POST',
                 headers: {
                     'Content-Type': 'application/json'
                 },
                 body: JSON.stringify({username: username, password: password, interest: interest})
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

    async createConversation(cookies, formData) {
        const bearerToken = "Bearer " + cookies.Auth;
        console.log(bearerToken)
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
    }
}

export default newtApi