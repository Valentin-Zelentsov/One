import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

//TIP Для <b>запуска</b> кода нажмите <shortcut actionId="Run"/> или
// щелкните значок <icon src="AllIcons.Actions.Execute"/> в боковой области.
public class Main {
    public static void main(String[] args) {
        //TIP Нажмите <shortcut actionId="ShowIntentionActions"/>, поместив каретку на выделенный текст
        // чтобы увидеть, как GIGA IDE предлагает исправить это.


        String tst = "test";
        String tst2 = "tset";
        if (orderChar(tst).equals(orderChar(tst2)))
        {
            System.out.printf("является анаграммой");
        }
else {
            System.out.printf("не является анаграммой");


        }
    }
    public static String orderChar(String input)
    {
        ArrayList<Character> symbols= new ArrayList();
        for (char c:input.toCharArray())
        {
            symbols.add(c);
        }
        Collections.sort(symbols);
        String result = new String();
        for (char c:symbols)
        {
            result += c;
        }
        return symbols.toString();
    }
}