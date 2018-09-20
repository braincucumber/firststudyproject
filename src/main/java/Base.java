import java.util.Scanner;

//Created by Evgeniy Busygin on 19.09.2018.
public class Base {
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
        System.out.println("Hello world!");
        System.out.println("Goodbye world");
        System.out.printf("%+020.10f", Math.PI);

        int i = 144;
        double root;
        root = Math.sqrt(i);
        System.out.printf("Корень числа %d равен %f", i, root);

        Scanner scanner = new Scanner(System.in);
        int o = scanner.nextInt();
        String text = scanner.next();
        System.out.println("Integer: " + o);
        System.out.println("String^ " + text);

        System.err.println("This is error stream");

        scanner.close();
    }
}
