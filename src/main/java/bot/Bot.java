package bot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    private final Logger log = (Logger) LogManager.getLogger(Bot.class);

    private static Bot bot;

    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        sendMsg(String.valueOf(update.getMessage().getChatId()), message);
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
