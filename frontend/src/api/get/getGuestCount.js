import axios from "axios"; // 조욱희 작성

const base = require("../../utils/base");
// guestlist와 거의 유사
export const getGuestCountApi = async () => {
  const url = base.url + "/guest-service/guests/count";
  try {
    const response = await axios.get(`${url}`);
    return response.data.data;
  } catch (e) {
    return 0;
  }
};

export default getGuestCountApi;
