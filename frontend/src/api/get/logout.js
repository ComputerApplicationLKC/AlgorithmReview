import axios from 'axios';

const base = require('../../utils/base')

export const logout = async () => {
    const url =
        base.url + '/member-service/logout';
    try {
        const response = await axios.get(`${url}`);
        return response.data.data;
    } catch (e) {
        return [];
    }
};

export default logout;