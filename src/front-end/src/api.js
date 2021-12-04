// Singleton to simplify API interface for use in front end JS
const newtApi = {

    async getUser(username) {
        const response = await fetch(`http://localhost:8080/api/users/${username}`)
        const data = await response.json()
        console.log(data)
        return data
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
         const data = await response.json()
         console.log(data)
         if (data.id != null) {
             return data.id;
         } else {
             return false;
         }
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