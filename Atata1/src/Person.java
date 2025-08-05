import java.util.ArrayList;
import java.util.Objects;

public class Person {
    private String name;
    private int sum;
    private ArrayList<Product> shoppingCart;


    public void addProduct(Product newProd) {
        if (this.sum < newProd.getCost())
        {
            System.out.println(this.name + " не может позволить себе " + newProd.getName());
        }
        else {
            this.sum = this.sum - newProd.getCost();
            this.shoppingCart.add(newProd);
        }

    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sum=" + sum +
                ", shoppingCart=" + shoppingCart +
                '}';
    }

    public Person(String name, int sum) {
        this.name = name;
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name+"tratata", sum, shoppingCart);
    }
}
