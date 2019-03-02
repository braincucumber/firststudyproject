package TinkoffHomework3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class DataReader {
             private String inputFilePath;
             private Random dataRandom = new Random();
             private String dataResult;
           DataReader(String inputFilePath) {
               this.inputFilePath = inputFilePath;
           }
           String getData(String inputFilePath) {
               List<String> dataList = new ArrayList<>();
               try {
                   BufferedReader dataReader = new BufferedReader(new FileReader(inputFilePath));
                   String readData;
                   while ((readData = dataReader.readLine()) != null) {
                       dataList.add(readData);               // читаем все слова из файла и добавляем в коллекцию
                   }
               } catch (
                       FileNotFoundException e) {
                   System.out.println("File not found.");
               } catch (
                       IOException e) {
                   System.out.println("I/O error.");
               }

              return dataResult = dataList.get(dataRandom.nextInt(dataList.size()));
           }
}
