package com.example.live_tino.user;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class Error extends Exception {
    String msg;
    String type;

    public Error(String msg, String type){
        super();
        this.msg = msg;
        this.type = type;

        // Save Error log in database...

        // Send error to slack message...
        this.sendToSlack();
    }

    private void  sendToSlack() {
        log.info("Slack으로 메시지 보내는중..");
        log.info("Error Type: " + this.type);
        log.info("Error Message: " + this.msg);

    }

}
