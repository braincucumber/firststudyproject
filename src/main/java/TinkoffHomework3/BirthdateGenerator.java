package TinkoffHomework3;

import java.time.Period;
import java.util.Date;
import java.util.Random;

class BirthdateGenerator {
    private Random  randomBirthdate = new Random();
    private Date birthDate;
    private long birthdayValue;
    private int age;
    Date getBirthday() {
        birthdayValue = -946771200000L + (Math.abs(randomBirthdate.nextLong()) % (79L * 365 * 24 * 60 * 60 * 1000));
        return birthDate = new Date(birthdayValue);
    }
    int calculateAge(Date birthDate) {
        Date currentDate = new Date();
        long timeBetween = currentDate.getTime() - birthDate.getTime();
        double yearsBetween = timeBetween / 3.15576e+10;
        age = (int) Math.floor(yearsBetween);
        return age;

    }
}