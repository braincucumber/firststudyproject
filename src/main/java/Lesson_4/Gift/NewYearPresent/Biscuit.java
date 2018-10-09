package Lesson_4.Gift.NewYearPresent;

public class Biscuit extends Candy {
    private Integer amount;

    public Biscuit(String brand, Double price, Double weight, Integer amount) {
        super(brand, price, weight);
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Biscuit [" + super.toString() + ", amount = " + amount + "]";
    }
}
