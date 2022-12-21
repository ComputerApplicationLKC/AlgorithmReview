import React, { useEffect, useState } from "react"; // 조욱희 작성
import styled from "styled-components";
import "antd/dist/antd.css";
import { List, Input, Pagination } from "antd";

import HeaderMain from "../components/Header";
import FooterMain from "../components/Footer";

import getGuestListApi from "../api/get/getGuestList";
import registerGuestApi from "../api/post/registerGuest";
import deleteGuestApi from "../api/delete/deleteGuest";
import getGuestCountApi from "../api/get/getGuestCount";

// 매개변수가 아니라 사실 constructor로 만들어도 되긴하나 비구조화할당문법으로 미리 만들어놓고 사용.
export default function GuestBook() {
  const [guestList, setGuestList] = useState([]);
  const [guestCount, setGuestCount] = useState(0);
  const [content, setContent] = useState("");

  const getGuestList = async () => {
    const data = await getGuestListApi(0);
    setGuestList(data);
  };

  const getGuestCount = async () => {
    const data = await getGuestCountApi();
    setGuestCount(data);
  };

  useEffect(() => {
    getGuestList();
  }, []);

  useEffect(() => {
    getGuestCount();
  }, []);

  const { TextArea } = Input;

  const onChange = (e) => {
    setContent(e.target.value);
  };

  const onDeleteClick = async (guestId) => {
    await deleteGuestApi(guestId);
    getGuestCount();
    getGuestList();
  };

  const onPageClick = async (e) => {
    console.log(e);
    const data = await getGuestListApi(e - 1);
    setGuestList(data);
  };

  const onSumbitHandler = async () => {
    console.log(TextArea);
    setContent("");
    let nickname = "익명";
    if (sessionStorage.getItem("nickname") !== null) {
      nickname = sessionStorage.getItem("nickname");
    }
    const data = {
      nickname: nickname,
      content: content,
    };
    await registerGuestApi(data);
    getGuestCount();
    getGuestList();
  };

  // useEffect로 변화값에 대한 설정 만들어놓고 초기값설정후 두번째 값으로 배열을 넘겨준다.
  // onClick으로는 동작에대한 함수들을 만들어놓고 {}안에 넣어서 적용시켜준다.
  return (
    <>
      <HeaderMain />
      <Title>Guest Book</Title>
      <Container className="container">
        <GuestInput>
          <Button onClick={onSumbitHandler} className="btn btn-outline-secondary">
            등록하기
          </Button>
          <GuestInputTitle>
            <Emoji>📝</Emoji>
            {sessionStorage.getItem("nickname") !== null ? ( // 세션스토리지에 이름넣어놓고 맞나 대조후 맞으면 nickname출력
              <span>"{sessionStorage.getItem("nickname")}"님, </span>
            ) : (
              <span>"익명"님, </span>
            )}
            방명록을 입력해주세요
          </GuestInputTitle>
          <TextArea value={content} onChange={onChange} showCount maxLength={100} />
        </GuestInput>
        <GuestListAllTitle>
          <GuestListTitle>
            <Emoji>📄</Emoji> 방명록
          </GuestListTitle>
          ({guestCount})
        </GuestListAllTitle>
        <GuestList onClick={onDeleteClick} data={guestList} />
        <PageContainer>
          <Pagination onChange={onPageClick} defaultCurrent={1} pageSize={12} total={guestCount} />
        </PageContainer>
      </Container>
      <FooterMain />
    </>
  );
}
// 토큰으로 접근확인후 밑에 동작들을 실행
function GuestList(props) {
  if (sessionStorage.getItem("access_token") !== null) {
    return (
      <List
        itemLayout="horizontal"
        dataSource={props.data}
        renderItem={(item) => (
          <>
            {console.log(item)}
            <List.Item
              actions={[
                <Button onClick={() => props.onClick(item.id)} className="btn btn-outline-secondary">
                  delete
                </Button>,
              ]}
            >
              <List.Item.Meta title={item.content} description={item.nickname} />
              <span>{item.createdDate}</span>
            </List.Item>
          </>
        )}
      />
    );
  } else {
    return (
      <List
        itemLayout="horizontal"
        dataSource={props.data}
        renderItem={(item) => (
          <List.Item>
            <List.Item.Meta title={item.content} description={item.nickname} />
            <div>{item.createdDate}</div>
          </List.Item>
        )}
      />
    );
  }
}

const Container = styled.div`
  padding-left: 3%;
  padding-right: 3%;
`;

const Title = styled.div`
  margin-top: 1%;
  margin-bottom: 2%;
  text-align: center;
  font-size: 35px;
  font-weight: bold;
`;

const GuestInput = styled.div`
  margin-top: 2%;
`;

const GuestInputTitle = styled.div`
  font-weight: bold;
  margin-bottom: 1%;
  font-size: 19.5px;
  color: dark-grey;
`;

const GuestListTitle = styled.span`
  font-weight: bold;
  font-size: 19.5px;
  margin-right: 3px;
  color: dark-grey;
`;

const GuestListAllTitle = styled.div`
  margin-bottom: 0.5%;
  margin-top: 3%;
  border-bottom: solid 1px lightgrey;
  padding-bottom: 0.5%;
  font-weight: bold;
`;

const Emoji = styled.span`
  margin-right: 6px;
`;

const Button = styled.span`
  float: right;
  padding: 3px 8px;
`;

const PageContainer = styled.div`
  text-align: center;
  bottom: 20px;
`;
