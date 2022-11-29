import axios from 'axios';

const base = require('../../utils/base')

export const getGuestCountApi = async () => {
    const url =
        base.url + '/guest-service/guests/count';
    try {
        const response = await axios.get(`${url}`);
        return response.data.data;
    } catch (e) {
        return 0;
    }
};

export default getGuestCountApi;