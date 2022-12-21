import axios from "axios"; // 조욱희 작성
// 문제 서치할때 axios로 url통신해줌
const base = require("../../utils/base");

export const searchApi = async (keyword) => {
  const url = base.url + "/problem-service?search=" + keyword;
  try {
    const response = await axios.get(`${url}`);
    return response.data.data;
  } catch (e) {
    return [];
  }
};

export default searchApi;
