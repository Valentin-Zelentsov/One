import java.nio.channels.ScatteringByteChannel;
import java.util.*;

//TIP Для <b>запуска</b> кода нажмите <shortcut actionId="Run"/> или
// щелкните значок <icon src="AllIcons.Actions.Execute"/> в боковой области.
public class App {

    static HashMap<String,Product> products;
    static HashMap<String,Person> persons;
    static ArrayList<Person> personList = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        try {
            String inp = "";

            System.out.println("Input persons");
            //String input1 = "Павел Андреевич = 10000; Анна Петровна = 2000; Борис = 10";
            inp = scanner.nextLine();
            persons = inputPersons(inp);
            System.out.println("Input products");

            //String input2 = "Хлеб = 40; Молоко = 60; Торт = 5000; Кофе растворимый = 879; Масло = 150";
            inp = scanner.nextLine();
            products = inputProducts(inp);

            System.out.println("Input deals:");
            inp = scanner.nextLine();

            //String input3 = "Павел Андреевич - Хлеб";
            //String input4 = "Павел Андреевич - Масло";
            //String input5 = "Павел Андреевич - Торт";



            while (!inp.toUpperCase().equals("END")) {
                addProd(inp);
                inp = scanner.nextLine();
            }

            for (Person prs : personList) {
                System.out.println(prs.toString());

            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


        }

        private static HashMap<String,Product> inputProducts(String input_str)
    {
        HashMap products = new HashMap<String,Product>();
        String[] productsStr = input_str.split(";");

        for (String prodsDescr : productsStr)
        {
            String[] nameAndCost = prodsDescr.split("=");
            Product prod = new Product(nameAndCost[0].trim(), Integer.parseInt(nameAndCost[1].trim()));
            products.put(prod.getName(),prod);
        }
        return products;
    }
    private static HashMap<String,Person> inputPersons(String input_str) throws Exception {
        HashMap persons = new HashMap<String,Person>();
        String[] personsStr = input_str.split(";");

        for (String persDescr : personsStr)
        {
            String[] nameAndSum = persDescr.split("=");
            Person pers = new Person(nameAndSum[0].trim(), Integer.parseInt(nameAndSum[1].trim()));
            persons.put(pers.getName(),pers);
            personList.add(pers);
        }
        return persons;
    }
    private static void addProd(String input_str)
    {
        String[] nameAndProd = input_str.split("-");
        String persName = nameAndProd[0];
        String prodName = nameAndProd[1];
        prodName = prodName.trim();
        Person prs = persons.get(persName.trim());
        Product prd = products.get(prodName);
        prs.addProduct(prd);


    }
}