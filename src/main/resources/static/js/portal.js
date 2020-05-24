function invalidateSession() {
    var messagePanel = $("#message-panel");
    if(messagePanel != null){
        messagePanel.remove();
    }
    $.get("/invalidateSession", function (data) {
        $("#invalidatedSessionId")
            .append('<span id="message-panel">' + data.toString() + ' </span>');
    });
}