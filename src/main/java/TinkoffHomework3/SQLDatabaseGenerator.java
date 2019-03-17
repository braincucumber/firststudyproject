package TinkoffHomework3;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SQLDatabaseGenerator {
    private String dbUrl;
    private String user;
    private String password;

    SQLDatabaseGenerator() {
    }

    void getDBData(int userQuantity) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        BirthdateGenerator birthdateGenerator = new BirthdateGenerator();
        FileDataGenerator fileDataGenerator = new FileDataGenerator();
        List<String> dataList = new ArrayList<>();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("database.txt");//Открываем стрим
            assert inputStream != null;
            BufferedReader dataReader = new BufferedReader(new InputStreamReader(inputStream));//Создаем ридер
            String readData;
            while ((readData = dataReader.readLine()) != null) {
                dataList.add(readData);// Добавляем все строки из файла в коллекцию
            }
            inputStream.close();//Закрываем стрим
            dbUrl = dataList.get(0);
            user = dataList.get(1);
            password = dataList.get(2);
            //Отлавливаем исключения
        } catch (
                FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (
                IOException e2) {
            System.out.println("I/O error.");
        }

        Connection dbConnection = null;
        Statement dbStatement = null;

        try {
            String excelOutputFilePath = "PersonalData.xls";//Задаем путь файла Excel
            String pdfOutputFilePath = "PersonalData.pdf";//Задаем путь файла PDF
            String[] userDataHeader = new String[]{"Имя", "Фамилия", "Отчество", "Возраст", "Пол", "Дата рождения", "ИНН", "Почтовый индекс", "Страна", "Область", "Город", "Улица", "Дом", "Квартира"};
            HSSFWorkbook userDataWorkbook = new HSSFWorkbook();//Создаем книгу
            HSSFSheet userDataSheet = userDataWorkbook.createSheet("FirstSheet");//Создаем лист

            //Создаем файл и таблицу в PDF, указываем путь к нему
            Document pdfDocument = new Document(PageSize.A3.rotate());
            PdfWriter.getInstance(pdfDocument, new FileOutputStream(pdfOutputFilePath));
            pdfDocument.open();
            PdfPTable userDataPdfTable = new PdfPTable(userDataHeader.length);
            userDataPdfTable.setWidthPercentage(100);//Задаем максимальную ширину полей

            //Задаем шрифт, поддерживающий кириллицу
            Font font = FontFactory.getFont("/fonts/arial.ttf",
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 0.8f, Font.NORMAL, BaseColor.BLACK);
            BaseFont baseFont = font.getBaseFont();
            Font cyrillicFont = new Font(baseFont, 11);

            String selectQuery = "SELECT * FROM userdata_db.persons p JOIN userdata_db.address a ON p.address_id = a.id ORDER BY RAND() LIMIT 1";//Запрос для получения случайной строки
            String rowCountQuery = "SELECT * FROM userdata_db.persons ORDER BY id DESC LIMIT 1; ";//Запрос для получения количества строк в таблице
            List[] dbUserData = new List[userQuantity];//Создаем лист массивов для хранения данных пользователей
            dbConnection = DriverManager.getConnection(dbUrl, user, password);//Создаем подключение
            dbStatement = dbConnection.createStatement();
            ResultSet rowCountResult = dbStatement.executeQuery(rowCountQuery);//Выполняем запрос для подсчета строк

            if (rowCountResult.next() && rowCountResult.getInt("id") > userQuantity) {//Если строк достаточно, то используем генерацию из БД

                for (int count = 0; count < userQuantity; count++) {
                    ArrayList<String> dataArray = new ArrayList<>();
                    ResultSet resultSet = dbStatement.executeQuery(selectQuery);//Выполяем запрос на получение случайной строки
                    resultSet.next();

                    //Заполняем массив полученными данными
                    dataArray.add(resultSet.getString("name"));
                    dataArray.add(resultSet.getString("surname"));
                    dataArray.add(resultSet.getString("middlename"));
                    dataArray.add(String.valueOf(birthdateGenerator.calculateAge(resultSet.getDate("birthday"))));
                    dataArray.add(resultSet.getString("gender"));
                    dataArray.add(dateFormat.format(resultSet.getDate("birthday")));
                    dataArray.add(resultSet.getString("inn"));
                    dataArray.add(resultSet.getString("postcode"));
                    dataArray.add(resultSet.getString("country"));
                    dataArray.add(resultSet.getString("region"));
                    dataArray.add(resultSet.getString("city"));
                    dataArray.add(resultSet.getString("street"));
                    dataArray.add(resultSet.getString("house"));
                    dataArray.add(resultSet.getString("flat"));
                    dbUserData[count] = dataArray;//Вносим полученный листовой массив в лист массивов
                }

                //Создаем шапку таблицы
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
                for (int count = 0; count < userQuantity; count++) {
                    HSSFRow bodyRows = userDataSheet.createRow(count + 1);
                    for (int i = 0; i < 14; i++) {
                        bodyRows.createCell(i).setCellValue(String.valueOf(dbUserData[count].get(i)));
                        PdfPCell cellValue = new PdfPCell(new Paragraph(String.valueOf(dbUserData[count].get(i)), cyrillicFont));
                        userDataPdfTable.addCell(cellValue);
                        userDataSheet.autoSizeColumn(i);
                    }
                    userDataPdfTable.completeRow();
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
            } else {//Если строк меньше необходимого количества пользователей, то используем генерацию из файлов
                fileDataGenerator.getFileData(userQuantity);
            }
        } catch (SQLException exception) {//Ловим исключения
            System.out.println("SQLException :" + exception.toString());
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                if (dbStatement != null) {//Закрываем стрим
                    dbStatement.close();
                }
            } catch (SQLException exception2) {
                System.out.println("SQLException :" + exception2.toString());
            }
            try {
                if (dbConnection != null) {//Закрываем соединение
                    dbConnection.close();
                }
            } catch (SQLException exception) {
                System.out.println("SQLException :" + exception.toString());
            }
        }
    }


}
