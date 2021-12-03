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
             return 0;
         }
     }
}

export default newtApi