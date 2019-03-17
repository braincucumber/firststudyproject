package TinkoffHomework3;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

class FileDataGenerator {
    private int userQuantity;

    FileDataGenerator(int userQuantity) {
        this.userQuantity = userQuantity;
    }

    void getFileData(int userQuantity) {
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
            SQLDatabaseCreator sqlDatabaseCreator = new SQLDatabaseCreator();
            //Создаем файл и таблицу в PDF, указываем путь к нему
            String pdfOutputFilePath = "PersonalData.pdf";
            Document pdfDocument = new Document(PageSize.A3.rotate());
            //Задаем шрифт, поддерживающий кириллицу
            Font font = FontFactory.getFont("/fonts/arial.ttf",
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 0.8f, Font.NORMAL, BaseColor.BLACK);
            BaseFont baseFont = font.getBaseFont();
            Font cyrillicFont = new Font(baseFont, 11);
            PdfWriter.getInstance(pdfDocument, new FileOutputStream(pdfOutputFilePath));
            pdfDocument.open();
            PdfPTable userDataPdfTable = new PdfPTable(userDataHeader.length);
            userDataPdfTable.setWidthPercentage(100);//Задаем максимальную ширину полей

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
                ArrayList<String> fileDataArray = new ArrayList<>();

                HSSFRow bodyRows = userDataSheet.createRow(count + 1);
                //Генерируем случайные значения
                birthDate = birthdateGenerator.getBirthday();
                userAge = birthdateGenerator.calculateAge(birthDate);
                innValue = innGenerator.generateInn();
                postCodeValue = random.nextInt(maxPostCode - minPostCode) + minPostCode;
                houseNumber = random.nextInt(100);
                apartmentNumber = random.nextInt(500);


                if (random.nextInt(100) > 50) {
                    for (String arrayIterator : maleUserData) {
                        String value = dataReader.getData(arrayIterator);
                        fileDataArray.add(Arrays.asList(maleUserData).indexOf(arrayIterator), value);
                    }
                    fileDataArray.add(3, "М");
                }
                else {
                    for (String arrayIterator : femaleUserData) {
                        String value = dataReader.getData(arrayIterator);
                        fileDataArray.add(Arrays.asList(femaleUserData).indexOf(arrayIterator), value);
                    }
                    fileDataArray.add(3, "Ж");
                }
                fileDataArray.add(3, String.valueOf(userAge));
                fileDataArray.add(5, dateFormat.format(birthDate));
                fileDataArray.add(6, innValue);
                fileDataArray.add(7, String.valueOf(postCodeValue));

                for (String arrayIterator : userAddressData) {
                    String value = dataReader.getData(arrayIterator);
                    fileDataArray.add((8 + Arrays.asList(userAddressData).indexOf(arrayIterator)), value);
                }

                for (String arrayIterator : fileDataArray) {
                    String value = fileDataArray.get(fileDataArray.indexOf(arrayIterator));
                    bodyRows.createCell(fileDataArray.indexOf(arrayIterator)).setCellValue(value);
                    PdfPCell cellValue = new PdfPCell(new Paragraph(value, cyrillicFont));
                    userDataPdfTable.addCell(cellValue);
                }
                fileDataArray.add(12, String.valueOf(houseNumber));
                fileDataArray.add(13, String.valueOf(apartmentNumber));
                sqlDatabaseCreator.dbData(fileDataArray);
                System.out.println("Done...");
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

        } catch (Throwable cause) {
            cause.printStackTrace();
        }
    }
}
