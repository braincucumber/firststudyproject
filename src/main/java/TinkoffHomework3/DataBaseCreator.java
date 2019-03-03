package TinkoffHomework3;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;


public class DataBaseCreator {
    public static void main(String[] args) {
        try {
            //Создаем файл и лист Excel, указываем путь к нему
            String excelOutputFilePath = "E:\\PersonalData.xls";
            HSSFWorkbook userDataWorkbook = new HSSFWorkbook();
            HSSFSheet userDataSheet = userDataWorkbook.createSheet("FirstSheet");
            Random random = new Random();
            int minPostCode = 100000;
            int maxPostCode = 200000;
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
            //Создаем файл и таблицу в PDF, указываем путь к нему
            String pdfOutputFilePath = "E:\\PersonalData.pdf";
            Document pdfDocument = new Document(PageSize.A3.rotate());
            //Задаем шрифт, поддерживающий кириллицу
            BaseFont bf = BaseFont.createFont("c:\\Windows\\Fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font cyrillicFont = new Font(bf, 11);
            PdfWriter.getInstance(pdfDocument, new FileOutputStream(pdfOutputFilePath));
            pdfDocument.open();
            PdfPTable userDataPdfTable = new PdfPTable(userDataHeader.length);
            userDataPdfTable.setWidthPercentage(100);


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
            for (int count = 0; count <= 30; count++) {
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
                    cellValue = new PdfPCell(new Paragraph(String.valueOf(dateFormat.format(birthDate)), cyrillicFont));
                    userDataPdfTable.addCell(cellValue);
                } else {
                    for (String arrayIterator : femaleUserData) {
                        //Генерируем значения для женского пола
                        String value = dataReader.getData(arrayIterator);
                        bodyRows.createCell(Arrays.asList(femaleUserData).indexOf(arrayIterator)).setCellValue(value);
                        PdfPCell cellValue = new PdfPCell(new Paragraph(value, cyrillicFont));
                        userDataPdfTable.addCell(cellValue);
                    }
                    bodyRows.createCell(3).setCellValue(userAge);
                    PdfPCell cellValue = new PdfPCell(new Paragraph(String.valueOf(userAge), cyrillicFont));
                    userDataPdfTable.addCell(cellValue);

                    //Устанавливаем женский пол
                    bodyRows.createCell(4).setCellValue("Женский");
                    cellValue = new PdfPCell(new Paragraph("Женский", cyrillicFont));
                    userDataPdfTable.addCell(cellValue);

                    Cell birthDateCell = bodyRows.createCell(5);
                    birthDateCell.setCellValue(dateFormat.format(birthDate));
                    cellValue = new PdfPCell(new Paragraph(String.valueOf(dateFormat.format(birthDate)), cyrillicFont));
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

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}