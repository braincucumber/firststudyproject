package NewYearPresent;

public class Chocolate extends Candy {
    private String size;

    public Chocolate(String brand, Double price, Double weight, String size) {
        super(brand, price, weight);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Chocolate [" + super.toString() + ", size = " + size + "]";
    }
}
