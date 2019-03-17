package TinkoffHomework3;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

class SQLDatabaseCreator {
    private String dbUrl;
    private String user;
    private String password;

    void dbData (ArrayList<String> dataArray) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<String> dataList = new ArrayList<>();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("database.txt");//Открываем стрим
            assert inputStream != null;
            BufferedReader dataReader = new BufferedReader(new InputStreamReader(inputStream));//Устанавливаем кодировку
            String readData;
            while ((readData = dataReader.readLine()) != null) {
                dataList.add(readData);// Добавляем все строки из файла в коллекцию
            }
            inputStream.close();//Закрываем стрим
            dbUrl = dataList.get(0);
            user =  dataList.get(1);
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
        String duplicateQuery = "SELECT * FROM userdata_db.persons WHERE surname = '" + dataArray.get(1) +"'";
        try {
            LocalDate birthDate = LocalDate.parse(dataArray.get(5), formatter);
            //System.out.println("Connecting to database...");
            dbConnection = DriverManager.getConnection(dbUrl, user, password);
            dbStatement = dbConnection.createStatement();
            //System.out.println("Inserting data...");
            ResultSet resultSet = dbStatement.executeQuery(duplicateQuery);
            while (resultSet.next()) {
                String s = resultSet.getString("surname");
                System.out.println(s);
            }
            dbStatement.execute("INSERT INTO userdata_db.address (postcode, country, region, city, street, house, flat)" + " " + "VALUES ('" + dataArray.get(7) + "', '" + dataArray.get(8) + "', '" + dataArray.get(9) + "', '" + dataArray.get(10) + "', '" + dataArray.get(11) + "', '" + dataArray.get(12) +"', '" + dataArray.get(13) + "')");
            dbStatement.execute("INSERT INTO userdata_db.persons (surname, name, middlename, birthday, gender, inn, address_id)" + " " + "VALUES ('" + dataArray.get(1) + "', '" + dataArray.get(0) + "', '" + dataArray.get(2) + "', '" + birthDate + "', '" + dataArray.get(4) + "', '" + dataArray.get(7) + "', (SELECT id FROM userdata_db.address WHERE postcode = " + dataArray.get(7) + "))");
            //System.out.println("Done...");
        } catch (SQLException exception) {
            System.out.println("SQLException :" + exception.toString());
        } catch (Exception e) {
            System.out.println("Exception :" + e.toString());
        } finally {
            try {
                if (dbStatement != null) {
                    dbStatement.close();
                }
            } catch (SQLException exception2) {
                System.out.println("SQLException :" + exception2.toString());
            }
            try {
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException exception) {
                System.out.println("SQLException :" + exception.toString());
            }
        }
    }
}
