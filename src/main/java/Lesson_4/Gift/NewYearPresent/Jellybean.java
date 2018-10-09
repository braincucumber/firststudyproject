package Lesson_4.Gift.NewYearPresent;

public class Jellybean extends Candy {
    private String type;

    public Jellybean(String brand, Double price, Double weight, String type) {
        super(brand, price, weight);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Jellybean {" + super.toString() + ", type = " + type + "]";
    }
}
