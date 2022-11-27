function dateSetting(step) {
    console.log(step);
    let today = new Date();
    let date = new Date();

    switch (step) {
        case "0", "1":
            date.setMonth(today.getMonth() + 3); // 3달 후
            break;
        case "2":
            date.setMonth(today.getMonth() + 2); // 2달 후
            break;
        case "3":
            date.setMonth(today.getMonth() + 1); // 1달 후
            break;
        case "4":
            date.setDate(today.getDate() + 14); // 2주 후
            break;
        case "5":
            date.setDate(today.getDate() + 7); // 1주 후
            break;
        default:
            return step;
    }

    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();

    month = month >= 10 ? month : '0' + month; // 날짜 포맷 맞추기
    day = day >= 10 ? day : '0' + day;

    console.log(year + "-" + month + "-" + day);
    return year + "-" + month + "-" + day;
}

export default dateSetting;
