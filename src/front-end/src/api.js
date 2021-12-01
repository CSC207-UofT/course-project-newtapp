// Singleton to simplify API interface for use in front end JS
const newtApi = {
    /**
     * Returns response of a GET request to /api/users/{id}.
     * @param id        id of user to fetch
     * @returns {*}
     */
    async getUser(id) {
        const response = await fetch(`http://localhost:8080/api/users/${id}`)
        const data = await response.json()
        console.log(data)
        return data
    },

     async createUser(form) {
         const response = await fetch('http://localhost:8080/api/users',
             {
                 method: 'POST',
                 headers: {
                     'Content-Type': 'application/json'
                 },
                 body: JSON.stringify(form)
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