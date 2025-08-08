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
        String prodStr = "";
        for (Product prs : shoppingCart) {
            if (prodStr.length() == 0)
            {
                prodStr = prs.getName();
            }
            else
            {
                prodStr = prodStr + ", " + prs.getName();
            }
        }
        if (prodStr.length() ==0)
        {
            prodStr = " Ничего не куплено";
        }
        return this.name + " - " + prodStr;
    }

    public Person(String name, int sum) throws Exception {

        if (name.length()<3)
        {
            throw new Exception("Имя не может быть короче 3 символов");
        }
        if (sum<0)
        {
            throw new Exception("Деньги не могут быть отрицательными");
        }
        this.name = name;

        this.sum = sum;
        this.shoppingCart = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return (this.name.equals(person.name));
                //Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name+"tratata", sum, shoppingCart);
    }
}
