import { useEffect } from "react";
import { GoogleLogin } from "react-google-login";
import googleLogin from "../api/post/googleLogin";
import { gapi } from 'gapi-script';

const base = require('../utils/base.json')
const clientId = base.client_id;

export default function GoogleLoginBtn(props) {

    useEffect(() => {
        function start() {
            gapi.client.init( {
                clientId,
                scope: 'email profile'
            });
        }
        gapi.load("client:auth2", start);
    }, []);

    const onSuccess = (response) => {
        googleLogin(response.wt.cu, response.profileObj.name, response.accessToken);
    }

    const onFailure = (error) => {
        console.log(error);
    }

    return (
        <div>
            <GoogleLogin
                clientId={clientId}
                buttonText="Google"
                responseType={"id_token"}
                onSuccess={onSuccess}
                onFailure={onFailure}/>
        </div>
    )
}