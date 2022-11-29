import axios from 'axios';

const base = require('../../utils/base')

export const registerReview = async (props) => {
    const url =
        base.url + '/problem-service/problems/' + props.problemId + "/reviews";

    console.log(props)
    const option = {
        url: url,
        method: 'POST',
        headers: {
            "Authorization": sessionStorage.getItem("access_token")
        },
        data: {
            title: props.title,
            content: props.content
        }
    }

    try {
        await axios(option);
        window.location.href = "/problems/" + props.problemId; 
    } catch (e) {
        return null;
    }
};

export default registerReview;
