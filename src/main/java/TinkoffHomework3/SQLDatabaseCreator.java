package TinkoffHomework3;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class SQLDatabaseCreator {
    private String dbUrl;
    private String user;
    private String password;

    void dbData(ArrayList<String> dataArray) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");//Задаем формат получаемой даты
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

            //Задаем URL, user, password, полученные из файла
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
        Statement dbResultStatement;
        String duplicateQuery = "SELECT * FROM userdata_db.persons WHERE surname = '" + dataArray.get(1) + "' and name = '" + dataArray.get(0) + "' and middlename = '" + dataArray.get(2) + "'";// Запрос для поиска дубликатов данных
        try {
            LocalDate birthDate = LocalDate.parse(dataArray.get(5), formatter);// Парсим и форматируем дату в формат, подходящий mysql
            dbConnection = DriverManager.getConnection(dbUrl, user, password);//Устанавливаем соединение с БД
            dbStatement = dbConnection.createStatement();
            dbResultStatement = dbConnection.createStatement();
            ResultSet resultSet = dbResultStatement.executeQuery(duplicateQuery);//Ищем дубликаты данных
            if (resultSet.next()) {//Если находим совпадение то обновляем данные
                while (resultSet.next()) {

                    //Обновляем данные
                    dbStatement.executeUpdate("UPDATE userdata_db.address a, userdata_db.persons p " +
                            "SET a.postcode = " + dataArray.get(7) + ", a.country = '" + dataArray.get(8) + "', a.region = '" + dataArray.get(9) + "', a.city = '" + dataArray.get(10) + "', a.street = '" + dataArray.get(11) + "', a.house = " + dataArray.get(12) + ", a.flat = " + dataArray.get(13) +
                            ", p.surname = '" + dataArray.get(1) + "', p.name = '" + dataArray.get(0) + "', p.middlename = '" + dataArray.get(2) + "', p.birthday = '" + birthDate + "', p.gender = '" + dataArray.get(4) + "', p.inn = " + dataArray.get(6) +
                            " WHERE " + resultSet.getString("address_id") + " = p.address_id;");
                    System.out.println("Обновленны данные пользователя с id: " + resultSet.getString("id"));//Выводим сообщение об обновлении данных
                }
            } else {//Если совпадений не найдено, то заносим новые данные в БД
                dbStatement.execute("INSERT INTO userdata_db.address (postcode, country, region, city, street, house, flat)" + " " + "VALUES ('" + dataArray.get(7) + "', '" + dataArray.get(8) + "', '" + dataArray.get(9) + "', '" + dataArray.get(10) + "', '" + dataArray.get(11) + "', '" + dataArray.get(12) + "', '" + dataArray.get(13) + "')");
                dbStatement.execute("INSERT INTO userdata_db.persons (surname, name, middlename, birthday, gender, inn, address_id)" + " " + "VALUES ('" + dataArray.get(1) + "', '" + dataArray.get(0) + "', '" + dataArray.get(2) + "', '" + birthDate + "', '" + dataArray.get(4) + "', '" + dataArray.get(6) + "', (SELECT id FROM userdata_db.address WHERE postcode = " + dataArray.get(7) + "))");
            }
        } catch (SQLException exception) {//Ловим исключения
            System.out.println("SQLException :" + exception.toString());
        } catch (Exception e) {
            System.out.println("Exception :" + e.toString());
        } finally {
            try {
                if (dbStatement != null) {//Закрываем стрим
                    dbStatement.close();
                }
            } catch (SQLException exception2) {
                System.out.println("SQLException :" + exception2.toString());
            }
            try {
                if (dbConnection != null) {//Закрываем подключение
                    dbConnection.close();
                }
            } catch (SQLException exception) {
                System.out.println("SQLException :" + exception.toString());
            }
        }
    }
}
