import axios from 'axios';

const base = require('../../utils/base')

export const deleteReviewApi = async (idx) => {
    const url =
        base.url + '/problem-service/reviews/' + idx;

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

export default deleteReviewApi;
