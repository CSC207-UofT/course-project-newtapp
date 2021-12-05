import {useCookies} from 'react-cookie';
import jwt_decode from "jwt-decode";

// Singleton to simplify JWT authorization interface for use in front end JS
const authUtil = {

    // Return true iff cookies contains valid, non-expired JWT
     hasAuth(cookies) {
        if (cookies === undefined) {
            return false;
        }
        const token = cookies.get("Auth");
        let decodedToken = "";
        try {
            decodedToken = jwt_decode(token);
        } catch (InvalidTokenError) {
            return false;
        }
        if (authUtil.isExpired(decodedToken)) {
            cookies.remove("Auth");
            return false;
        }
        // token is valid
        console.log(true)
        return true;
    },

    // Return true iff token is expired
    isExpired(decodedToken) {
         const now = Math.round(Date.now() / 1000);
         return !(decodedToken.iat < now < decodedToken.exp);
    }
}

export default authUtil