package TinkoffHomework3;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;


public class DataBaseCreator {
    public static void main(String[] args) {
        try {
            Random random = new Random();
            int userQuantity = random.nextInt(30);//Устанавливаем максимальное значение случайного числа пользьзователей
            ApiDataGenerator apiDataGenerator = new ApiDataGenerator(userQuantity);
            FileDataGenerator fileDataGenerator = new FileDataGenerator(userQuantity);

            String apiUrl = "https://randomapi.com/api/?key=DRVQ-GMHS-SZW5-5CF8&ref=6qqg94z"; //key=DRVQ-GMHS-SZW5-5CF8&ref=6qqg94zo
            //Проверяем подключение
            HttpURLConnection connectionCheck;
            connectionCheck = (HttpURLConnection) new URL(apiUrl).openConnection();
            connectionCheck.connect();//Подключаемся
            if (HttpURLConnection.HTTP_OK == connectionCheck.getResponseCode()) {//Проверяем код ответа: 200 - идем дальше !=200 - Выводим сообщение об ошибке и используем код из предыдущего задания
                apiDataGenerator.getApiData(userQuantity);
            } else {
                System.out.println("Fail: " + connectionCheck.getResponseCode() + ", " + connectionCheck.getResponseMessage());//Если код ответа !=200 - выводим код и текст ошибки и используем код из предыдущего задания
                fileDataGenerator.getFileData(userQuantity);
            }
        } catch (Throwable cause) {
            cause.printStackTrace();//Ловим исключения
        }
    }
}