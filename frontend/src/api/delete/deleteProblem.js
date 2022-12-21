import axios from "axios"; // 조욱희 작성

const base = require("../../utils/base");
// 문제 삭제
export const deleteProblemApi = async (idx) => {
  const url = base.url + "/problem-service/problems/" + idx;

  const option = {
    url: url,
    method: "DELETE",
    headers: {
      Authorization: sessionStorage.getItem("access_token"),
    },
  };

  try {
    await axios(option);
  } catch (e) {
    return null;
  }
};

export default deleteProblemApi;
