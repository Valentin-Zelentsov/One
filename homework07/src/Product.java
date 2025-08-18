import java.util.Objects;

public class Product {
    private String name;
    private int cost;

    public Product(String name, int cost)
            throws Exception {

        if (name.length() < 3) {
            throw new Exception("Название продукта не может быть короче 3 символов");
        }
        if (!name.matches("\\d*"))
        {
            throw new Exception("Название продукта не может содержать только цифры");
        }
        if (cost < 0) {
            throw new Exception("Цена продукта не может быть отрицательной");
        }
        {
            this.name = name;
            this.cost = cost;
        }
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return (this.name.equals(product.name));
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, cost);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
