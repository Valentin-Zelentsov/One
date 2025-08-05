import java.util.ArrayList;

public class App {
    private static ArrayList tvCollection;

    public static void main(String[] args) {
        //TIP Нажмите <shortcut actionId="ShowIntentionActions"/>, поместив каретку на выделенный текст
        // чтобы увидеть, как GIGA IDE предлагает исправить это.

        App.genTV(5);
        for (int i = 0;i<tvCollection.size();i++)
        {
            System.out.println(tvCollection.get(i).toString());
        }
    }


     static void genTV(int kolichestvo) {
        tvCollection = new ArrayList<TV>();
        for (int i = 0;i<kolichestvo;i++){tvCollection.add(new TV());}
    }
}
