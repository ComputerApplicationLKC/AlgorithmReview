import axios from 'axios';

const base = require('../../utils/base')

export const updateReviewApi = async (props) => {
    const url =
        base.url + '/problem-service/problems/' + props.problemId + '/reviews/' + props.reviewId;

    const option = {
        url: url,
        method: 'PUT',
        headers: {
            "Authorization": sessionStorage.getItem("access_token")
        },
        data: {
            content: props.content
        }
    }

    try {
        const response = await axios(option);
        console.log('[SUCCESS] PUT ', response);
        window.location.href = "/problems/" + props.problemId; 
    } catch (e) {
        console.log('[FAIL] PUT ', e);
        return null;
    }
};

export default updateReviewApi;
