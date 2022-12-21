import axios from "axios"; // 조욱희 작성

const base = require("../../utils/base");
// 게스트 삭제
export const deleteGuestApi = async (idx) => {
  const url = base.url + "/guest-service/guests/" + idx;

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

export default deleteGuestApi;
