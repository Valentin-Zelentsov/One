import java.util.HashSet;
import java.util.Set;

public class PowerulSet {

public static <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
    HashSet<T> result = new HashSet<>();
    for (T t:set1){
        if (set2.contains(t)){
            result.add(t);

        }
    }
    return result;
}
    public static <T> Set<T> relativeComplement(Set<T> set1, Set<T> set2) {
        HashSet<T> result = new HashSet<>();
        for (T t:set1){
            if (!set2.contains(t)){
                result.add(t);

            }
        }
        return result;
    }
    public static <T> Set<T> union(Set<T> set1, Set<T> set2) {
        HashSet<T> result = new HashSet<>();
        for (T t:set1){
            result.add(t);
        }
        for (T t:set2){
            result.add(t);
        }
        return result;
    }
}