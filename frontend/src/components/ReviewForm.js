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

    const onSumbitHandler = () => {
        const editorInstance = editorRef.current.getInstance();
        console.log(editorInstance.getMarkdown())
        const data = {
            title: title,
            problemId: problemId,
            content: editorInstance.getMarkdown()
        }
        console.log(data);
        props.onSumbitHandler(data)
    }


    return (
        <Container className="container">
            <Title>{title}</Title>
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
