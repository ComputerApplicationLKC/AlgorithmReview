import axios from 'axios';
import dateSetting from '../dateSetting';

const base = require('../../utils/base')

export const registerProblem = async (props) => {
    const url =
        base.url + '/problem-service/api/problems';

    console.log(props)
    const option = {
        url: url,
        method: 'POST',
        headers: {
            "Authorization": sessionStorage.getItem("access_token")
        },
        data: {
            title: props.title,
            link: props.link,
            tagList: props.tagList,
            notificationDate: dateSetting(props.notificationDate),
            step: props.step,
            content: props.content
        }
    }

    try {
        const response = await axios(option);
        console.log(response)
        window.location.href = "/problems/" + response.data.data; // TODO 수정
    } catch (e) {
        return null;
    }
};

export default registerProblem;
