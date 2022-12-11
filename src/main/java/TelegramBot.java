import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class TelegramBot extends TelegramLongPollingBot {

    private final String BOT_TOKEN = "5841322766:AAGolTb4TBOxG3YO__uNdifJpPcgGk-tJ0Y";
    private final String BOT_NAME = "VlNikoAd_bot";
    Storage storage;
    ReplyKeyboardMarkup replyKeyboardMarkup;

    TelegramBot() {
        storage = new Storage();
        initKeyboard();
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                Message inMessage = update.getMessage();
                System.out.println(inMessage.toString());

                String chartId = inMessage.getChatId().toString();

                String response = parseMessage(inMessage.getText());
                SendMessage outMessage = new SendMessage();

                outMessage.setReplyMarkup(replyKeyboardMarkup);
                outMessage.setChatId(chartId);
                outMessage.setText(response);


                execute(outMessage);
            }



        } catch (TelegramApiException telegramApiException) {
            telegramApiException.printStackTrace();
        }
    }
    void initKeyboard()
    {
        //Создаем объект будущей клавиатуры и выставляем нужные настройки
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true); //подгоняем размер
        replyKeyboardMarkup.setOneTimeKeyboard(false); //скрываем после использования

        //Создаем список с рядами кнопок
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        //Создаем один ряд кнопок и добавляем его в список
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRows.add(keyboardRow);
        //Добавляем одну кнопку с текстом "Просвяти" наш ряд
        keyboardRow.add(new KeyboardButton("Просвяти"));
        //добавляем лист с одним рядом кнопок в главный объект
        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }

    public String parseMessage(String textMessage) {
        String response;
        if (textMessage.equals("/start")) {
            response = "Привет от Владоса! Жми /get :) ";
        } else if (textMessage.equals("/get") || textMessage.equals("Просвяти"))
            response = storage.getRandomText();
         else
            response = "Нажми кнопку 'Просвяти'";

        return response;
    }
}
