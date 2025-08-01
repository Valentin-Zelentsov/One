import java.util.Random;//TIP Для <b>запуска</b> кода нажмите <shortcut actionId="Run"/> или
// щелкните значraок <icon src="AllIcons.Actions.Execute"/> в боковой области.
public class Main {
    public static void main(String[] args) {
        String vasyaName = "Вася";
        String petyaName = "Петя";
        String value0 = "Камень";
        String value1 = "Ножницы";
        String value2 = "Бумага";
        Random random = new Random();
        int vasyaBrosok = random.nextInt(3);
        int petyaBrosok = random.nextInt(3);
        //TIP Нажмите <shortcut actionId="ShowIntentionActions"/>, поместив каретку на выделенный текст
        // чтобы увидеть, как GIGA IDE предлагает исправить это.
        System.out.println(vasyaName + " выкинул " + getChoice(vasyaBrosok));
        System.out.println(petyaName + " выкинул " + getChoice(petyaBrosok));
        if (vasyaBrosok == petyaBrosok) {
            System.out.println("Ничья");
        }
        if (vasyaBrosok == 0) {
            if (petyaBrosok == 1) {
                System.out.println("Победа Васи");
            }
            if (petyaBrosok == 2) {
                System.out.println("Победа Пети");
            }
        }
        if (vasyaBrosok == 1) {
            if (petyaBrosok == 2) {
                System.out.println("Победа Васи");
            }
            if (petyaBrosok == 0) {
                System.out.println("Победа Пети");
            }
        }
        if (vasyaBrosok == 2) {
            if (petyaBrosok == 0) {
                System.out.println("Победа Васи");
            }
            if (petyaBrosok == 1) {
                System.out.println("Победа Пети");
            }
        }
    }


    public static String getChoice(int i) {
        String value0 = "Камень";
        String value1 = "Ножницы";
        String value2 = "Бумага";
        String res = "";
        if (i == 0) {
            res = value0;
        }
        if (i == 1) {
            res = value1;
        }
        if (i == 2) {
            res = value2;
        }
        return res;
    }
}

