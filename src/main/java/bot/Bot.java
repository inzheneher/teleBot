package bot;

import httpRespons.HttpResponser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.json.JSONObject;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import wheather.Forcaster;

import java.util.Date;

public class Bot extends TelegramLongPollingBot {

    private final Logger log = (Logger) LogManager.getLogger(getClass());

    private static Bot bot;

    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        if ("time".equals(message)) {
            sendMsg(String.valueOf(update.getMessage().getChatId()), new Date().toString());
        } else if ("temperature".equals(message)) {
            double response = new JSONObject(HttpResponser.
                    getResponse(Forcaster.
                            getForcastIO().
                            getUrl("55.7201017", "37.6737875")).
                    get("currently").toString()).getDouble("temperature");
            sendMsg(String.valueOf(update.getMessage().getChatId()), response + " градусов Цельсия");
        } else {
            sendMsg(String.valueOf(update.getMessage().getChatId()), message);
        }
        log.trace(message);
    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "faceOfTable_bot";
    }

    public String getBotToken() {
        return "692318941:AAHWQgubZpWbn5OGRMAbbKC5YMTx6tjs5G8";
    }

    public static Bot getBot() {
        if (bot != null) {
            return bot;
        } else {
            return new Bot();
        }
    }
}
