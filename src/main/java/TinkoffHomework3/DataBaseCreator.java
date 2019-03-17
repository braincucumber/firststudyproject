package TinkoffHomework3;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;


public class DataBaseCreator {
    public static void main(String[] args) {
        try {
            Random random = new Random();
            int userQuantity = random.nextInt(30);//Устанавливаем максимальное значение случайного числа пользьзователей
            ApiDataGenerator apiDataGenerator = new ApiDataGenerator();
            SQLDatabaseGenerator sqlDatabaseGenerator = new SQLDatabaseGenerator();

            String apiUrl = "https://randomapi.com/api/?key=DRVQ-GMHS-SZW5-5CF8&ref=6qqg94zo";
            //Проверяем подключение
            HttpURLConnection connectionCheck;
            connectionCheck = (HttpURLConnection) new URL(apiUrl).openConnection();
            connectionCheck.connect();//Подключаемся
            if (HttpURLConnection.HTTP_OK == connectionCheck.getResponseCode()) {//Проверяем код ответа: 200 - идем дальше
                apiDataGenerator.getApiData(userQuantity, apiUrl);//Генерируем данные из API
            } else {
                System.out.println("Fail: " + connectionCheck.getResponseCode() + ", " + connectionCheck.getResponseMessage() + ". Используем данные из БД");//Если код ответа !=200 - выводим код и текст ошибки и используем данные из БД
                sqlDatabaseGenerator.getDBData(userQuantity);//Генерируем данные из БД
            }
        } catch (Throwable cause) {
            cause.printStackTrace();//Ловим исключения
        }
    }
}