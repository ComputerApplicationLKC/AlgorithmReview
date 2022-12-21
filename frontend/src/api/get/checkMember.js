import axios from "axios"; // 조욱희 작성

const base = require("../../utils/base");
// 멤버체크 접근토큰 발부받고 200번대나오면 에러
export const checkMember = async () => {
  const url = base.url + "/member-service/check";

  const option = {
    url: url,
    method: "GET",
    headers: {
      Authorization: sessionStorage.getItem("access_token"),
    },
  };

  try {
    const response = await axios(option);
    if (response.data.status !== 200) {
      console.log("error while checking member");
      return false;
    }
    return true;
  } catch (e) {
    return false;
  }
};

export default checkMember;
