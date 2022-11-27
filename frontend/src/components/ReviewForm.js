import React, { useState } from "react";
import styled from "styled-components";
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/react-editor';
import 'antd/dist/antd.css';
import { Select, Calendar } from 'antd';

function ReviewForm(props) {
    const editorRef = React.useRef();

    const problemId = props.problemId;
    const title = props.title;
    const [notificationDate, setNotificationDate] = useState(props.notificationDate)

    const onNotificationHandler = (e) => { setNotificationDate(e) }

    const onSumbitHandler = () => {
        const editorInstance = editorRef.current.getInstance();
        console.log(editorInstance.getMarkdown())
        const data = {
            title: title,
            problemId: problemId,
            notificationDate: notificationDate,
            content: editorInstance.getMarkdown()
        }
        console.log(data);
        props.onSumbitHandler(data)
    }

    function Notification() {
        return (
            <CustomSelect onChange={onNotificationHandler} defaultValue={notificationDate}>
                <option value="0" selected>알림 기한 설정</option>
                <option value="5">일주일 후 알림</option>
                <option value="4">이주일 후 알림</option>
                <option value="3">한 달 후 알림</option>
                <option value="2">두 달 후 알림</option>
                <option value="1">세 달 후 알림</option>
            </CustomSelect>
        )
    }


    return (
        <Container className="container">
            <Title>{title}</Title>
            <Notification value={notificationDate} />
            <br />
            <Editor
                initialValue={props.content}
                previewStyle="vertical"
                height="600px"
                initialEditType="markdown"
                useCommandShortcut={true}
                ref={editorRef}
            />
            <Button onClick={onSumbitHandler} className="btn btn-dark">등록하기</Button>
        </Container>
    )
}

const CustomSelect = styled(Select)`
    width: 100%;
    margin-bottom: 1%;
`

const Container = styled.div`
    margin-top: 2%;
`

const Title = styled.textarea`
    display: block;
    font-size: 2.75rem;
    width: 100%;
    line-height: 1.5;
    outline: none;
    border: none;
    font-weight: bold;
    height: 80px;
    color: rgb(33, 37, 41);
    resize: none;
    margin-bottom: 0.5%;
`

const Button = styled.button`
    float: right;
    margin-top: 10px;
`

export default ReviewForm;
