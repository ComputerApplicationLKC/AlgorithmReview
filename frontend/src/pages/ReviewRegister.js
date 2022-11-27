import React, { useState } from "react";
import HeaderMain from "../components/Header"
import ReviewForm from "../components/ReviewForm"
import registerReview from "../api/post/registerReview"
import FooterMain from '../components/Footer';

const ReviewRegister = (props) => {
    console.log(props)
    if (props.location.state === undefined) {
        window.location.href = "/404"
    }
    const onSumbitHandler = (props) => {
        const data = {
            title: props.title,
            problemId: props.problemId,
            notificationDate: props.notificationDate,
            content: props.content
        }
        registerReview(data)
    }
    return (
        <div>
            <HeaderMain />
            <ReviewForm onSumbitHandler={onSumbitHandler} problemId={props.match.params.problemId} notificationDate="0" title={props.location.state.title} content="" />
            <FooterMain />
        </div>
    );
}

export default ReviewRegister;
