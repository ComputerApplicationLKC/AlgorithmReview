import axios from 'axios';

const base = require('../../utils/base')

export const updateNotificationDateApi = async (idx, notificationDate) => {
    const url =
        base.url + '/problem-service/api/problems/' + idx + '/notification';

    console.log(idx, notificationDate)
    const option = {
        url: url,
        method: 'PUT',
        headers: {
            "Authorization": sessionStorage.getItem("access_token")
        },
        data: {
            notificationDate: notificationDate
        }
    }

    try {
        const response = await axios(option);
    } catch (e) {
        console.log('[FAIL] PUT ', e);
    }
};

export default updateNotificationDateApi;