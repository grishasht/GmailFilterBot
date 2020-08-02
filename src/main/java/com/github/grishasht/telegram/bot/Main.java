package com.github.grishasht.telegram.bot;

import com.github.grishasht.telegram.bot.gmailfilter.GmailFilterBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

class Main {

    public static void main(String[] args) {

        ApiContextInitializer.init();

        final var botsApi = new TelegramBotsApi();

        try {

            botsApi.registerBot(new GmailFilterBot());

        } catch (TelegramApiRequestException e) {
            System.out.printf("Can't register telegram bot: \n%s", e.getCause());
        }
    }
}
