import java.util.*;

//TIP Для <b>запуска</b> кода нажмите <shortcut actionId="Run"/> или
// щелкните значок <icon src="AllIcons.Actions.Execute"/> в боковой области.
public class App {

    static HashMap<String,Product> products;
    static HashMap<String,Product> persons;

    public static void main(String[] args) {
        //TIP Нажмите <shortcut actionId="ShowIntentionActions"/>, поместив каретку на выделенный текст
        // чтобы увидеть, как GIGA IDE предлагает исправить это.
        System.out.printf("Hello and welcome!");

        String input1 = "Павел Андреевич = 10000; Анна Петровна = 2000; Борис = 10";

        String input2 = "Хлеб = 40; Молоко = 60; Торт = 1000; Кофе растворимый = 879; Масло = 150";

        String input3 = "Павел Андреевич - Хлеб";
        String input4 = "Павел Андреевич - Масло";


        Scanner scanner = new Scanner(System.in);
        String inp = scanner.nextLine();
        persons = inputPersons(inp);
        inp = scanner.nextLine();
        inputProducts(inp);
        inp = scanner.nextLine();
        while (inp!="END")
        {
            addProd(inp);
        }


        }

        private static HashMap<String,Product> inputProducts(String input_str)
    {
        HashMap products = new HashMap<String,Product>();
        String[] productsStr = input_str.split(";");

        for (String prodsDescr : productsStr)
        {
            String[] nameAndCost = prodsDescr.split("=");
            Product prod = new Product(nameAndCost[0].trim(), Integer.getInteger(nameAndCost[1].trim()));
            products.put(prod,prod.getName());
        }
        return products;
    }
    private static HashMap<String,Person> inputPersons(String input_str)
    {
        HashMap persons = new HashMap<String,Person>();
        String[] personsStr = input_str.split(";");

        for (String persDescr : personsStr)
        {
            String[] nameAndSum = persDescr.split("=");
            Person prod = new Person(nameAndSum[0].trim(), Integer.getInteger(nameAndSum[1].trim()));
            persons.put(prod.getName(),prod);
        }
        return persons;
    }
    private static void addProd(String input_str)
    {
        String[] nameAndProd = input_str.split("=");
        addProd(persons.get(nameAndProd[0]),products.get(nameAndProd[1]));


    }
    private static void addProd(Person pers, Product prod)
    {
        pers.addProduct(prod);
    }
}