import axios from 'axios';

const base = require('../../utils/base')

export const getProblemApi = async (idx) => {
    const url =
        base.url + '/problemr-service/problems/' + idx;
    try {
        const response = await axios.get(`${url}`);
        return response.data.data;
    } catch (e) {
        window.location.href = "/404"
    }
}

export default getProblemApi;