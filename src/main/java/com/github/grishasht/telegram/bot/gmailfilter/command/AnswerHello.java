package com.github.grishasht.telegram.bot.gmailfilter.command;

import com.github.grishasht.telegram.bot.gmailfilter.GmailFilterBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AnswerHello extends Command {

    public AnswerHello(GmailFilterBot bot) {
        super(bot);
    }

    @Override
    public void execute(Update update) throws TelegramApiException {

        final Message message = update.getMessage();
        final SendMessage helloAnswer = new SendMessage()
                .setChatId(message.getChatId())
                .setText("Hello " + message.getText() + "!");

        bot.execute(helloAnswer);
    }
}
