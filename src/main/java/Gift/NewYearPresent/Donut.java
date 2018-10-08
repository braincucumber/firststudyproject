package Gift.NewYearPresent;

public class Donut extends Candy {
    private String quality;

    public Donut(String brand, Double price, Double weight, String quality) {
        super(brand, price, weight);
        this.quality = quality;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }
    @Override
    public String toString() {
        return "Donut [" + super.toString() + ", quality = " + quality + "]";
    }
}
