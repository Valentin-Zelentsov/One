import java.util.Scanner;

//TIP Для <b>запуска</b> кода нажмите <shortcut actionId="Run"/> или
// щелкните значок <icon src="AllIcons.Actions.Execute"/> в боковой области.
public class Main {
    public static void main(String[] args) {
        String arrow1 = ">>-->";
        String arrow2 = "<--<<";
        Scanner scanner = new Scanner(System.in);
        String inputStr= scanner.nextLine();
        if (inputStr.length()>105)
        {
            inputStr = inputStr.substring(0,106);
        }
        int count = 0;
        for (int i = 0; (i<inputStr.length()-4);i++)
        {
            if (inputStr.substring(i,i+5).equals(arrow1)||inputStr.substring(i,i+5).equals(arrow2))
            {
                count++;
                i=i+4; //чтобы стрелы не наезжали друг на друга и не было задвоения стрел по типу ">>-->>-->"
            }
        }
        System.out.println(count);
        //int i = inputStr.
        //TIP Нажмите <shortcut actionId="ShowIntentionActions"/>, поместив каретку на выделенный текст
        // чтобы увидеть, как GIGA IDE предлагает исправить это.
              }

}