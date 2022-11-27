import { GoogleLogin } from "react-google-login";
import googleLogin from "../api/post/googleLogin";

const base = require('../utils/base.json')
const clientId = base.client_id;

export default function GoogleLoginBtn(props) {
    console.log(props)
    const onSuccess = (response) => {
        console.log(response)
        googleLogin(response.accessToken, response.profileObj.name);
    }

    const onFailure = (error) => {
        console.log(error);
    }

    return (
        <div>
            <GoogleLogin
                clientId={clientId}
                responseType={"id_token"}
                onSuccess={onSuccess}
                onFailure={onFailure} />
        </div>
    )
}