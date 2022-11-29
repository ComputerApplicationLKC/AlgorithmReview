import axios from 'axios';
import checkMember from '../get/checkMember'

const base = require('../../utils/base')

const googleLogin = async (accessToken, nickname) => {
    const url =
        base.url + '/member-service/google';

    const option = {
        url: url,
        method: 'POST',
        data: { "accessToken": accessToken }
    }

    try {
        const response = await axios(option);
        if (response.data.status === 200) {
            console.log('로그인 성공')
            sessionStorage.setItem("nickname", response.data.data.nickname)
            sessionStorage.setItem("access_token", response.data.data.access_token)
            if (await checkMember() == true) {
                console.log("인증 성공")
                window.location.href = '/'
            }
            else {
                console.log("인증 실패")
            }
        }
        else {
            console.log(response)
            window.location.href = '/'
        }
    } catch (e) {
        sessionStorage.setItem("nickname", nickname)
        window.location.href = '/'
        return null;
    }
};

export default googleLogin;