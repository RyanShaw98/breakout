import java.util.Comparator;
import java.util.List;

public class ListComparator implements Comparator<List<String>> {

    @Override
    public int compare(List<String> o1, List<String> o2) {
        Integer s1 = Integer.parseInt(o1.get(o1.size() - 1));
        Integer s2 = Integer.parseInt(o2.get(o2.size() - 1));
        return s2.compareTo(s1);
    }
}