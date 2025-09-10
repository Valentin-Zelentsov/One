import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//TIP Для <b>запуска</b> кода нажмите <shortcut actionId="Run"/> или
// щелкните значок <icon src="AllIcons.Actions.Execute"/> в боковой области.
public class Main {
    public static void main(String[] args) {
        //TIP Нажмите <shortcut actionId="ShowIntentionActions"/>, поместив каретку на выделенный текст
        // чтобы увидеть, как GIGA IDE предлагает исправить это.
        ArrayList<String> znahenia = new ArrayList<>();
        znahenia.add("длиношеее");
        znahenia.add("молоко");
        znahenia.add("длиношеее");
        ArrayList<String> result = uniqueElements(znahenia);
        for (String s:result){
            System.out.println(s);
        }
    }
    public static <T> ArrayList<T> uniqueElements (ArrayList<T> list)
    {
       //return new HashSet<>(list);
       return new ArrayList<T>(new HashSet<T>(list));
    }
}