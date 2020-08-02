package com.github.grishasht.telegram.bot.gmailfilter;

import com.github.grishasht.telegram.bot.gmailfilter.command.PrintHello;
import com.github.grishasht.telegram.bot.gmailfilter.command.Command;
import com.github.grishasht.telegram.bot.gmailfilter.command.Start;
import com.github.grishasht.telegram.bot.gmailfilter.exceprion.GmailFilterBotException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class GmailFilterBot extends TelegramLongPollingBot {

    public static final String BOT_CREDENTIALS_PATH = "bot-credentials.properties";

    public static final String COMMAND_HELLO = "/hello";
    public static final String COMMAND_START = "/start";

    private final String BOT_TOKEN;
    private final String BOT_USERNAME;

    private final Map<String, Command> commandMap = new HashMap<>();

    private String currentCommand = "";

    public GmailFilterBot() {

        final Properties botProperties = new Properties();

        try {
            botProperties.load(this.getClass().getClassLoader().getResourceAsStream(BOT_CREDENTIALS_PATH));

            BOT_TOKEN = botProperties.getProperty("token");
            BOT_USERNAME = botProperties.getProperty("username");

        } catch (IOException e) {
            throw new GmailFilterBotException("Can't read bot credentials from file: " + BOT_CREDENTIALS_PATH, e);
        }

        initCommands();

    }

    private void initCommands() {

        commandMap.put(COMMAND_START, new Start(this));
        commandMap.put(COMMAND_HELLO, new PrintHello(this));

    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            try {

                if ("".equals(getCurrentCommand())) {

                    commandMap.get(update.getMessage().getText()).execute(update);

                } else {

                    commandMap.get(currentCommand).execute(update);

                }

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    public String getCurrentCommand() {
        return currentCommand;
    }

    public void setCurrentCommand(String currentCommand) {
        this.currentCommand = currentCommand;
    }
}
