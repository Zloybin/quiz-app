function send_data(article, word, translation){
    let json = "{ \"article\":\"" + article + "\", \"word\":\"" + word + "\", \"translation\":\"" + translation + "\"}"
    let url = "http://localhost:7777/Quiz/database";
    $.post({
        url: url,
        data: json,
        success: function (data) {
            console.log(data);
        }
    });
}