import axios from 'axios';

const base = require('../../utils/base')

export const deleteProblemApi = async (idx) => {
    const url =
        base.url + '/problem-service/api/problems/' + idx;

    const option = {
        url: url,
        method: 'DELETE',
        headers: {
            "Authorization": sessionStorage.getItem("access_token")
        }
    }

    try {
        await axios(option);
    } catch (e) {
        return null;
    }
};

export default deleteProblemApi;
