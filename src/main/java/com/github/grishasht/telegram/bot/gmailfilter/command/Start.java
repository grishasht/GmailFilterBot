package com.github.grishasht.telegram.bot.gmailfilter.command;

import com.github.grishasht.telegram.bot.gmailfilter.GmailFilterBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Start extends Command {

    public Start(GmailFilterBot bot) {
        super(bot);
    }

    @Override
    public void execute(Update update) throws TelegramApiException {

        final SendMessage message = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText("Hello! Let's start");

        bot.execute(message);
    }
}
