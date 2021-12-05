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
     }
}

export default newtApi