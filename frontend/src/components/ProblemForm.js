import React, { useState } from "react";
import ReactDOMServer from 'react-dom/server';
import styled from "styled-components";
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/react-editor';
import 'antd/dist/antd.css';
import { Rate, Select } from 'antd';

import TagButton from "./TagButton";
import registerProblem from "../api/post/registerProblem"

function ProblemForm() {
    const inputTag = React.useRef(null);
    const editorRef = React.useRef();

    const [tag, setTag] = useState("")

    const [title, setTitle] = useState("");
    const [step, setStep] = useState(1);
    const [link, setLink] = useState("");
    const [tagList, setTagList] = useState([])
    const [notificationDate, setNotificationDate] = useState("0");

    const onTagHandler = (e) => { setTag(e.currentTarget.value) }
    const onTitleHandler = (e) => { setTitle(e.currentTarget.value) }
    const onLinkHandler = (e) => { setLink(e.currentTarget.value) }
    const onStepHandler = (e) => { setStep(e) }
    const onNotificationHandler = (e) => { setNotificationDate(e) }

    const onTitleKeyPress = (e) => {
        if (e.key == 'Enter') {
            inputTag.current.focus()
        }
    }

    const onTagKeyPress = (e) => {
        if (e.key == 'Enter') {
            console.log(tag)
            let tagBlock = document.getElementById("tag-block");
            if (tag != "") {
                tagBlock.innerHTML += ReactDOMServer.renderToString(<TagButton name={tag}></TagButton>)
                addTag(tag)
                setTag("");
            }
        }
    }

    const onSumbitHandler = () => {
        const editorInstance = editorRef.current.getInstance();
        console.log(editorInstance.getMarkdown())
        const body = {
            title: title,
            link: link,
            step: step,
            notificationDate: notificationDate,
            tagList: tagList,
            content: editorInstance.getMarkdown()
        }
        registerProblem(body)
    }

    const addTag = (tag) => {
        const result = tagList.concat(tag);
        setTagList(result);
    }

    function onDateChange(date, dateString) {
        console.log(date, dateString);
    }

    function Notification() {
        return (
            <>
                <CustomSelect onChange={onNotificationHandler} defaultValue={notificationDate}>
                    <option value="0" selected>알림 기한 설정</option>
                    <option value="5">일주일 후 알림</option>
                    <option value="4">이주일 후 알림</option>
                    <option value="3">한 달 후 알림</option>
                    <option value="2">두 달 후 알림</option>
                    <option value="1">세 달 후 알림</option>
                </CustomSelect>
            </>
        )
    }

    const returnHtml = <Container className="container">
        <Title value={title} onChange={onTitleHandler} onKeyPress={onTitleKeyPress} placeholder="제목을 입력하세요"></Title>
        <TagLine />
        <TagInput value={tag} onChange={onTagHandler} onKeyPress={onTagKeyPress} id="tag" ref={inputTag} placeholder="태그를 입력하세요"></TagInput>
        <TagBlock id="tag-block"></TagBlock>
        <LinkLine />
        <LinkInput value={link} onChange={onLinkHandler} placeholder="문제 링크를 입력하세요"></LinkInput>
        <LinkLine />
        <Notification value={notificationDate} />

        <StepContainer><StepTitle>난이도</StepTitle><Step onChange={onStepHandler} /></StepContainer>
        <Editor
            initialValue=""
            previewStyle="vertical"
            height="600px"
            initialEditType="markdown"
            useCommandShortcut={true}
            ref={editorRef}
        />
        <Button onClick={onSumbitHandler} className="btn btn-dark">등록하기</Button>
    </Container>;


    return (
        <>{returnHtml}</>
    )
}

const CustomSelect = styled(Select)`
    float: right;
    width: 50%
`

const Container = styled.div`
    margin-top: 2%;
`

const Title = styled.textarea`
    display: block;
    padding: 0px;
    font-size: 2.75rem;
    width: 100%;
    line-height: 1.5;
    outline: none;
    border: none;
    font-weight: bold;
    height: 90px;
    color: rgb(33, 37, 41);
    resize: none;
`

const TagLine = styled.div`
    background: rgb(73, 80, 87);
    background: grey;
    height: 6px;
    width: 4rem;
    margin-bottom: 1rem;
    border-radius: 1px;
`

const TagInput = styled.input`
    display: inline-flex;
    cursor: text;
    font-size: 1.125rem;
    line-height: 2rem;
    margin-bottom: 0.75rem;
    outline: none;
    border: none;
`

const TagBlock = styled.div`
`

const LinkLine = styled.div`
    background: lightgray;
    height: 3px;
    width: 2rem;
    margin-top: 1rem;
    margin-bottom: 0.7rem;
    border-radius: 1px;
`

const LinkInput = styled.input`
    display: block;
    padding: 0px;
    font-size: 1.125rem;
    width: 100%;
    resize: none;
    line-height: 1.5;
    outline: none;
    border: none;
    color: rgb(33, 37, 41);
    height: 30;
`

const StepContainer = styled.div`
    margin-bottom: 1%;
    vertical-align: center;
`

const StepTitle = styled.span`
    font-size: 1.05rem;
    color: #66655b;
    display: inline;
    margin-right: 14px;
`

const Step = styled(Rate)`
    font-size: 25px;
`

const Button = styled.button`
    float: right;
    margin-top: 10px;
`

export default ProblemForm;
