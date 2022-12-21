import React from "react"; // 조욱희 작성
import HeaderMain from "../components/Header";
import ReviewForm from "../components/ReviewForm";
import registerReview from "../api/post/registerReview";
import FooterMain from "../components/Footer";
// 리뷰등록 페이지 맨위노드에 헤더메인컴포넌트 깔아놓고 리뷰폼안에다가 동작 깔아놓음 밑에는 각 기능에 대한 동작들을 미리 정의해 놓은것
const ReviewRegister = (props) => {
  console.log(props);
  if (props.location.state === undefined) {
    window.location.href = "/404";
  }
  const onSumbitHandler = (props) => {
    const data = {
      title: props.title,
      problemId: props.problemId,
      content: props.content,
    };
    registerReview(data);
  };
  return (
    <div>
      <HeaderMain />
      <ReviewForm
        onSumbitHandler={onSumbitHandler}
        problemId={props.match.params.problemId}
        title={props.location.state.title}
        content=""
      />
      <FooterMain />
    </div>
  );
};

export default ReviewRegister;
