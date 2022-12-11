import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Storage {

    private ArrayList<String> testList;

    Storage() {

        testList = new ArrayList<>();
        parser("https://citatnica.ru/citaty/mudrye-tsitaty-velikih-lyudej");
    }

    String getRandomText() {
        int randomValue = (int) (Math.random() * testList.size());
        return testList.get(randomValue);
    }

    void parser(String strURL)
    {
        String classNmae = "su-note-inner su-u-clearfix su-u-trim";
        Document doc = null;
        try {
            //Получаем документ нужной нам страницы
            doc = Jsoup.connect(strURL).maxBodySize(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Получаем группу объектов, обращаясь методом из Jsoup к определенному блоку
        Elements elQuote = doc.getElementsByClass(classNmae);

        //Достаем текст из каждого объекта поочереди и добавляем в наше хранилище
        elQuote.forEach(el -> {
            testList.add(el.text());
        });
    }

}
