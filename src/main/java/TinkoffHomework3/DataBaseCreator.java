package TinkoffHomework3;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;


public class DataBaseCreator {
    public static void main(String[] args) {
        try {
            String excelOutputFilePath = "PersonalData.xls";//Задаем путь файла Excel
            HSSFWorkbook userDataWorkbook = new HSSFWorkbook();//Создаем книгу
            HSSFSheet userDataSheet = userDataWorkbook.createSheet("FirstSheet");//Создаем лист
            Random random = new Random();
            int minPostCode = 100000;//Минимальное значение индекса из задания
            int maxPostCode = 200000;//Максимальное значение индекса из задания
            int userAge;
            String innValue;
            int postCodeValue;
            int houseNumber;
            int apartmentNumber;
            int userQuantity = random.nextInt(30);//Устанавливаем максимальное значение случайного числа пользьзователей
            Date birthDate;
            //Создаем массивы с названиями полей шапки и названиями файлов с данными
            String[] userDataHeader = new String[]{"Имя", "Фамилия", "Отчество", "Возраст", "Пол", "Дата рождения", "ИНН", "Почтовый индекс", "Страна", "Область", "Город", "Улица", "Дом", "Квартира"};
            String[] maleUserData = new String[]{"male_names.txt", "male_surnames.txt", "male_patronymic.txt"};
            String[] femaleUserData = new String[]{"female_names.txt", "female_surnames.txt", "female_patronymic.txt"};
            String[] userAddressData = new String[]{"country.txt", "region.txt", "city.txt", "street.txt"};
            //Задаем формат даты
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            InnGenerator innGenerator = new InnGenerator("");
            DataReader dataReader = new DataReader("");
            BirthdateGenerator birthdateGenerator = new BirthdateGenerator();
            //Создаем файл и таблицу в PDF, указываем путь к нему
            String pdfOutputFilePath = "PersonalData.pdf";
            Document pdfDocument = new Document(PageSize.A3.rotate());
            //Задаем шрифт, поддерживающий кириллицу
            BaseFont bf = BaseFont.createFont("c:\\Windows\\Fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font cyrillicFont = new Font(bf, 11);
            PdfWriter.getInstance(pdfDocument, new FileOutputStream(pdfOutputFilePath));
            pdfDocument.open();
            PdfPTable userDataPdfTable = new PdfPTable(userDataHeader.length);
            userDataPdfTable.setWidthPercentage(100);//Задаем максимальную ширину полей

            String apiUrl = "https://randomapi.com/api/?key=DRVQ-GMHS-SZW5-5CF8&ref=6qqg94zo";
            //Создаем подключение
            HttpURLConnection connectionCheck;
            connectionCheck = (HttpURLConnection) new URL(apiUrl).openConnection();
            connectionCheck.connect();//Подключаемся
            if (HttpURLConnection.HTTP_OK == connectionCheck.getResponseCode()) {//Проверяем код ответа: 200 - идем дальше !=200 - Выводим сообщение об ошибке и используем код из предыдущего задания
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
                            userDataSheet.autoSizeColumn(i);//Автоматически расширяем колонки до нужного размера
                        }
                    }
                    //Пишем Excel файл
                    FileOutputStream fileOut = new FileOutputStream(excelOutputFilePath);
                    userDataWorkbook.write(fileOut);
                    fileOut.close();
                    userDataWorkbook.close();
                    System.out.println("Excel файл создан. Путь: " + excelOutputFilePath);

                } catch (Throwable cause) {
                    cause.printStackTrace();//Ловим исключения
                } finally {
                    connectionCheck.disconnect();//Закрываем подключение
                }
            } else {
                System.out.println("Fail: " + connectionCheck.getResponseCode() + ", " + connectionCheck.getResponseMessage());//Если код ответа !=200 - выводим код и текст ошибки и используем код из предыдущего задания

                // Создаем шапку таблицы
                HSSFRow headRows = userDataSheet.createRow(0);

                for (String header : userDataHeader) {
                    //Для Excel файла
                    headRows.createCell(Arrays.asList(userDataHeader).indexOf(header)).setCellValue(header);
                    //Для PDF
                    PdfPCell headerCell = new PdfPCell();
                    headerCell.setPhrase(new Phrase(header, cyrillicFont));
                    userDataPdfTable.addCell(headerCell);
                }
                userDataPdfTable.completeRow();

                //Заполняем тело таблицы
                for (int count = 0; count <= userQuantity; count++) {
                    HSSFRow bodyRows = userDataSheet.createRow(count + 1);
                    //Генерируем случайные значения
                    birthDate = birthdateGenerator.getBirthday();
                    dateFormat.format(birthDate);
                    userAge = birthdateGenerator.calculateAge(birthDate);
                    innValue = innGenerator.generateInn();
                    postCodeValue = random.nextInt(maxPostCode - minPostCode) + minPostCode;
                    houseNumber = random.nextInt(100);
                    apartmentNumber = random.nextInt(500);

                    //Рандомизируем пол
                    if (random.nextInt(100) > 50) {
                        for (String arrayIterator : maleUserData) {
                            //Генерируем значения для мужского пола
                            String value = dataReader.getData(arrayIterator);
                            bodyRows.createCell(Arrays.asList(maleUserData).indexOf(arrayIterator)).setCellValue(value);
                            PdfPCell cellValue = new PdfPCell(new Paragraph(value, cyrillicFont));
                            userDataPdfTable.addCell(cellValue);
                        }

                        //Устанавливаем возраст
                        bodyRows.createCell(3).setCellValue(userAge);
                        PdfPCell cellValue = new PdfPCell(new Paragraph(String.valueOf(userAge), cyrillicFont));
                        userDataPdfTable.addCell(cellValue);

                        //Устанавливаем мужской пол
                        bodyRows.createCell(4).setCellValue("Мужской");
                        cellValue = new PdfPCell(new Paragraph("Мужской", cyrillicFont));
                        userDataPdfTable.addCell(cellValue);

                        //Устанавливаем дату рождения
                        Cell birthDateCell = bodyRows.createCell(5);
                        birthDateCell.setCellValue(dateFormat.format(birthDate));
                        cellValue = new PdfPCell(new Paragraph(dateFormat.format(birthDate), cyrillicFont));
                        userDataPdfTable.addCell(cellValue);
                    } else {
                        for (String arrayIterator : femaleUserData) {
                            //Генерируем значения для женского пола
                            String value = dataReader.getData(arrayIterator);
                            bodyRows.createCell(Arrays.asList(femaleUserData).indexOf(arrayIterator)).setCellValue(value);
                            PdfPCell cellValue = new PdfPCell(new Paragraph(value, cyrillicFont));
                            userDataPdfTable.addCell(cellValue);
                        }
                        //Устанавливаем возраст
                        bodyRows.createCell(3).setCellValue(userAge);
                        PdfPCell cellValue = new PdfPCell(new Paragraph(String.valueOf(userAge), cyrillicFont));
                        userDataPdfTable.addCell(cellValue);

                        //Устанавливаем женский пол
                        bodyRows.createCell(4).setCellValue("Женский");
                        cellValue = new PdfPCell(new Paragraph("Женский", cyrillicFont));
                        userDataPdfTable.addCell(cellValue);

                        //Устанавливаем дату рождения
                        Cell birthDateCell = bodyRows.createCell(5);
                        birthDateCell.setCellValue(dateFormat.format(birthDate));
                        cellValue = new PdfPCell(new Paragraph(dateFormat.format(birthDate), cyrillicFont));
                        userDataPdfTable.addCell(cellValue);
                    }
                    //Устанавливаем значение ИНН
                    bodyRows.createCell(6).setCellValue(innValue);
                    PdfPCell cellValue = new PdfPCell(new Paragraph(String.valueOf(innValue), cyrillicFont));
                    userDataPdfTable.addCell(cellValue);

                    //Устанавливаем значение индекса
                    bodyRows.createCell(7).setCellValue(postCodeValue);
                    cellValue = new PdfPCell(new Paragraph(String.valueOf(postCodeValue), cyrillicFont));
                    userDataPdfTable.addCell(cellValue);

                    for (String arrayIterator : userAddressData) {
                        //Генерируем случайные значения адреса
                        String value = dataReader.getData(arrayIterator);
                        bodyRows.createCell(Arrays.asList(userAddressData).indexOf(arrayIterator) + 8).setCellValue(value);
                        cellValue = new PdfPCell(new Paragraph(value, cyrillicFont));
                        userDataPdfTable.addCell(cellValue);
                    }

                    //Устанавливаем номер дома
                    bodyRows.createCell(12).setCellValue(houseNumber);
                    cellValue = new PdfPCell(new Paragraph(String.valueOf(houseNumber), cyrillicFont));
                    userDataPdfTable.addCell(cellValue);

                    //Устанавливаем номер квартиры
                    bodyRows.createCell(13).setCellValue(apartmentNumber);
                    cellValue = new PdfPCell(new Paragraph(String.valueOf(apartmentNumber), cyrillicFont));
                    userDataPdfTable.addCell(cellValue);

                    userDataPdfTable.completeRow();
                }
                //Автоматически расширяем колонки до нужного размера
                for (int count = 0; count <= 13; count++) {
                    userDataSheet.autoSizeColumn(count);
                }

                //Пишем PDF
                pdfDocument.add(userDataPdfTable);
                pdfDocument.close();
                System.out.println("PDF файл создан. Путь: " + pdfOutputFilePath);


                //Пишем Excel
                FileOutputStream fileOut = new FileOutputStream(excelOutputFilePath);
                userDataWorkbook.write(fileOut);
                fileOut.close();
                userDataWorkbook.close();
                System.out.println("Excel файл создан. Путь: " + excelOutputFilePath);
            }
        } catch (Throwable cause) {
            cause.printStackTrace();//Ловим исключения
        }
    }
}