package TinkoffHomework3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class DataReader {
    private String inputFilePath;
    private Random dataRandom = new Random();

    DataReader(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    String getData(String inputFilePath) {//На вход принимаем имя файла
        List<String> dataList = new ArrayList<>();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(inputFilePath);
            assert inputStream != null;
            BufferedReader dataReader = new BufferedReader(new InputStreamReader(inputStream, "windows-1251"));//Устанавливаем кодировку
            String readData;
            while ((readData = dataReader.readLine()) != null) {
                dataList.add(readData);// Добавляем все строки из файла в коллекцию
            }
            inputStream.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (
                IOException e) {
            System.out.println("I/O error.");
        }

        return dataList.get(dataRandom.nextInt(dataList.size()));//Возвращаем случайную строку из файла
    }
}
