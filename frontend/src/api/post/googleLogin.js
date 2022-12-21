import axios from "axios"; // 조욱희 작성

import checkMember from "../get/checkMember";

const base = require("../../utils/base");

const googleLogin = async (email, username, token) => {
  const url = base.url + "/member-service/google";

  const option = {
    url: url,
    method: "POST",
    data: { email: email, username: username, token: token },
  };

  try {
    const response = await axios(option);
    if (response.data.status === 200) {
      console.log("로그인 성공");
      sessionStorage.setItem("nickname", response.data.data.nickname);
      sessionStorage.setItem("access_token", response.data.data.email);
      await checkMember();
      window.location.href = "/";
    } else {
      console.log(response);
      window.location.href = "/";
    }
  } catch (e) {
    sessionStorage.setItem("nickname", username);
    window.location.href = "/";
    return null;
  }
};

export default googleLogin;
