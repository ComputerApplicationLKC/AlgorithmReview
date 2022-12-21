import axios from "axios"; // 조욱희 작성

const base = require("../../utils/base");
// 리뷰삭제
export const deleteReviewApi = async (idx) => {
  const url = base.url + "/problem-service/reviews/" + idx;

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

export default deleteReviewApi;
