package TinkoffHomework3;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ApiDataGenerator {
    private int userQuantity;

    ApiDataGenerator(int userQuantity) {
        this.userQuantity = userQuantity;
    }

    void getApiData(int userQuantity) {
        SQLDatabaseCreator sqlDatabaseCreator = new SQLDatabaseCreator();
        String excelOutputFilePath = "PersonalData.xls";//Задаем путь файла Excel
        HSSFWorkbook userDataWorkbook = new HSSFWorkbook();//Создаем книгу
        HSSFSheet userDataSheet = userDataWorkbook.createSheet("FirstSheet");
        String[] userDataHeader = new String[]{"Имя", "Фамилия", "Отчество", "Возраст", "Пол", "Дата рождения", "ИНН", "Почтовый индекс", "Страна", "Область", "Город", "Улица", "Дом", "Квартира"};
        String apiUrl = "https://randomapi.com/api/?key=DRVQ-GMHS-SZW5-5CF8&ref=6qqg94zo";
        //Создаем подключение
        try {
            HttpURLConnection connection;
            List[] apiUserData = new List[userQuantity];//Создаем лист массивов для хранения данных пользователей
            for (int count = 0; count < userQuantity; count++) {
                connection = (HttpURLConnection) new URL(apiUrl).openConnection();//Открываем подключение для получения данных клиента
                connection.setRequestMethod("GET");
                connection.connect();
                StringBuilder sb = new StringBuilder();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));//Читаем входящий стрим
                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                String apiResults = sb.toString();//Собираем полученные строки в одну
                JsonObject jsonObject = new JsonParser().parse(apiResults).getAsJsonObject();//Парсим строку в JSON объект
                JsonArray jsonArray = jsonObject.getAsJsonArray("results");//Извлекаем из JSON объекта массив "results"
                ArrayList<String> arrayList = new ArrayList<>();
                //Считываем элементы JSON массива и заносим их в листовой массив
                for (JsonElement element : jsonArray) {
                    JsonObject resultsObj = element.getAsJsonObject();
                    JsonElement apiFirstName = resultsObj.get("firstName");
                    JsonElement apiLastName = resultsObj.get("lastName");
                    JsonElement apiPatronymic = resultsObj.get("patronymic");
                    JsonElement apiAge = resultsObj.get("age");
                    JsonElement apiGender = resultsObj.get("gender");
                    JsonElement apiBirthDate = resultsObj.get("birthDate");
                    JsonElement apiInn = resultsObj.get("inn");
                    JsonElement apiPostCode = resultsObj.get("postCode");
                    JsonElement apiCountry = resultsObj.get("country");
                    JsonElement apiState = resultsObj.get("state");
                    JsonElement apiCity = resultsObj.get("city");
                    JsonElement apiStreet = resultsObj.get("street");
                    JsonElement apiHouseNumber = resultsObj.get("houseNumber");
                    JsonElement apiApartmentNumber = resultsObj.get("apartmentNumber");
                    arrayList.add(apiFirstName.getAsString());
                    arrayList.add(apiLastName.getAsString());
                    arrayList.add(apiPatronymic.getAsString());
                    arrayList.add(apiAge.getAsString());
                    arrayList.add(apiGender.getAsString());
                    arrayList.add(apiBirthDate.getAsString());
                    arrayList.add(apiInn.getAsString());
                    arrayList.add(apiPostCode.getAsString());
                    arrayList.add(apiCountry.getAsString());
                    arrayList.add(apiState.getAsString());
                    arrayList.add(apiCity.getAsString());
                    arrayList.add(apiStreet.getAsString());
                    arrayList.add(apiHouseNumber.getAsString());
                    arrayList.add(apiApartmentNumber.getAsString());
                }
                sqlDatabaseCreator.dbData(arrayList);
                System.out.println("Done...");
                apiUserData[count] = arrayList;//Вносим полученный листовой массив в лист массивов
            }
            HSSFRow headRows = userDataSheet.createRow(0);//Создаем шапку таблицы
            for (String header : userDataHeader) {
                headRows.createCell(Arrays.asList(userDataHeader).indexOf(header)).setCellValue(header);
            }
            //Заполняем тело таблицы
            for (int count = 0; count < userQuantity; count++) {
                HSSFRow bodyRows = userDataSheet.createRow(count + 1);
                for (int i = 0; i < 14; i++) {
                    bodyRows.createCell(i).setCellValue(String.valueOf(apiUserData[count].get(i)));
                    userDataSheet.autoSizeColumn(i);
                }
            }
            //Пишем Excel файл
            FileOutputStream fileOut = new FileOutputStream(excelOutputFilePath);
            userDataWorkbook.write(fileOut);
            fileOut.close();
            userDataWorkbook.close();
            System.out.println("Excel файл создан при помощи API. Путь: " + excelOutputFilePath);

        } catch (Throwable cause) {
            cause.printStackTrace();//Ловим исключения
        }
    }
}