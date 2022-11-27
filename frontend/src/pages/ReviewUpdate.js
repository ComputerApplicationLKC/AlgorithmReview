import React, { useState } from "react";
import HeaderMain from "../components/Header"
import ReviewForm from "../components/ReviewForm"
import updateReviewApi from "../api/put/updateReview";
import FooterMain from '../components/Footer';

const ReviewUpdate = (props) => {
    console.log(props)

    const onSumbitHandler = (p) => {
        const data = {
            reviewId: props.location.state.reviewId,
            problemId: props.location.state.problemId,
            notificationDate: p.notificationDate,
            content: p.content
        }
        updateReviewApi(data)
    }

    return (
        <div>
            <HeaderMain />
            <ReviewForm onSumbitHandler={onSumbitHandler} problemId={props.match.params.problemId} notificationDate={props.location.state.notificationDate} content={props.location.state.content} title={props.location.state.title} />
            <FooterMain />
        </div>
    );
}

export default ReviewUpdate;
