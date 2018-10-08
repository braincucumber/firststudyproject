package Gift.NewYearPresent;

public abstract class Candy {
    private String brand;
    private Double price;
    private Double weight;

    public Candy(String brand, Double price, Double weight) {
        this.brand = brand;
        this.price = price;
        this.weight = weight;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "brand = " + brand + ", price = " + price + ", weight = " + weight;
    }
}
