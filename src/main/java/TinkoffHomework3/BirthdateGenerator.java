package TinkoffHomework3;

import java.util.Date;
import java.util.Random;

class BirthdateGenerator {
    private Random randomBirthdate = new Random();

    Date getBirthday() {
        long birthdayValue = -946771200000L + (Math.abs(randomBirthdate.nextLong()) % (79L * 365 * 24 * 60 * 60 * 1000));//Минимальное значение - 01.01.1940, максимальное - 01.01.2019
        return new Date(birthdayValue);//Возвращаем случайную дату
    }

    int calculateAge(Date birthDate) {
        Date currentDate = new Date();
        long timeBetween = currentDate.getTime() - birthDate.getTime();//Высчитываем количество времени между сегодняшним днем и днем рождения
        double yearsBetween = timeBetween / 3.15576e+10;//Переводим в годы
        return (int) Math.floor(yearsBetween);//Округляем и возвращаем вычисленное значение возраста

    }
}