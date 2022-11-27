import React, { useEffect, useState } from 'react'
import styled from 'styled-components'
import '@toast-ui/editor/dist/toastui-editor.css';
import Viewer from '@toast-ui/editor/dist/toastui-editor-viewer';
import 'antd/dist/antd.css';
import { Rate, DatePicker } from 'antd';
import { Link } from "react-router-dom";
import moment from 'moment';

import getProblemApi from '../api/get/getProblem'
import HeaderMain from "../components/Header"
import TagButton from "../components/TagButton";
import checkMember from "../api/get/checkMember";
import updateStepApi from "../api/put/updateStep";
import deleteReviewApi from '../api/delete/deleteReview';
import deleteProblemApi from '../api/delete/deleteProblem';
import updateNotificationDateApi from '../api/put/updateNotificationDate';
import FooterMain from '../components/Footer';

const Detail = (props) => {
    const [problem, setProblem] = useState({});
    const [review, setReview] = useState({});
    const [reviewList, setReviewList] = useState([]);
    const [tagList, setTagList] = useState([]);
    const [step, setStep] = useState(1);
    const [notificationDate, setNotificationDate] = useState("");

    const problemId = props.match.params.problemId;

    const getProblem = async () => {
        const data = await getProblemApi(problemId);
        setProblem(data);
        setReviewList(data.reviewList)
        setReview(data.reviewList[0])
        setTagList(data.tagList)
        setStep(data.step)
        setNotificationDate(data.notificationDate)
    };

    useEffect(() => {
        getProblem();
    }, []);

    const onClick = (reviewId) => {
        console.log(reviewId)
        setReview(reviewList[reviewId])
    }

    const onDelete = async () => {
        const check = window.confirm("삭제하시겠습니까?");
        if (check) {
            if (reviewList.length > 1) {
                await deleteReviewApi(review.id);
                window.location.reload();
            }
            else {
                await deleteProblemApi(problemId);
                window.location.href = "/"
            }
        }
    }

    const onDateChange = async (value, mode) => {
        updateNotificationDateApi()
        setNotificationDate(mode)
        if (await checkMember() == true) {
            updateNotificationDateApi(problem.id, mode)
        }
      }

    let el = document.querySelector('#viewer')
    if (el !== null) {
        const viewer = new Viewer({
            el: el,
            height: '600px',
            initialValue: review.content
        });
    }

    const onStepChange = async (e) => {
        if (await checkMember() == true) {
            console.log("인증 성공")
            updateStepApi(problem.id, e)
            setStep(e)
        }
        else {
            console.log("인증 실패")
        }
    }

    return (
        <div>
            <HeaderMain />
            <Container>
                <Title>{problem.title}</Title>
                <Line>
                    <Step allowClear="true" value={step} onChange={onStepChange} />
                    {sessionStorage.getItem("access_token") !== null
                     ? <>· <Notification>알림 예정일 <DatePicker value={moment(notificationDate, "YYYY-MM-DD")} defaultValue={problem.notificationDate} defaultPickerValue={moment(problem.notificationDate, "YYYY-MM-DD")} onChange={onDateChange} /></Notification></>
                     : <>· <Notification>알림 예정일 <b>{notificationDate}</b></Notification></>
                    }
                     {/* placeholder={problem.notificationDate} */}
                     · <LinkStyle onClick={() => window.open(problem.link)}>문제 링크</LinkStyle>
                </Line>
                <TagList tagList={tagList} />
                <ButtonContainer onDelete={onDelete} review={review} problem={problem} />
                <hr />
                <div id="viewer"></div>
            </Container>
            <ReviewList onClick={onClick} problemId={problemId} title={problem.title} reviewList={reviewList} />
            <FooterMain />
        </div>
    );
}

function ReviewList(props) {
    const reviewList = props.reviewList;
    return (
        <div>
            <Box className="card m-2">
                {sessionStorage.getItem("access_token") !== null
                    ? <Link to={{
                        pathname: "/problems/" + props.problemId + "/reviews/write",
                        state: { title: props.title }
                    }}
                        style={{
                            color: "white",
                            width: "90%",
                            marginBottom: "8%"
                        }} className="btn-secondary btn">Add Review</Link>
                    : <Button readonly className="btn-secondary btn">리뷰 목록</Button>
                }
                <ReviewBlock>
                    {reviewList.length > 0 &&
                        reviewList.map((review, index) => {
                            return (
                                <div>
                                    <a onClick={() => props.onClick(index)}>{index + 1}. {review.createdDate.substr(0, 10)}</a>
                                </div>
                            )
                        })}
                </ReviewBlock>
            </Box>
        </div>
    )

}

function TagList(props) {
    const tagList = props.tagList
    return (
        <TagContainer>
            {tagList.length != 0 &&
                tagList.map((o) => {
                    return (
                        <Link to={{
                            pathname: "/",
                            state: { tagName: o.tag.tagName }
                        }}style={{ textDecoration: 'none' }}><TagButton name={o.tag.tagName} /></Link>
                    )
                })}
        </TagContainer>
    )
}

function ButtonContainer(props) {
    if (sessionStorage.getItem("access_token") !== null) {
        return (
            <>
                <Link onClick={() => props.onDelete()} style={{
                    background: "white",
                    color: "darkgrey",
                    border: "none",
                    float: "right",
                    textDecoration: "none",
                    marginLeft: "1.3%"
                }}>
                    삭제
                </Link>
                <Link to={{
                    pathname: "/reviews/update",
                    state: { title: props.problem.title, notificationDate: props.problem.notificationDate, problemId: props.problem.id, content: props.review.content, reviewId: props.review.id }
                }} style={{
                    background: "white",
                    color: "darkgrey",
                    border: "none",
                    float: "right",
                    textDecoration: "none"
                }}>
                    수정
                </Link>
            </>
        )
    }
    else {
        return (<></>)
    }
}

const TagContainer = styled.span`
    margin-top: 15px;
    margin-bottom: 3px;
    padding-bottom: 0px;
`

const Container = styled.div`
    max-width: 1200px;
    margin-top: 2%;
    margin-left: 20%;
    margin-right: 20%;
`

const Title = styled.div`
    display: block;
    margin-top: 1.5%;
    margin-bottom: 0.1%;
    padding: 0px;
    font-size: 2.75rem;
    width: 100%;
    resize: none;
    line-height: 1.5;
    outline: none;
    border: none;
    font-weight: bold;
    color: rgb(33, 37, 41);
`
const Line = styled.div`
    margin-bottom: 2%;
`

const LinkStyle = styled.a`
    margin-left: 4px;
    font-weight: bold;
    color: rgb(12,166,120);
    // text-decoration: none;
`

const Step = styled(Rate)`
    font-size: 25px;
    margin-right: 4px;
`

const Notification = styled.span`
    margin-left: 4px;
    margin-right: 6px;
`

const Box = styled.div`
    width: 240px;
    margin-left: 1%;
    border: none;
    border-left: 2px solid rgb(233, 236, 239);
    padding: 0.25rem 0.75rem;
    color: rgb(134, 142, 150);
    line-height: 1.5;
    font-size: 0.875rem;
    position: fixed;
    top: 200px;
    right: 0px;
`

const Button = styled.button`
    width: 85%;
    margin-bottom: 8%;
`

const ReviewBlock = styled.div`
    padding-left: 5%;    
`

export default Detail;