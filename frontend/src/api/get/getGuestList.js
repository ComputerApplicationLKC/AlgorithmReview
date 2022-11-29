import axios from 'axios';

const base = require('../../utils/base')

export const getGuestListApi = async (page) => {
    const url =
        base.url + '/guest-service/guests?page=' + page;
    try {
        const response = await axios.get(`${url}`);
        return response.data.data;
    } catch (e) {
        return null;
    }
}

export default getGuestListApi;