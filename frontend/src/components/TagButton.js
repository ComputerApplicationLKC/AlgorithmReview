import styled from "styled-components";
import { Link } from "react-router-dom";

function TagButton(props) {
    return (
        <Button>{props.name}</Button>
    )
}

const Button = styled.div`
    background: rgb(241, 243, 245);
    border: white;
    height: 2rem;
    border-radius: 1rem;
    display: inline-flex;
    -webkit-box-align: center;
    align-items: center;
    color: rgb(12, 166, 120);
    text-decoration: none;
    font-weight: 500;
    font-size: 1rem;
    padding-right: 10px;
    padding-left: 10px;
    margin-left: 3px;
    margin-right: 3px;
`

export default TagButton;