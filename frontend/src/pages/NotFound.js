import React from 'react'
import styled from "styled-components";
import { Link } from "react-router-dom";

const NotFound = () => {

    return (
        <Container>
            <Warning><Bold>404</Bold> 해당 페이지가 존재하지 않습니다.<br/></Warning>
            <Link to="/" style={{
                color: "rgb(12,166,120)",
                fontSize: "17px"
            }}>메인 화면으로 돌아가기</Link>
        </Container>
    );
}

const Container = styled.div`
    height: 10%;
    padding-top: 20%;
    padding-bottom: 20%;
    text-align: center;
    color: grey;
`

const Warning = styled.div`
    font-size: 40px;
    color: grey;
`

const Bold = styled.span`
    font-size: 70px;
    font-weight: bold;
    margin-right: 10px;
    color: rgb(12,166,120);
`

export default NotFound;