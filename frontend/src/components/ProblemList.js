import styled from "styled-components";
import { Link } from "react-router-dom";

import TagButton from "./TagButton";

function ProblemList(props) {
    const problemList = props.problemList
    {
        return (
            <Container className="container">
                {problemList.length != 0 &&
                    problemList.map((problem) => {
                        return (
                            <CardComponent className="card m-2">
                                <Card onClick={props.onClick} id={problem.id} title={problem.title} step={problem.step} tagList={problem.tagList} />
                            </CardComponent>
                        )
                    })}
            </Container>
        )
    }
}

function Card(props) {
    return (
        <div>
            <Step>{"⭐️".repeat(props.step)}</Step><br />
            <div className="card-body">
                <Link to={`/problems/${props.id}`} style={{ textDecoration: 'none' }}><div className="card-title"><h4>{props.title}</h4></div></Link>
                <br />
                <TagList onClick={props.onClick} tagList={props.tagList} />
            </div>
        </div>
    )
}


function TagList(props) {
    const tagList = props.tagList
    return (
        <div>
            {tagList.length != 0 &&
                tagList.map((o) => {
                    return (
                        <Link onClick={() => props.onClick(o.tag.tagName)} style={{ textDecoration: 'none' }}><TagButton name={o.tag.tagName} /></Link>
                    )
                })}
        </div>
    )
}

const Container = styled.div`
    item-align: center;
`

const Step = styled.div`
    float: right;
    font-weight: bold;
    font-size: 22px;
    margin-right: 8px;
    margin-top: 6px;
`

const CardComponent = styled.div`
    margin: 5px;
    padding-bottom: 1.3%;
    width: 350px;
    display: inline-block;
`

export default ProblemList;
