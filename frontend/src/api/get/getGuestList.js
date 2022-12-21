import axios from "axios"; // 조욱희 작성

const base = require("../../utils/base");
// 게스트목록 얻을때
export const getGuestListApi = async (page) => {
  const url = base.url + "/guest-service/guests?page=" + page;
  try {
    const response = await axios.get(`${url}`);
    return response.data.data;
  } catch (e) {
    return null;
  }
};

export default getGuestListApi;
