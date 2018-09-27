import NewYearPresent.*;

/**
 * This program calculates total price and weight of a present box and shows all content of it.
 * @author Evgeniy Busygin
 */

public class Present {
    public static void main(String[] args) {
        Jellybean jellybean1 = new Jellybean("Google", 15.99, 1.06, "Jelly");
        Chocolate chocolate1 = new Chocolate("Alpen Gold", 5.50, 0.5, "20x20");
        Biscuit biscuit1 = new Biscuit("Tuc", 1.99, 2.0, 3);
        Donut donut1 = new Donut("DD", 9.99, 0.75, "Perfect");
        Candy[] box = {jellybean1, chocolate1, biscuit1, donut1};
        Double boxWeight = 0.0;
        Double boxPrice = 0.0;
        for (Candy someCandy : box
        ) {
            boxWeight += someCandy.getWeight(); // Вычисляется общий вес коробки
            boxPrice += someCandy.getPrice(); // Вычисляется общая цена коробки
            System.out.println(someCandy.toString());
        }
        System.out.println("Total weight of a box: " + boxWeight);
        System.out.println("Total price of a box: " + boxPrice);
    }
}
