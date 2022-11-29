import axios from 'axios';

const base = require('../../utils/base')

export const searchApi = async (keyword) => {
    const url =
        base.url + '/problem-service?search=' + keyword;
    try {
        const response = await axios.get(`${url}`);
        return response.data.data;
    } catch (e) {
        return [];
    }
};

export default searchApi;