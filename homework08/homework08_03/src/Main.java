import java.util.Set;


//TIP Для <b>запуска</b> кода нажмите <shortcut actionId="Run"/> или
// щелкните значок <icon src="AllIcons.Actions.Execute"/> в боковой области.
public class Main {
    public static void main(String[] args) {
        Set<String> stroki = Set.of("1234", "01235");
        Set<String> stroki2 = Set.of("1234", "12356", "7891");
        System.out.println(PowerulSet.intersection(stroki,stroki2).toString());
        System.out.println(PowerulSet.union(stroki,stroki2).toString());
        System.out.println(PowerulSet.relativeComplement(stroki,stroki2).toString());
    }
}