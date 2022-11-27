import React, { useEffect, useState } from 'react'
import styled from "styled-components";
import { Pagination } from 'antd';

import GoogleLoginBtn from "../components/GoogleLogin"
import HeaderMain from "../components/Header"
import ProblemList from "../components/ProblemList"
import TagList from "../components/TagList";
import FooterMain from '../components/Footer';
import getProblemListApi from '../api/get/getProblemList'
import getTagListApi from '../api/get/getTagList';
import getProblemCountApi from '../api/get/getProblemCount';

const Main = (props) => {
    const [problemList, setProblemList] = useState([]);
    const [allCount, setAllCount] = useState(0);
    const [tagList, setTagList] = useState([]);
    const [tagName, setTagName] = useState("전체");
    const [page, setPage] = useState(0);
    const [tagCount, setTagCount] = useState(0);

    const getTagList = async () => {
        const data = await getTagListApi();
        setTagList(data);
    };

    const getProblemList = async (tagName) => {
        if (props.location.state !== undefined) {
            tagName = props.location.state.tagName;
            setTagName(props.location.state.tagName);
            const data = await getTagListApi();
            setTagCount(data.length)
        }          
        const data = await getProblemListApi(tagName, 0);
        setProblemList(data);
    };

    const getProblemCount = async () => {
        const data = await getProblemCountApi();
        setAllCount(data);
    }

    useEffect(() => {
        getTagList();
    }, []);

    useEffect(() => {
        getProblemList("");
    }, []);

    useEffect(() => {
        getProblemCount();
    }, []);

    const onTagClick = async (tagName) => {
        setPage(1);
        if (tagName == "전체") {
            setTagName(tagName);
            getProblemList("");
        }
        else {
            setTagName(tagName);
            getProblemList(tagName);
        }

        tagList.map((tag) => {
            if (tag.tagName === tagName) {
                console.log(tagName)
                console.log(tag.count)
                setTagCount(tag.count)
            }
        })
    }

    const onPageClick = async (e) => {
        if (tagName == "전체") {
            const data = await getProblemListApi("", e - 1);
            setProblemList(data);
        }
        else {
            const data = await getProblemListApi(tagName, e - 1);
            setProblemList(data);
        }
        setPage(e)
        console.log(page, e)
    }

    return (
        <div>
            <HeaderMain />
            <LoginCheck />
            <Container className="container">
                <TagList onClick={onTagClick} tagList={tagList} count={allCount} name={tagName} />
                <ProblemList onClick={onTagClick} problemList={problemList} />
            </Container>
            <PageContainer>
                {tagName !== "전체"
                    ? <Pagination onChange={onPageClick} defaultCurrent={1} pageSize={12} total={tagCount} />
                    : <Pagination onChange={onPageClick} defaultCurrent={1} pageSize={12} total={allCount} />
                }
            </PageContainer>
            <FooterMain />
        </div>
    );
}

function LoginCheck() {
    if (sessionStorage.getItem("nickname") === null) {
        return (
            <Welcome><GoogleLoginBtn href="/" /></Welcome>
        )
    }
    return (<></>)
}

const Container = styled.div`
    max-width: 1200px;
`

const Welcome = styled.span`
    position: absolute;
    right: 0.5%;
    padding: 0.5%;
`

const PageContainer = styled.div`
    width: 100%;
    text-align: center;
    margin-top: 1%;
    bottom: 2%;
`

export default Main;