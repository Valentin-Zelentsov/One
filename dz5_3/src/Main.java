import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

//TIP Для <b>запуска</b> кода нажмите <shortcut actionId="Run"/> или
// щелкните значок <icon src="AllIcons.Actions.Execute"/> в боковой области.
public class Main {
    public static void main(String[] args) {
        //TIP Нажмите <shortcut actionId="ShowIntentionActions"/>, поместив каретку на выделенный текст
        // чтобы увидеть, как GIGA IDE предлагает исправить это.

        System.out.println("Введите наборы из букв английского алфавита, разделенных одним пробелом:");
        Scanner scanner = new Scanner(System.in);
        String input_str = scanner.nextLine();
        String[] words = input_str.split(" ");
        for (String s : words)
        {
            char[] wordChars = s.toCharArray();
            Arrays.sort(wordChars);
            System.out.println(wordChars);
        }

    }
}