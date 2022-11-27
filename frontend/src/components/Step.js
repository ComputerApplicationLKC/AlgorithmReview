import styled from 'styled-components'

import selectedStar from '../images/star_selected.png';
import notSelectedStar from '../images/star_not_selected.png';

function Step() {
    function selectStar(step) {
        console.log(step)
        let num = 1;
        const result = [];
        for (num = 1; num <= step; num++)
            document.getElementById("star" + num).src = selectedStar;
        for (num = step + 1; num <= 5; num++)
            document.getElementById("star" + num).src = notSelectedStar;
        return result;
    };

    return (
        <span>
            <Star id="star1" src={selectedStar} onClick={() => selectStar(1)} /><Star id="star2" src={notSelectedStar} onClick={() => selectStar(2)} /><Star id="star3" src={notSelectedStar} onClick={() => selectStar(3)} /><Star id="star4" src={notSelectedStar} onClick={() => selectStar(4)} /><Star id="star5" src={notSelectedStar} onClick={() => selectStar(5)} />
        </span>
    )
}


const Star = styled.img`
    padding-bottom: 6px;
    width: 30px;
`

export default Step