package org.simple;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class App {
    public static void main(String[] args) {

        try {
            String token = "";
            if (args.length > 0) {
                token = String.valueOf(args[0]);
                TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
                botsApi.registerBot(new WhatIsYourIndianName(token));
            } else {
                System.err.println("Token is not correct or missing.");
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}