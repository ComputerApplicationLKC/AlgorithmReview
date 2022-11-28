import axios from 'axios';

const base = require('../../utils/base')

export const getTagListApi = async () => {
    const url =
        base.url + '/problem-service/tags';
    try {
        const response = await axios.get(`${url}`);
        return response.data.data;
    } catch (e) {
        return [];
    }
};

export default getTagListApi;