package TinkoffHomework3;
import java.util.Random;
class InnGenerator {
    private String innNumber;
    private Random innRandom = new Random();
    InnGenerator(String innNumber) {
        this.innNumber = innNumber;
    }

    String generateInn() {
        innNumber = "77";
        int[] innNumberArray = new int[8];
        for (int i = 0; i < innNumberArray.length; i++) {
            innNumberArray[i] = (innRandom.nextInt(10));
            innNumber += innNumberArray[i];
        }
        int firstINNControlNumber = (7 * 7 + 7 * 2 + (innNumberArray[0] * 4) + (innNumberArray[1] * 10) + (innNumberArray[2] * 3) + (innNumberArray[3] * 5) + (innNumberArray[4] * 9) + (innNumberArray[5] * 4) + (innNumberArray[6] * 6) + (innNumberArray[7] * 8)) % 11;
        if (firstINNControlNumber == 10) {
            firstINNControlNumber = 0;
        }
        int secondINNControlNumber = (7 * 3 + 7 * 7 + (innNumberArray[0] * 2) + (innNumberArray[1] * 4) + (innNumberArray[2] * 10) + (innNumberArray[3] * 3) + (innNumberArray[4] * 5) + (innNumberArray[5] * 9) + (innNumberArray[6] * 4) + (innNumberArray[7] * 6) + (firstINNControlNumber * 8)) % 11;
        if (secondINNControlNumber == 10) {
            secondINNControlNumber = 0;
        }
        innNumber += firstINNControlNumber + "" + secondINNControlNumber;
        return innNumber;
    }
}