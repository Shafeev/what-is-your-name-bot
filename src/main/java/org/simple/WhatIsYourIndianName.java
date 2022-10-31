package org.simple;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;

public class WhatIsYourIndianName extends TelegramLongPollingBot {
    private String token = "";
    private static final String BOT_NAME = "WHAT_IS_THE_NAME";
    private static final HashMap<Integer, String> dayName = new HashMap<>();
    private static final HashMap<Integer, String> monthName = new HashMap<>();

    static {
        dayName.put(1, "Картман");
        dayName.put(2, "Змей");
        dayName.put(3, "Король");
        dayName.put(4, "Помидор");
        dayName.put(5, "Меч");
        dayName.put(6, "Кот");
        dayName.put(7, "Фрукт");
        dayName.put(8, "Ботинок");
        dayName.put(9, "Орел");
        dayName.put(10, "Хрящ");
        dayName.put(11, "Батон");
        dayName.put(12, "Салат");
        dayName.put(13, "Волк");
        dayName.put(14, "Шнур");
        dayName.put(15, "Жмых");
        dayName.put(16, "Клюв");
        dayName.put(17, "Дождь");
        dayName.put(18, "Заяц");
        dayName.put(19, "Куст");
        dayName.put(20, "Щит");
        dayName.put(21, "Пух");
        dayName.put(22, "Глаз");
        dayName.put(23, "Лис");
        dayName.put(24, "Друг");
        dayName.put(25, "Лист");
        dayName.put(26, "Нос");
        dayName.put(27, "Сын");
        dayName.put(28, "Грач");
        dayName.put(29, "Чай");
        dayName.put(30, "День");
        dayName.put(31, "Носок");

        monthName.put(1, "Дикий");
        monthName.put(2, "Рыжиий");
        monthName.put(3, "Хитрый");
        monthName.put(4, "Мудрый");
        monthName.put(5, "Пупырчатый");
        monthName.put(6, "Главный");
        monthName.put(7, "Грозный");
        monthName.put(8, "Нежный");
        monthName.put(9, "Облачный");
        monthName.put(10, "Кислый");
        monthName.put(11, "Смелый");
        monthName.put(12, "Снежный");
    }

    public WhatIsYourIndianName(String token) {
        this.token = token;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage()) {
                Message inMessage = update.getMessage();
                StringBuilder builder = new StringBuilder();
                Long chatId = inMessage.getChatId();
                if (inMessage.hasText()) {
                    String input = inMessage.getText();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d-M-u", Locale.ENGLISH);
                    LocalDate date = LocalDate.parse(input, dtf);
                    Integer dayOfMonth = date.getDayOfMonth();
                    Integer month = date.getMonthValue();
                    builder.append(monthName.get(month));
                    builder.append(" ");
                    builder.append(dayName.get(dayOfMonth));
                    sendMessage(chatId, builder.toString());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private synchronized void sendMessage(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
