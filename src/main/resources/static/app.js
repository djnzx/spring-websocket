var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/ws_0001');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        stompClient.subscribe('/topic/greetings', function (resp) {
            processResponse(resp.body);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
}

function getInputContent() {
    return $("#name").val();
}

function buildRequestDto(val) {
    return {'name': val };
}

function sendName() {
    body = JSON.stringify( buildRequestDto( getInputContent() ));
    stompClient.send("/app/hello", {}, body);
}

function processResponse(body) {
    object = JSON.parse(body);
    html = "<tr><td>" + object.content + "</td><td>" + object.id + "</td></tr>"
    $("#greetings").append(html);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#send" ).click(function() { sendName(); });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
});