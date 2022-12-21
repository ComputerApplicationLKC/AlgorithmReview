import React, { useState } from "react"; // 조욱희 작성
import HeaderMain from "../components/Header";
import ReviewForm from "../components/ReviewForm";
import updateReviewApi from "../api/put/updateReview";
import FooterMain from "../components/Footer";
// 리뷰업데이트 페이지 여기서는 핸들러가 제출용하나밖에 필요하지 않음 역시 여기도 헤더메인컴포넌트에 덮여서 리뷰폼완성
const ReviewUpdate = (props) => {
  console.log(props);

  const onSumbitHandler = (p) => {
    const data = {
      reviewId: props.location.state.reviewId,
      problemId: props.location.state.problemId,
      content: p.content,
    };
    updateReviewApi(data);
  };

  return (
    <div>
      <HeaderMain />
      <ReviewForm
        onSumbitHandler={onSumbitHandler}
        problemId={props.match.params.problemId}
        content={props.location.state.content}
        title={props.location.state.title}
      />
      <FooterMain />
    </div>
  );
};

export default ReviewUpdate;
