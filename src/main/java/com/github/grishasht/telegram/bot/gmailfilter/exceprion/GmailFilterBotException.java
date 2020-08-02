package com.github.grishasht.telegram.bot.gmailfilter.exceprion;

public class GmailFilterBotException extends RuntimeException{

    public GmailFilterBotException() {
    }

    public GmailFilterBotException(String message) {
        super(message);
    }

    public GmailFilterBotException(String message, Throwable cause) {
        super(message, cause);
    }

    public GmailFilterBotException(Throwable cause) {
        super(cause);
    }
}
