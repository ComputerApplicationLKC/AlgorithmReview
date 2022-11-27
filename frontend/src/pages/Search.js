import { useEffect, useState } from "react";
import queryString from 'query-string'
import styled from 'styled-components'
import 'antd/dist/antd.css';
import { Viewer } from '@toast-ui/react-editor';
import { Rate } from 'antd';

import HeaderMain from "../components/Header"
import searchApi from '../api/get/getSearch'
import FooterMain from '../components/Footer';

const Search = (props) => {
    const [searchList, setSearchList] = useState([])

    const { search } = props.location;
    const queryObj = queryString.parse(search);
    const keyword = queryObj.q;

    const getSearchList = async () => {
        const data = await searchApi(keyword);
        setSearchList(data)
    };

    useEffect(() => {
        getSearchList();
    }, []);

    // const onPageClick = async (e) => {
    //     const data = await searchApi(e - 1, keyword);
    //     setSearchList(data);
    // }

    if (searchList.length > 0) {
        return (
            <div>
                <HeaderMain />
                <SearchContent count={searchList.length} keyword={keyword} />
                <SearchList className="container">
                    {searchList.map((search) => {
                        return (
                            <CardComponent>
                                <Link href={`/problems/${search.id}`} style={{ textDecoration: 'none' }}><Title>{search.title}</Title></Link>
                                <Step value={search.step} />
                                · <Link onClick={() => window.open(search.link)}>문제 링크</Link>
                                <hr />
                                <Viewer initialValue={search.content} />
                            </CardComponent>
                        )
                    })}
                </SearchList>
                {/* <PageContainer>
                    {searchList.length !== 0
                        ? <Pagination onChange={onPageClick} defaultCurrent={1} total={searchList.length / 9 + 1} />
                        : null
                    }
                </PageContainer> */}
                <FooterMain />
            </div>
        )
    }
    else {
        return (
            <>
                <HeaderMain />
                <SearchBar className="container">
                    <SearchTitle><b>"{keyword}"</b> 검색 결과입니다.</SearchTitle>
                    <SearchSub>검색 결과가 없습니다.</SearchSub>
                </SearchBar>
            </>
        )
    }
}

function SearchContent(props) {
    return (
        <SearchBar className="container">
            <SearchTitle><b>"{props.keyword}"</b> 검색 결과입니다.</SearchTitle>
            <SearchSub>총 <b>{props.count}</b>개의 포스트를 찾았습니다.</SearchSub>
        </SearchBar>
    )
}
const SearchBar = styled.div`
    margin-top: 3%;
    max-width: 1200px;
`

const SearchTitle = styled.div`
    font-size: 35px;
`

const SearchSub = styled.div`
    padding: 10px;
    font-size: 18px;
    color: grey;
`

const SearchList = styled.div`
    max-width: 1200px;
`

const CardComponent = styled.div`
    border: solid 1px lightgrey;
    border-radius: 10px;
    margin-top: 30px;
    padding: 2% 6%;
`

const Title = styled.div`
    display: block;
    margin-top: 1.5%;
    margin-bottom: 0.5%;
    padding: 0px;
    font-size: 2.15rem;
    width: 100%;
    resize: none;
    line-height: 1.5;
    outline: none;
    border: none;
    font-weight: bold;
    color: rgb(33, 37, 41);
`

const Link = styled.a`
    color: rgb(12,166,120);
    margin-left: 4px;
    font-weight: bold;
`

const Step = styled(Rate)`
    font-size: 25px;
    margin-right: 4px;
`

// const PageContainer = styled.div`
//     text-align: center;
//     margin-top: 30px;
// `

export default Search
