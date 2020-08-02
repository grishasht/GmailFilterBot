package com.github.grishasht.telegram.bot.gmailfilter.command;

import com.github.grishasht.telegram.bot.gmailfilter.GmailFilterBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class Command {

    protected final GmailFilterBot bot;

    private boolean wasExecuted = false;
    private Command next;

    public Command(GmailFilterBot bot) {
        this.bot = bot;
    }

    public abstract void execute(Update update) throws TelegramApiException;

    protected void registerNextStep(Command next){
        this.next = next;
    }

    protected void executeNextCommand(Update update) throws TelegramApiException {

        this.next.execute(update);

    }

    public boolean wasExecuted() {
        return wasExecuted;
    }

    public void setWasExecuted(boolean wasExecuted) {
        this.wasExecuted = wasExecuted;
    }
}
