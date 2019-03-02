package TinkoffHomework3;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Random;


public class ExcelCreator {
    public static void main(String[] args) {
        try {
            String outputFilePath = "E:\\PersonalData.xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");
            Random random = new Random();
            int minPostCode = 100000;
            int maxPostCode = 200000;
            Date birthDate;
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
            short dateFormat = creationHelper.createDataFormat().getFormat("dd-MM-yyyy");
            cellStyle.setDataFormat(dateFormat);
            InnGenerator innGenerator = new InnGenerator("");
            DataReader dataReader = new DataReader("");
            BirthdateGenerator birthdateGenerator = new BirthdateGenerator ();

            HSSFRow rowhead = sheet.createRow(0);
            rowhead.createCell(0).setCellValue("Имя");
            rowhead.createCell(1).setCellValue("Фамилия");
            rowhead.createCell(2).setCellValue("Отчество");
            rowhead.createCell(3).setCellValue("Возраст");
            rowhead.createCell(4).setCellValue("Пол");
            rowhead.createCell(5).setCellValue("Дата рождения");
            rowhead.createCell(6).setCellValue("ИНН");
            rowhead.createCell(7).setCellValue("Почтовый индекс");
            rowhead.createCell(8).setCellValue("Страна");
            rowhead.createCell(9).setCellValue("Область");
            rowhead.createCell(10).setCellValue("Город");
            rowhead.createCell(11).setCellValue("Улица");
            rowhead.createCell(12).setCellValue("Дом");
            rowhead.createCell(13).setCellValue("Квартира");

            for (int count = 0;count <= 30;count++) {
                HSSFRow dataRows = sheet.createRow(count+1);
                Cell birthDateCell = dataRows.createCell(5);
                birthDate = birthdateGenerator.getBirthday();
                birthDateCell.setCellValue(birthDate);
                birthDateCell.setCellStyle(cellStyle);
                if (random.nextInt(100) > 50) {
                    dataRows.createCell(0).setCellValue(dataReader.getData("E:\\IntelliJ IDEA Community Edition 2018.2.4\\IdeaProjects\\src\\main\\resources\\male_names.txt"));
                    dataRows.createCell(1).setCellValue(dataReader.getData("E:\\IntelliJ IDEA Community Edition 2018.2.4\\IdeaProjects\\src\\main\\resources\\male_surnames.txt"));
                    dataRows.createCell(2).setCellValue(dataReader.getData("E:\\IntelliJ IDEA Community Edition 2018.2.4\\IdeaProjects\\src\\main\\resources\\male_patronymic.txt"));
                    dataRows.createCell(4).setCellValue("Мужской");
                }
                else {
                    dataRows.createCell(0).setCellValue(dataReader.getData("E:\\IntelliJ IDEA Community Edition 2018.2.4\\IdeaProjects\\src\\main\\resources\\female_names.txt"));
                    dataRows.createCell(1).setCellValue(dataReader.getData("E:\\IntelliJ IDEA Community Edition 2018.2.4\\IdeaProjects\\src\\main\\resources\\female_surnames.txt"));
                    dataRows.createCell(2).setCellValue(dataReader.getData("E:\\IntelliJ IDEA Community Edition 2018.2.4\\IdeaProjects\\src\\main\\resources\\female_patronymic.txt"));
                    dataRows.createCell(4).setCellValue("Женский");
                }
                dataRows.createCell(3).setCellValue(birthdateGenerator.calculateAge(birthDate));
                dataRows.createCell(6).setCellValue(innGenerator.generateInn());
                dataRows.createCell(7).setCellValue(random.nextInt(maxPostCode-minPostCode) + minPostCode);
                dataRows.createCell(8).setCellValue(dataReader.getData("E:\\IntelliJ IDEA Community Edition 2018.2.4\\IdeaProjects\\src\\main\\resources\\country.txt"));
                dataRows.createCell(9).setCellValue(dataReader.getData("E:\\IntelliJ IDEA Community Edition 2018.2.4\\IdeaProjects\\src\\main\\resources\\region.txt"));
                dataRows.createCell(10).setCellValue(dataReader.getData("E:\\IntelliJ IDEA Community Edition 2018.2.4\\IdeaProjects\\src\\main\\resources\\city.txt"));
                dataRows.createCell(11).setCellValue(dataReader.getData("E:\\IntelliJ IDEA Community Edition 2018.2.4\\IdeaProjects\\src\\main\\resources\\street.txt"));
                dataRows.createCell(12).setCellValue(random.nextInt(100));
                dataRows.createCell(13).setCellValue(random.nextInt(500));
            }

            FileOutputStream fileOut = new FileOutputStream(outputFilePath);
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();
                System.out.println("Файл создан. Путь: " + outputFilePath);

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}