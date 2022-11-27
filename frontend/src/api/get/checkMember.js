import axios from 'axios';

const base = require('../../utils/base')

export const checkMember = async () => {
    const url =
        base.url + '/member-service/api/members/check';

    const option = {
        url: url,
        method: 'GET',
        headers: {
            "Authorization": sessionStorage.getItem("access_token")
        }
    }

    try {
        const response = await axios(option);
        if (response.data.status !== 200) {
            return false;
        }
        return true;
    } catch (e) {
        return false;
    }
};

export default checkMember;