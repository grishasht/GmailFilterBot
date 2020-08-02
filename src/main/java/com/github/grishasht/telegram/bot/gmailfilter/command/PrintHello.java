package com.github.grishasht.telegram.bot.gmailfilter.command;

import com.github.grishasht.telegram.bot.gmailfilter.GmailFilterBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PrintHello extends Command {

    public PrintHello(GmailFilterBot bot) {
        super(bot);
    }

    @Override
    public void execute(Update update) throws TelegramApiException {

        if (wasExecuted()){

            executeNextCommand(update);

            bot.setCurrentCommand("");

            return;
        }

        final Long chatId = update.getMessage().getChatId();

        final SendMessage enterYourNameMsg = new SendMessage()
                .setChatId(chatId)
                .setText("Hello! Enter your name");

        bot.execute(enterYourNameMsg);

        bot.setCurrentCommand(GmailFilterBot.COMMAND_HELLO);

        registerNextStep(new AnswerHello(bot));

        setWasExecuted(true);
    }

}
