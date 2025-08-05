import java.util.Scanner;

//TIP Для <b>запуска</b> кода нажмите <shortcut actionId="Run"/> или
// щелкните значок <icon src="AllIcons.Actions.Execute"/> в боковой области.
public class Main {
    public static void main(String[] args) {


        String keyboard = "qwertyuiopasdfghjklzxcvbnmq";

        System.out.println("Введите символ на английском языке (один):");

        Scanner scanner = new Scanner(System.in);
        String inp = scanner.nextLine();
        inp = inp.toLowerCase();
        inp = inp.substring(0,1);
        int i = keyboard.indexOf(inp)+1;
        String res = keyboard.substring(i, i+1);
        System.out.println("символ справа: " + res);



    }
}