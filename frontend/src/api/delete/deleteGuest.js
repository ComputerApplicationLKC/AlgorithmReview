import axios from 'axios';

const base = require('../../utils/base')

export const deleteGuestApi = async (idx) => {
    const url =
        base.url + '/guest-service/api/guests/' + idx;

    const option = {
        url: url,
        method: 'DELETE',
        headers: {
            "Authorization": sessionStorage.getItem("access_token")
        }
    }

    try {
        const response = await axios(option);
    } catch (e) {
        return null;
    }
};

export default deleteGuestApi;
