import axios from 'axios';

const base = require('../../utils/base')

export const updateStepApi = async (idx, step) => {
    const url =
        base.url + '/problem-service/problems/' + idx + '/step';

    console.log(idx, step)
    const option = {
        url: url,
        method: 'PUT',
        headers: {
            "Authorization": sessionStorage.getItem("access_token")
        },
        data: {
            step: step
        }
    }

    try {
        const response = await axios(option);
    } catch (e) {
        return null;
    }
};

export default updateStepApi;
